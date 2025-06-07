<?php

namespace App\Controller;

use App\Entity\Candidature;
use App\Entity\Offre;
use App\Form\CandidatureaddType;
use App\Form\CandidatureType;
use App\Repository\CandidatureRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;
use Symfony\Contracts\Translation\TranslatorInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\SecurityBundle\Security;
use App\Entity\User;
use App\Entity\Chercheur;

final class CandidatureController extends AbstractController
{
    private TranslatorInterface $translator;

    // Assurez-vous que l'utilisateur est validé dans le constructeur
    public function __construct(TranslatorInterface $translator, Security $security)
    {
        $this->translator = $translator;
        $user = $security->getUser();
        
        if (!$user) {
            // Gérer l'absence d'utilisateur (redirection ou exception)
            throw new \Symfony\Component\Security\Core\Exception\AuthenticationException('User not authenticated');
        }
    
        if (!$user instanceof User) {
            // Gérer l'erreur d'objet utilisateur invalide
            throw new \LogicException('Invalid user object');
        }
    
        // L'utilisateur est valide à ce point et peut être utilisé si nécessaire
    }

    #[Route('/candidature', name: 'app_candidatureoffre')]
    public function index(CandidatureRepository $rep, Security $security): Response
    {
        $user = $security->getUser();
        if (!$user instanceof User) {
            throw new \LogicException('Invalid user object');
        }

        $candidature = $rep->findAll();

        return $this->render('candidature/index.html.twig', [
            'tabcandidature' => $candidature,
            'user' => $user // Add the user data to the view
        ]);
    }

    #[Route('/showcandidature', name: 'app_showcandidature')]
    public function showcandidature(CandidatureRepository $rep, Security $security): Response
    {
        $user = $security->getUser();
        if (!$user instanceof User) {
            throw new \LogicException('Invalid user object');
        }

        $candidature = $rep->findBy(['statut' => 'en_attente']);

        return $this->render('candidature/showcandidature.html.twig', [
            'tabcandidature' => $candidature,
            'user' => $user // Add the user data to the view
        ]);
    }

    #[Route('/addcandidature/{id}', name: 'app_addcandidature')]
    public function addcandidature(ManagerRegistry $m, Request $req, int $id, Security $security): Response
    {
        $user = $security->getUser();
        if (!$user) {
            return new Response('User not authenticated', 401);
        }

        if (!$user instanceof User) {
            return new Response('Invalid user object', 500);
        }

        $em = $m->getManager();
        $offre = $em->getRepository(Offre::class)->find($id);

        if (!$offre) {
            throw $this->createNotFoundException($this->translator->trans('Offre non trouvée'));
        }
        
        $candidature = new Candidature();
        $candidature->setStatut('en_attente');
        $candidature->setOffre($offre); // Associer l'offre à la candidature
        $candidature->setUtilisateur($user->getPrenom());

        if ($user instanceof Chercheur) {
            $candidature->setCv($user->getCv());
        }
        
        $candidature->setDatesoumission(new \DateTimeImmutable('now', new \DateTimeZone('UTC')));

        $form = $this->createForm(CandidatureaddType::class, $candidature);
        $form->handleRequest($req);
        if ($form->isSubmitted() && $form->isValid()) {
            $em->persist($candidature);
            $em->flush();
            return $this->redirectToRoute('app_offre');
        }

        return $this->render('candidature/addcandidature.html.twig', [
            'addform' => $form->createView(),
            'user' => $user // Add the user data to the view
        ]);
    }

    #[Route('/updatecandidature/{id}', name: 'app_updatecandidature')]
    public function updatecandidature(ManagerRegistry $m, Request $req, $id, CandidatureRepository $rep, Security $security): Response
    {
        $user = $security->getUser();
        if (!$user) {
            return new Response('User not authenticated', 401);
        }

        if (!$user instanceof User) {
            return new Response('Invalid user object', 500);
        }

        $em = $m->getManager();
        $candidature = $rep->find($id);
        $candidature->setUtilisateur($user->getPrenom());
        
        $form = $this->createForm(CandidatureaddType::class, $candidature);
        $form->handleRequest($req);
        if ($form->isSubmitted() && $form->isValid()) {
            $em->persist($candidature);
            $em->flush();
            return $this->redirectToRoute('app_offre');
        }

        return $this->render('candidature/updatecandidature.html.twig', [
            'addform' => $form->createView(),
            'user' => $user // Add the user data to the view
        ]);
    }

    #[Route('/deletecandidature/{id}', name: 'app_deletecandidature')]
    public function deletecandidature(ManagerRegistry $m, $id, CandidatureRepository $rep): Response
    {
        $em = $m->getManager();
        $candidature = $rep->find($id);
        $em->remove($candidature);
        $em->flush();
        
        return $this->redirectToRoute('app_offre');
    }

    #[Route('/accept/{id}', name: 'app_accept')]
    public function accept(ManagerRegistry $m, $id, CandidatureRepository $rep): Response
    {
        $em = $m->getManager();
        $candidature = $rep->find($id);

        if (!$candidature) {
            throw $this->createNotFoundException('Candidature non trouvée');
        }

        $candidature->setStatut('acceptée');
        $em->persist($candidature);
        $em->flush();

        return $this->redirectToRoute('app_acceptees');
    }

    #[Route('/reject/{id}', name: 'app_reject')]
    public function rejectCandidature(ManagerRegistry $m, $id, CandidatureRepository $rep): Response
    {
        $em = $m->getManager();
        $candidature = $rep->find($id);

        if (!$candidature) {
            throw $this->createNotFoundException('Candidature non trouvée');
        }

        $candidature->setStatut('rejetée');
        $em->persist($candidature);
        $em->flush();

        return $this->redirectToRoute('app_rejetees');
    }

    #[Route('/acceptes', name: 'app_acceptees')]
    public function Acceptees(CandidatureRepository $rep): Response
    {
        $candidatures = $rep->findBy(['statut' => 'acceptée']);

        return $this->render('candidature/acceptees.html.twig', [
            'tabcandidature' => $candidatures,
        ]);
    }

    #[Route('/rejetes', name: 'app_rejetees')]
    public function Rejetees(CandidatureRepository $rep): Response
    {
        $candidatures = $rep->findBy(['statut' => 'rejetée']);

        return $this->render('candidature/rejetees.html.twig', [
            'tabcandidature' => $candidatures,
        ]);
    }

    #[Route('/export/pdf/{id}', name: 'app_export_pdf')]
    public function exportToPdf(CandidatureRepository $rep, int $id): Response
    {
        
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        $dompdf = new Dompdf($pdfOptions);

        // Récupérer une seule candidature par ID
        $candidature = $rep->find($id);

        if (!$candidature) {
            throw $this->createNotFoundException("Candidature introuvable");
        }

        $html = $this->renderView('candidature/pdf.html.twig', [
            'candidature' => $candidature, // Passer une seule candidature
        ]);

        $dompdf->loadHtml($html);
        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();

        return new Response($dompdf->output(), 200, [
            'Content-Type' => 'application/pdf',
            'Content-Disposition' => 'inline; filename="candidature_'.$id.'.pdf"',
        ]);
    }
}
