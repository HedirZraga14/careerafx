<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20250402172827 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            CREATE TABLE blocked_user (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE candidature (id INT AUTO_INCREMENT NOT NULL, offre_id INT NOT NULL, statut VARCHAR(255) NOT NULL, datesoumission DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', utilisateur VARCHAR(255) NOT NULL, cv VARCHAR(255) NOT NULL, lettremotivation LONGTEXT NOT NULL, INDEX IDX_E33BD3B84CC8505A (offre_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE candidaturemission (id INT AUTO_INCREMENT NOT NULL, mission_id INT DEFAULT NULL, demande_id INT DEFAULT NULL, etat VARCHAR(255) NOT NULL, score INT DEFAULT NULL, userid VARCHAR(255) NOT NULL, user VARCHAR(255) NOT NULL, INDEX IDX_56CFA3BBE6CAE90 (mission_id), UNIQUE INDEX UNIQ_56CFA3B80E95E18 (demande_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE chercheur (id INT NOT NULL, photo VARCHAR(255) NOT NULL, cv VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE demande_mission (id INT AUTO_INCREMENT NOT NULL, offremission_id INT DEFAULT NULL, message LONGTEXT DEFAULT NULL, portfolio VARCHAR(255) DEFAULT NULL, status VARCHAR(20) NOT NULL, userid VARCHAR(255) NOT NULL, INDEX IDX_A0904993AF78A772 (offremission_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE discussion (id INT AUTO_INCREMENT NOT NULL, title VARCHAR(30) NOT NULL, content VARCHAR(255) NOT NULL, created_at DATETIME NOT NULL, user_id INT NOT NULL, likes INT DEFAULT 0 NOT NULL, dislikes INT DEFAULT 0 NOT NULL, user_name VARCHAR(30) NOT NULL, user_photo VARCHAR(30) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE evenement (id INT AUTO_INCREMENT NOT NULL, type_event_id INT DEFAULT NULL, description VARCHAR(255) DEFAULT NULL, date DATETIME NOT NULL, lieu VARCHAR(255) NOT NULL, disponibilite TINYINT(1) NOT NULL, image_url VARCHAR(255) DEFAULT NULL, nom VARCHAR(255) NOT NULL, user_id INT DEFAULT NULL, participants_ids JSON DEFAULT NULL COMMENT '(DC2Type:json)', INDEX IDX_B26681EBC08CF77 (type_event_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE freelancer (id INT NOT NULL, photo VARCHAR(255) NOT NULL, cv VARCHAR(255) NOT NULL, adresse VARCHAR(255) DEFAULT NULL, annees_experience INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE message (id INT AUTO_INCREMENT NOT NULL, messager2 VARCHAR(100) NOT NULL, messager1 VARCHAR(100) NOT NULL, content VARCHAR(255) NOT NULL, created_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', user_name_sender VARCHAR(100) NOT NULL, user_photo_sender VARCHAR(100) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE missionfreelencer (id INT AUTO_INCREMENT NOT NULL, titre VARCHAR(255) DEFAULT NULL, prix DOUBLE PRECISION DEFAULT NULL, description VARCHAR(255) DEFAULT NULL, datelimite VARCHAR(255) DEFAULT NULL, specialite VARCHAR(255) NOT NULL, date DATETIME DEFAULT NULL, userid VARCHAR(255) NOT NULL, image_url VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE moderateur (id INT NOT NULL, societe VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE notification (id INT AUTO_INCREMENT NOT NULL, message VARCHAR(255) NOT NULL, is_read TINYINT(1) NOT NULL, created_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', user_id VARCHAR(100) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE offre (id INT AUTO_INCREMENT NOT NULL, typecontrat_id INT NOT NULL, typeoffre_id INT NOT NULL, nomposte VARCHAR(255) NOT NULL, entreprise VARCHAR(255) NOT NULL, localisation VARCHAR(255) NOT NULL, salaire DOUBLE PRECISION NOT NULL, disponibilite TINYINT(1) NOT NULL, image VARCHAR(255) NOT NULL, utilisateur VARCHAR(255) NOT NULL, INDEX IDX_AF86866FE179E612 (typecontrat_id), INDEX IDX_AF86866F10A1DED2 (typeoffre_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE reply (id INT AUTO_INCREMENT NOT NULL, discussion_id INT NOT NULL, content VARCHAR(255) NOT NULL, created_at DATETIME NOT NULL, user_id INT NOT NULL, likes INT DEFAULT 0 NOT NULL, dislikes INT DEFAULT 0 NOT NULL, user_name VARCHAR(30) NOT NULL, user_photo VARCHAR(30) DEFAULT NULL, INDEX IDX_FDA8C6E01ADED311 (discussion_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE reset_password_request (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, selector VARCHAR(20) NOT NULL, hashed_token VARCHAR(100) NOT NULL, requested_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', expires_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', INDEX IDX_7CE748AA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE type_contrat (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(50) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE type_event (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE type_offre (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(50) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles JSON NOT NULL COMMENT '(DC2Type:json)', password VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, domaine VARCHAR(255) DEFAULT NULL, sexe VARCHAR(20) DEFAULT NULL, tel VARCHAR(20) DEFAULT NULL, type VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', available_at DATETIME NOT NULL COMMENT '(DC2Type:datetime_immutable)', delivered_at DATETIME DEFAULT NULL COMMENT '(DC2Type:datetime_immutable)', INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE candidature ADD CONSTRAINT FK_E33BD3B84CC8505A FOREIGN KEY (offre_id) REFERENCES offre (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE candidaturemission ADD CONSTRAINT FK_56CFA3BBE6CAE90 FOREIGN KEY (mission_id) REFERENCES missionfreelencer (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE candidaturemission ADD CONSTRAINT FK_56CFA3B80E95E18 FOREIGN KEY (demande_id) REFERENCES demande_mission (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE chercheur ADD CONSTRAINT FK_9DD29B50BF396750 FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE demande_mission ADD CONSTRAINT FK_A0904993AF78A772 FOREIGN KEY (offremission_id) REFERENCES missionfreelencer (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE evenement ADD CONSTRAINT FK_B26681EBC08CF77 FOREIGN KEY (type_event_id) REFERENCES type_event (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE freelancer ADD CONSTRAINT FK_4C2ED1E8BF396750 FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE moderateur ADD CONSTRAINT FK_6DDC3554BF396750 FOREIGN KEY (id) REFERENCES user (id) ON DELETE CASCADE
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE offre ADD CONSTRAINT FK_AF86866FE179E612 FOREIGN KEY (typecontrat_id) REFERENCES type_contrat (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE offre ADD CONSTRAINT FK_AF86866F10A1DED2 FOREIGN KEY (typeoffre_id) REFERENCES type_offre (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reply ADD CONSTRAINT FK_FDA8C6E01ADED311 FOREIGN KEY (discussion_id) REFERENCES discussion (id)
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reset_password_request ADD CONSTRAINT FK_7CE748AA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)
        SQL);
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql(<<<'SQL'
            ALTER TABLE candidature DROP FOREIGN KEY FK_E33BD3B84CC8505A
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE candidaturemission DROP FOREIGN KEY FK_56CFA3BBE6CAE90
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE candidaturemission DROP FOREIGN KEY FK_56CFA3B80E95E18
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE chercheur DROP FOREIGN KEY FK_9DD29B50BF396750
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE demande_mission DROP FOREIGN KEY FK_A0904993AF78A772
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EBC08CF77
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE freelancer DROP FOREIGN KEY FK_4C2ED1E8BF396750
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE moderateur DROP FOREIGN KEY FK_6DDC3554BF396750
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE offre DROP FOREIGN KEY FK_AF86866FE179E612
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE offre DROP FOREIGN KEY FK_AF86866F10A1DED2
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reply DROP FOREIGN KEY FK_FDA8C6E01ADED311
        SQL);
        $this->addSql(<<<'SQL'
            ALTER TABLE reset_password_request DROP FOREIGN KEY FK_7CE748AA76ED395
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE blocked_user
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE candidature
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE candidaturemission
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE chercheur
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE demande_mission
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE discussion
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE evenement
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE freelancer
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE message
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE missionfreelencer
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE moderateur
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE notification
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE offre
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE reply
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE reset_password_request
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE type_contrat
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE type_event
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE type_offre
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE user
        SQL);
        $this->addSql(<<<'SQL'
            DROP TABLE messenger_messages
        SQL);
    }
}
