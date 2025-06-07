<?php

namespace App\Controller;


use App\Entity\Moderateur;
use App\Entity\User;
use App\Entity\Recruteur;
use App\Entity\ResetPasswordRequest;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\SecurityBundle\Security;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\String\Slugger\SluggerInterface;

class ModerateurController extends AbstractController
{ 
    
    #[Route('/profilemoderateur', name: 'app_profilemoderateur')]
    public function profile(ManagerRegistry $doctrine, Security $security, UserRepository $userRepository): Response
    {
        $user = $security->getUser();
    
        // Vérification si l'utilisateur est authentifié
        if (!$user) {
            return new Response('User not authenticated', 401);
        }
    
        // Vérification si l'objet user est valide
        if (!$user instanceof User) {
            return new Response('Invalid user object', 500);
        }
    
        // Vérifier si l'utilisateur est un moderateur
        if ($user instanceof Moderateur) {
            // Charger le profil du modérateur
            $profile = $doctrine->getRepository(Moderateur::class)->find($user->getId());
    
            // Vérifier si le profil existe
            if (!$profile) {
                return new Response('Profil not found', 404);
            }
    
            // Afficher la page du profil du modérateur
            return $this->render('security/profilemoderateur.html.twig', [
                'tabprofile' => $profile,
            ]);
        }
    
        // Si l'utilisateur n'est pas un modérateur, rediriger vers la page d'accueil
        return $this->redirectToRoute('app_index');
    }
    
    #[Route('/moderateur', name: 'app_moderateur')]
    public function index(ManagerRegistry $doctrine, Request $request): Response
    {
        // Créer une instance de Recruteur
        $recruteur = new Moderateur();

        // Gérer la soumission du formulaire
        if ($request->isMethod('POST')) {
            // Récupérer les données depuis la requête
            $recruteur->setNom($request->request->get('nom'));
            $recruteur->setPrenom($request->request->get('prenom'));
            $recruteur->setTel($request->request->get('tel'));
            $recruteur->setEmail($request->request->get('email'));
            $recruteur->setSociete($request->request->get('societe'));
  

            // Gestion du mot de passe
            $password = $request->request->get('password');
            if ($password) {
                $hashedPassword = password_hash($password, PASSWORD_BCRYPT);
                $recruteur->setPassword($hashedPassword);
            }
            
            // Vous pouvez ajouter ici une vérification que le champ "confirm_password" correspond à "password"
            // par exemple via une contrainte de validation côté entité ou dans le contrôleur.

            // Sauvegarder les données dans la base
            $entityManager = $doctrine->getManager();   
            $entityManager->persist($recruteur);
            $entityManager->flush();

            // Rediriger après l'enregistrement
            return $this->redirectToRoute('app_login');
        }

        return $this->render('security/moderateur.html.twig');
    } 
    #[Route('/updatemoderateur/{id}', name: 'app_updatemoderateur')]
public function updatemoderateur($id, ManagerRegistry $doctrine, Request $request, SluggerInterface $slugger): Response
{
    $em = $doctrine->getManager();
    $moderateur = $em->getRepository(Moderateur::class)->find($id);

    if (!$moderateur) {
        throw $this->createNotFoundException('Le modérateur n\'existe pas.');
    }

    if ($request->isMethod('POST')) {
        // Récupération des données du formulaire
        $moderateur->setNom($request->request->get('nom'));
        $moderateur->setPrenom($request->request->get('prenom'));
        $moderateur->setEmail($request->request->get('email'));
        $moderateur->setTel($request->request->get('tel'));
        $moderateur->setSociete($request->request->get('societe'));
        $moderateur->setDomaine($request->request->get('domaine'));
        $moderateur->setSexe($request->request->get('sexe'));


        

        $em->persist($moderateur);
        $em->flush();

        return $this->redirectToRoute('app_profilemoderateur');
    }

    // Affichage du formulaire de modification
    return $this->render('security/updatemoderateur.html.twig', [
        'moderateur' => $moderateur,
    ]);
}

#[Route('/deletemoderateur/{id}', name: 'app_deletemoderateur')]
public function deletemoderateur($id, ManagerRegistry $doctrine): Response
{
    $em = $doctrine->getManager();
    $moderateur = $em->getRepository(Moderateur::class)->find($id);

    if (!$moderateur) {
        throw $this->createNotFoundException('Le modérateur n\'existe pas.');
    }

    // Récupérer et supprimer les demandes de réinitialisation associées au modérateur
    $resetRequests = $em->getRepository(ResetPasswordRequest::class)->findBy(['user' => $moderateur]);
    foreach ($resetRequests as $request) {
        $em->remove($request);
    }

    // Supprimer le modérateur
    $em->remove($moderateur);
    $em->flush();

    $this->addFlash('success', 'Modérateur supprimé avec succès.');

    return $this->redirectToRoute('app_profilemoderateur');
}
}
