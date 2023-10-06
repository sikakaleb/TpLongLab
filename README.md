# Projet de Vote Électronique RMI TpLongLab

## Introduction

L’objectif de ce laboratoire est de développer, à l’aide de RMI, un système de vote électronique. Ce système comprend un serveur qui permet aux utilisateurs de voter électroniquement et un client qui peut communiquer avec le serveur pour s’authentifier, récupérer la liste des candidats et voter.

## Exigences du Projet

- Ce travail doit être réalisé en binôme. Les deux membres doivent être dans le même groupe.
- La date cible de fin est fixée à 23h59 la veille du prochain laboratoire.
- Un membre de chaque binôme doit soumettre une archive .zip contenant le code source et un rapport expliquant les choix effectués, les difficultés rencontrées et comment utiliser les applications. L'archive doit également contenir les noms des deux étudiants.

## Fonctionnalités

### Candidats

Un candidat est caractérisé par :
- Un rang (considéré comme un identifiant unique), commençant à 1.
- Son prénom et son nom (sous forme de chaîne unique).
- [Facultatif] Un pitch (texte ou vidéo).

### Utilisateurs

Un utilisateur ou un électeur est représenté par :
- Un numéro d’étudiant.
- Un mot de passe.

### Authentification

L'authentification est simplifiée pour ce projet. Les utilisateurs autorisés sont déjà connus par le serveur. Pour authentifier un utilisateur :
- Le client fournit un talon comme paramètre de la méthode qui envoie le matériel de vote.
- Le serveur demande les informations d’identification.
- Si les informations sont correctes, le serveur envoie un mot de passe à usage unique que l'utilisateur doit fournir lors du vote.

### Vote

Nous adoptons le vote cardinal. La valeur de chaque candidat varie de 0 à 3. À la fin du vote, les scores sont additionnés et le candidat avec le score le plus élevé gagne.

## Cycle de vie

### Démarrage du serveur

Lorsque le serveur est lancé :
- Il récupère ou crée la liste des utilisateurs/votants et des candidats.
- Il définit les conditions de vote.

### Début du vote

Une fois le vote lancé, les utilisateurs :
- Consultent la liste des candidats.
- Obtiennent le matériel de vote.
- Votent.

### Fin du vote

Une fois le vote terminé :
- Les scores des candidats sont disponibles.
- Les résultats sont affichés côté serveur et peuvent être récupérés côté client.
