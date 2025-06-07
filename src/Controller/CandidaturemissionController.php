<?php

namespace App\Controller;

use App\Entity\Candidaturemission;
use App\Entity\Missionfreelencer;
use App\Entity\Utilisateur;
use App\Repository\CandidaturemissionRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\DemandeMission;
use App\Service\SmsService;
use App\Entity\Freelancer;
use App\Entity\Moderateur;
use Symfony\Bundle\SecurityBundle\Security;
use App\Entity\User;

final class CandidaturemissionController extends AbstractController
{
    #[Route('/candidature', name: 'app_candidaturemission')]
    public function index(): Response
    {
        return $this->render('candidaturemission/index.html.twig', [
            'controller_name' => 'CandidaturemissionController',
        ]);
    }
    

 #[Route('/postuler/{offreId}', name: 'app_postuler_offre')]
public function postulerAOffre(int $offreId, EntityManagerInterface $entityManager, Security $security): Response
{
    $user = $security->getUser();

    if (!$user instanceof User) {
        return new Response('User not authenticated', 401);
    }

    $offre = $entityManager->getRepository(Missionfreelencer::class)->find($offreId);
    if (!$offre) {
        throw $this->createNotFoundException('Offre non trouvée.');
    }

    $candidature = new Candidaturemission();
    $candidature->setUser($user);   // Assure-toi que setUser existe et est correctement mappé
    $candidature->setMission($offre);  // Assure-toi que setMission existe

    $entityManager->persist($candidature);
    $entityManager->flush();

    return $this->redirectToRoute('app_offre_details', ['id' => $offre->getId()]);
}


    #[Route('/candidatures', name: 'app_lister_toutes_candidatures')]
    public function listerToutesCandidatures(Security $security, EntityManagerInterface $entityManager): Response
    {
        $user = $security->getUser();
        if (!$user instanceof Moderateur) {

            return $this->render('error/access_denied.html.twig');
    
        }

        

        // Récupérer toutes les candidatures
        $candidatures = $entityManager->getRepository(Candidaturemission::class)->findAll();

        return $this->render('candidaturemission/voir_candidatures.html.twig', [
            'candidatures' => $candidatures,
        ]);
    }


#[Route('/candidature/{id}/evaluer', name: 'evaluer_candidature', methods: ['POST'])]
public function evaluerCandidature(int $id, Request $request, EntityManagerInterface $entityManager): Response
{
    // Récupère la candidature par son ID
    $candidature = $entityManager->getRepository(Candidaturemission::class)->find($id);

    if (!$candidature) {
        $this->addFlash('error', 'Candidature non trouvée.');
        return $this->redirectToRoute('app_lister_toutes_candidatures');
    }

    // Récupérer le score depuis le formulaire
    $score = $request->request->get('score');

    if (!is_numeric($score) || $score < 1 || $score > 10) {
        $this->addFlash('error', 'Le score doit être un nombre entre 1 et 10.');
        return $this->redirectToRoute('app_lister_toutes_candidatures');
    }

    // Mettre à jour le score de la candidature
    $candidature->setScore((int)$score);

    // Sauvegarder la modification dans la base de données
    $entityManager->persist($candidature);
    $entityManager->flush();

    $this->addFlash('success', 'Score enregistré avec succès.');

    return $this->redirectToRoute('app_lister_toutes_candidatures');
}


#[Route('/candidature/{id}/{action}', name: 'accepter_refuser_candidature', methods: ['POST'])]
public function accepterOuRefuserCandidature(
    Candidaturemission $candidature,
    string $action,
    EntityManagerInterface $em,
    SmsService $smsService
): RedirectResponse {
    try {
        // Récupérer l'ID de l'utilisateur
        $userId = $candidature->getUserid();

        // Trouver l'utilisateur avec cet ID
        $user = $em->getRepository(User::class)->find($userId);

        if (!$user) {
            throw new \Exception('Utilisateur non trouvé.');
        }

        $phoneNumber = $user->getTel(); // Numéro de téléphone du freelancer

        if ($action === 'accepter') {
            $candidature->accepter();
            $this->addFlash('success', 'Candidature acceptée avec succès.');
            $message = "Votre candidature pour la mission {$candidature->getMission()->getTitre()} a été acceptée.";
        } elseif ($action === 'refuser') {
            $candidature->refuser();
            $this->addFlash('success', 'Candidature refusée avec succès.');
            $message = "Votre candidature pour la mission {$candidature->getMission()->getTitre()} a été refusée.";
        } else {
            throw new \InvalidArgumentException('Action non valide.');
        }

        $em->flush();

        // Vérifier si le numéro est valide avant d'envoyer le SMS
        if (!empty($phoneNumber)) {
            $smsService->sendSms($phoneNumber, $message);
        }

    } catch (\Exception $e) {
        $this->addFlash('error', $e->getMessage());
    }

    return $this->redirectToRoute('app_lister_toutes_candidatures');
}

#[Route('/demande/mission/{id<\d+>}', name: 'voir_demande_details')]
public function voirDemandeDetails(int $id, EntityManagerInterface $entityManager): Response
{
    // Récupérer la demande par son ID
    $demandeMission = $entityManager->getRepository(DemandeMission::class)->find($id);

    // Vérifier si la demande existe
    if (!$demandeMission) {
        $this->addFlash('error', 'Demande de mission non trouvée.');
        return $this->redirectToRoute('app_lister_toutes_candidatures');
    }

    // Passer la demande à la vue
    return $this->render('demande_mission/showmoderateur.html.twig', [
        'demande_mission' => $demandeMission,
    ]);
}

#[Route('/offre/{id}', name: 'app_offre_details')]
public function showOfferDetails(int $id, EntityManagerInterface $entityManager): Response
{
    $utilisateur = $this->getUser();
    // Récupérer la candidature de l'utilisateur pour cette offre
    $candidature = $entityManager->getRepository(Candidaturemission::class)
                                ->findOneBy(['utilisateur' => $utilisateur, 'mission' => $id]);

    // Récupérer les détails de l'offre
    $offreMission = $entityManager->getRepository(Missionfreelencer::class)->find($id);

    return $this->render('offrefrelencer/index.html.twig', [
        'offre' => $offreMission,
        'candidature' => $candidature, // Passer la candidature à la vue
    ]);
}

#[Route('/mes-candidatures', name: 'app_mes_candidatures')]
public function consulterCandidatures(CandidaturemissionRepository $candidaturemissionRepository): Response
{
    // Récupérer l'utilisateur connecté via Symfony Security
    $utilisateur = $this->getUser();
    
    if (!$utilisateur) {
        throw $this->createAccessDeniedException('Utilisateur non authentifié.');
    }

    // Récupérer les candidatures de l'utilisateur connecté
    $candidatures = $candidaturemissionRepository->findByUtilisateur($utilisateur);

    if (empty($candidatures)) {
        $this->addFlash('warning', 'Vous n\'avez pas encore de candidatures.');
    }

    // Retourner la réponse avec les candidatures
    return $this->render('demande_mission/mes_candidatures.html.twig', [
        'candidatures' => $candidatures,
    ]);
}



    }

