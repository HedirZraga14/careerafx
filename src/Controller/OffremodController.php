<?php

namespace App\Controller;

use App\Entity\Offre;
use App\Entity\TypeContrat;
use App\Form\OffreType;
use App\Repository\CandidatureRepository;
use App\Repository\OffreRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\String\Slugger\SluggerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Entity\User;
use App\Entity\Moderateur;
use Symfony\Bundle\SecurityBundle\Security;

final class OffremodController extends AbstractController
{
    private OffreRepository $offreRepository;

    public function __construct(OffreRepository $offreRepository, Security $security)
    {
       
        $user = $security->getUser();
        
        if (!$user) {
            // Gérer l'absence d'utilisateur (redirection ou exception)
            throw new \Symfony\Component\Security\Core\Exception\AuthenticationException('User not authenticated');
        }
    
        if (!$user instanceof User) {
            // Gérer l'erreur d'objet utilisateur invalide
            throw new \LogicException('Invalid user object');
        }
    
    }

    #[Route('/offremod', name: 'app_offremod')]
    public function index(Security $security, OffreRepository $offreRepository): Response
    {

        // Retrieve offers associated with the current user
        $offres = $offreRepository->findAll();

        // If no offers are found, you can either show a default message or handle it as needed
        if (empty($offres)) {
            $this->addFlash('info', 'Aucune offre trouvée pour ce utilisateur.');
        }

        return $this->render('offre/offremod.html.twig', [
            'taboffre' => $offres,
        ]);
    }



#[Route('/ajoutoffre', name: 'app_ajoutoffre')]
public function ajoutoffre(ManagerRegistry $doctrine, Request $req, SluggerInterface $slugger, Security $security): Response
{
    $user = $security->getUser();
    dump($user);
    // Check if user is authenticated
    if (!$user) {
        return new Response('User not authenticated', 401);
    }

    if (!$user instanceof User) {
        return new Response('Invalid user object', 500);
    }

    $offre = new Offre();
    $offre->setDisponibilite(true);

    // Set the whole user object (not just the prenom)
    $offre->setUtilisateur($user->getPrenom());  // Associe l'utilisateur complet à l'offre

    $form = $this->createForm(OffreType::class, $offre);
    $form->handleRequest($req);

    if ($form->isSubmitted() && $form->isValid()) {
        // Find TypeContrat by name
        $typeContrat = $doctrine->getRepository(TypeContrat::class)->findOneBy(['nom' => 'CDI']);
        if (!$typeContrat) {
            $this->addFlash('error', "Le TypeContrat 'Stage' est introuvable.");
            return $this->redirectToRoute('app_ajoutoffre');
        }

        // Set related values
        $offre->setTypecontrat($typeContrat);
       

        // Handle image upload
        $imageFile = $form->get('image')->getData();
        if ($imageFile) {
            $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
            $safeFilename = $slugger->slug($originalFilename);
            $newFilename = $safeFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();

            try {
                $imageFile->move(
                    $this->getParameter('images_directory'),
                    $newFilename
                );
                $offre->setImage($newFilename);
            } catch (FileException $e) {
                $this->addFlash('error', 'Impossible de télécharger l’image.');
                return $this->redirectToRoute('app_ajoutoffre');
            }
        }

        // Persist the new offer
        $entityManager = $doctrine->getManager();
        $entityManager->persist($offre);
        $entityManager->flush();

        $this->addFlash('success', "Offre ajoutée avec succès !");
        return $this->redirectToRoute('app_offremod');
    }

    // Render the form
    return $this->render('offre/ajoutoffre.html.twig', [
        'addform' => $form->createView(),
        'user' => $user,
    ]);
}

#[Route('/modifoffre/{id}', name: 'app_modifoffre')]
public function modifoffre(OffreRepository $offreRepository,ManagerRegistry $doctrine, Request $req, SluggerInterface $slugger, int $id): Response
{
    $offre = $offreRepository->find($id);
    if (!$offre) {
        $this->addFlash('error', "Offre introuvable.");
        return $this->redirectToRoute('app_offremod');
    }

    $form = $this->createForm(OffreType::class, $offre);
    $form->handleRequest($req);

    if ($form->isSubmitted() && $form->isValid()) {
        $imageFile = $form->get('image')->getData();
        if ($imageFile) {
            $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
            $safeFilename = $slugger->slug($originalFilename);
            $newFilename = $safeFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();

            try {
                $imageFile->move(
                    $this->getParameter('images_directory'),
                    $newFilename
                );
                $offre->setImage($newFilename);
            } catch (FileException $e) {
                $this->addFlash('error', 'Impossible de télécharger l’image.');
            }
        }

        $doctrine->getManager()->flush();
        $this->addFlash('success', "Offre modifiée avec succès !");
        return $this->redirectToRoute('app_offremod');
    }

    return $this->render('offre/modifoffre.html.twig', [
        'addform' => $form->createView(),
    ]);
}


    #[Route('/supprimeroffre/{id}', name: 'app_supprimeroffre')]
    public function supprimeroffre(OffreRepository $offreRepository,ManagerRegistry $doctrine, int $id): Response
    {
        $offre = $offreRepository->find($id);
        if (!$offre) {
            $this->addFlash('error', "Offre introuvable.");
        } else {
            $doctrine->getManager()->remove($offre);
            $doctrine->getManager()->flush();
            $this->addFlash('success', "Offre supprimée avec succès !");
        }
        return $this->redirectToRoute('app_offremod');
    }

    #[Route('/statistiques', name: 'app_statistiques')]
    public function statistiques(OffreRepository $offreRepository): Response {
    
        // Récupération des statistiques
        $statsOffre = $offreRepository->countByOffreType();
        $statsLocalisation = $offreRepository->countByLocalisation();
        $statsSalaire = $offreRepository->countBySalaireRange();
    
        // Préparer les données pour Chart.js
        $labelsOffre = array_column($statsOffre, 'typeOffre'); // Extraction du nom
        $dataOffre = array_column($statsOffre, 'total');
    
        $labelsLocalisation = array_column($statsLocalisation, 'localisation');
        $dataLocalisation = array_column($statsLocalisation, 'total');
    
        $labelsSalaire = array_column($statsSalaire, 'range');
        $dataSalaire = array_column($statsSalaire, 'total');
    
        return $this->render('offre/statistiques.html.twig', [
            'labelsOffre' => json_encode($labelsOffre),
            'dataOffre' => json_encode($dataOffre),
            'labelsLocalisation' => json_encode($labelsLocalisation),
            'dataLocalisation' => json_encode($dataLocalisation),
            'labelsSalaire' => json_encode($labelsSalaire),
            'dataSalaire' => json_encode($dataSalaire),
        ]);
    }
    #[Route('/export/pdf2', name: 'app_export_pdf2')]
    public function exportToPdf2(OffreRepository $offreRepository,Security $security): Response
    {
        $user = $security->getUser();

        if (!$user) {
            // Gérer l'absence d'utilisateur (redirection ou exception)
            throw new \Symfony\Component\Security\Core\Exception\AuthenticationException('User not authenticated');
        }
    
        if (!$user instanceof User) {
            // Gérer l'erreur d'objet utilisateur invalide
            throw new \LogicException('Invalid user object');
        }
        

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        $dompdf = new Dompdf($pdfOptions);

        $offres = $offreRepository->findBy(['Utilisateur' => $user->getPrenom()]);
        $html = $this->renderView('offre/pdf.html.twig', [
            'offres' => $offres,
        ]);

        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'landscape');
        $dompdf->render();

        return new Response($dompdf->output(), 200, [
            'Content-Type' => 'application/pdf',
            'Content-Disposition' => 'inline; filename="offres.pdf"',
        ]);
    }
    
}
