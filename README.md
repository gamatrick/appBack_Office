Backend App Back Office

Ce projet est une application de gestion de back-office développée avec Spring Boot. Il permet de gérer des utilisateurs, des produits, des catégories et des commandes.
Technologies utilisées

    Java 21

    Spring Boot 3.2.0

    Spring Data JPA : Pour la persistance des données.

    Spring Security : Pour la gestion de l'authentification et des autorisations.

    MySQL / MariaDB : Base de données relationnelle.

    Hibernate : Framework ORM.

    Lombok : Pour réduire le code boilerplate (getters, setters, etc.).

    JSON Web Token (JWT) : Pour la sécurisation des échanges API.

Architecture du projet

L'application suit une architecture en couches standard :

    Controller : Expose les points d'entrée (endpoints) REST.

    Service : Contient la logique métier.

    Repository : Interface avec la base de données via Spring Data JPA.

    Entity : Modèles de données mappés sur les tables SQL.

    Security : Configuration des filtres de sécurité et de JWT.

Prérequis

    Java Development Kit (JDK) 21 ou supérieur.

    Maven 3.6+.

    Un serveur MySQL ou MariaDB actif.

Installation et configuration
1. Clonage du dépôt
Bash

git clone https://github.com/gamatrick/appBack_Office.git
cd appBack_Office/backend

2. Configuration de la base de données

Modifiez le fichier src/main/resources/application.properties avec vos identifiants locaux :
Properties

spring.datasource.url=jdbc:mysql://localhost:3306/db_backoffice
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

3. Compilation et lancement

Utilisez Maven pour compiler et lancer l'application :
Bash

mvn clean install
mvn spring-boot:run

L'application sera accessible par défaut sur le port 8080.
Modèle de données

Le projet comporte quatre entités principales :

    User : Gestion des comptes et des rôles.

    Product : Informations sur les produits (nom, prix, stock, image).

    Category : Classification des produits.

    Order : Suivi des commandes passées par les utilisateurs.

Sécurité

L'API est sécurisée. Les routes nécessitent une authentification via un jeton JWT, à l'exception des points d'accès configurés comme publics dans la classe SecurityConfig.
