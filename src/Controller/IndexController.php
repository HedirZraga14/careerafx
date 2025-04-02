<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

final class IndexController extends AbstractController
{
    //page apres login
    #[Route('', name: 'app_index')]
    public function index(): Response
    {
        return $this->render('index/index.html.twig', [
            'controller_name' => 'IndexController',
        ]);
    }
    //page chercheur ou freelancer
    #[Route('/indexhome', name: 'app_indexhome')]
    public function indexhome(): Response
    {
        return $this->render('index/indexhome.html.twig', [
            'controller_name' => 'IndexController',
        ]);
    }
}
