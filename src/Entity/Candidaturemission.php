<?php

namespace App\Entity;
use App\Entity\Missionfreelencer;
use App\enum\EtatCandidature;
use App\Repository\CandidaturemissionRepository;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Freelancer;
#[ORM\Entity(repositoryClass: CandidaturemissionRepository::class)]
class Candidaturemission
{
     // Constantes pour l'état
     const ETAT_EN_ATTENTE = 'en_attente';
     const ETAT_ACCEPTED = 'accepté';
     const ETAT_REFUSED = 'refusé';

    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(type: 'string')]
    private string $etat;

    
    #[ORM\ManyToOne(inversedBy: 'candidaturemissions')]
    private ?Missionfreelencer $mission = null;

    #[ORM\OneToOne(cascade: ['persist', 'remove'])]
    #[ORM\JoinColumn(name: 'demande_id', referencedColumnName: 'id', onDelete: 'CASCADE')]
    private ?DemandeMission $demande = null;

    #[ORM\Column(type: 'integer', nullable: true)]
    private ?int $score = null;

    #[ORM\Column(length: 255)]
    private ?string $userid = null;

    #[ORM\Column(length: 255)]
    private ?string $user = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEtat(): string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): static
    {
        $this->etat = $etat;
        return $this;
    }

   

    public function getMission(): ?Missionfreelencer
    {
        return $this->mission;
    }

    public function setMission(?Missionfreelencer $mission): static
    {
        $this->mission = $mission;
        return $this;
    }

    public function getDemande(): ?DemandeMission
    {
        return $this->demande;
    }

    public function setDemande(?DemandeMission $demande): static
    {
        $this->demande = $demande;
        return $this;
    }

    public function __construct()
    {
        $this->etat = self::ETAT_EN_ATTENTE;
    }

    public function accepter(): void
    {
        $this->etat = self::ETAT_ACCEPTED;
    }

    public function refuser(): void
    {
        $this->etat = self::ETAT_REFUSED;
    }

    public function getScore(): ?int
    {
        return $this->score;
    }

    public function setScore(?int $score): self
    {
        $this->score = $score;
        return $this;
    }

    public function getUserid(): ?string
    {
        return $this->userid;
    }

    public function setUserid(user $userid): static
    {
        $this->userid = $userid;

        return $this;
    }

    public function getUser(): ?user
    {
        return $this->user;
    }

    public function setUser(string $user): static
    {
        $this->user = $user;

        return $this;
    }
}
