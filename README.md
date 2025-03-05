# E-Learning Platform

## Overview
Ce projet a été développé dans le cadre du programme **Esprit School of Engineering**. Il s'agit d'une plateforme d'apprentissage en ligne intégrant des fonctionnalités avancées pour améliorer l'expérience des étudiants et des enseignants.

## Features
- **Gestion des utilisateurs** avec **Keycloak**
- **Authentification sécurisée** via **JWT**
- **Recommandation de contenu** basée sur **Machine Learning (KNN)**
- **Suivi de progression et statistiques**
- **Tableau de bord administrateur** pour la gestion des cours et utilisateurs

## Tech Stack
### Frontend
- Angular 18.1.1
- Tailwind CSS
- FontAwesome

### Backend
- Spring Boot
- H2 Database
- Keycloak
- JWT Authentication

### Other Tools
- Docker (pour Keycloak et API Gateway)
- Flask API pour Machine Learning

## Directory Structure
```
E-Learning-Platform/
│── frontend/    # Application Angular
│── backend/     # API Spring Boot
│── ml-api/      # API Flask pour Machine Learning
│── docs/        # Documentation du projet
│── README.md    # Présentation du projet
```

## Getting Started
1. **Cloner le projet :**
   ```sh
   git clone https://github.com/Sarahhamami/Khotwa.git
   ```
2. **Configurer Keycloak** et démarrer les services Docker.
3. **Lancer le backend :**
   ```sh
   cd backend
   mvn spring-boot:run
   ```
4. **Lancer le frontend :**
   acceder à https://github.com/Yassinekh17/khotwaFront et cloner le projet front-end
   ```sh
   git clone https://github.com/Yassinekh17/khotwaFront
   ng serve 
   ```
6. **Accéder à la plateforme :** `http://localhost:4200`

## Acknowledgments
Ce projet a été développé sous la supervision de l'**Esprit School of Engineering** et dans le cadre du programme de PIDev 2eme année ingénieurie 4SAE.
