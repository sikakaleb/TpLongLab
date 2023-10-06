# Rapport sur le Système de Vote Électronique

## Auteur
Kaleb Sika (FISA)(https://github.com/sikakaleb/TpLongLab)

## Introduction

Ce rapport présente le système de vote électronique développé pour le projet. Le système permet aux électeurs de voter pour leurs candidats préférés et d'afficher les résultats une fois que tous les votes ont été comptabilisés.

## Choix de Conception

1. **Architecture Modulaire** : Le système est conçu avec une architecture modulaire, ce qui facilite la maintenance et l'évolutivité.
2. **Sécurité des Votes** : Chaque électeur reçoit un OTP (One-Time Password) pour assurer l'authenticité du vote.
3. **Flexibilité des Candidats** : Les candidats peuvent fournir soit un pitch textuel soit un pitch vidéo, montrant la flexibilité dans la manière dont ils peuvent se présenter.
4. **Gestion Centralisée des Votes** : Un `VoteManager` centralise la gestion des votes, assurant l'intégrité des données.
5. **Interface RMI** : L'utilisation de Java RMI facilite la communication entre le client et le serveur, offrant une expérience utilisateur transparente.

## Difficultés Rencontrées

1. **Synchronisation des Votes** : Assurer que chaque électeur ne vote qu'une seule fois a nécessité une gestion soignée des états.
2. **Gestion des Exceptions** : De nombreuses exceptions peuvent survenir, de l'authentification des électeurs à la validation des votes. Leur gestion a été cruciale pour assurer le bon fonctionnement du système.

## Boucles de vérification sur le Serveur et le Client
Dans l'implémentation actuelle du système de vote, nous utilisons des boucles infinies combinées à Thread.sleep() pour vérifier périodiquement l'état du vote et les résultats.

**Serveur :** Le serveur vérifie toutes les 12 secondes si le vote est terminé. Une fois le vote terminé, il envoie les résultats à tous les clients et sort de la boucle.

**Client :** Après avoir voté, le client vérifie toutes les 10 secondes si les résultats sont disponibles. Si les résultats sont disponibles, ils sont affichés et le client sort de la boucle. Sinon, un message est affiché indiquant que les résultats ne sont pas encore disponibles.

Bien que cette approche fonctionne pour notre démonstration, elle présente quelques points à considérer dans une application réelle :

**Éviter les boucles infinies :** Dans une application de production, l'utilisation de boucles infinies avec Thread.sleep() n'est pas idéale. Une approche basée sur la programmation événementielle ou d'autres mécanismes plus avancés serait préférable.

**Trafic Inutile :** Il est important de s'assurer que le délai de vérification côté client n'est pas trop court. Un délai trop court pourrait entraîner un trafic inutile, surtout si de nombreux clients sont connectés en même temps. Dans notre cas, un délai de 10 secondes semble raisonnable, mais cela dépendra toujours des spécificités de l'application.
## Comment Tester l'Application

1. **Lancement du Serveur** : Exécutez `VotingServer.java` pour démarrer le serveur RMI.
2. **Lancement du Client** : Exécutez `VotingClient.java` pour démarrer l'application client pour chaque utilisateur.
3. **Authentification** : Utilisez les identifiants fournis dans `votersData.csv` pour vous authentifier et recevoir un OTP.
4. **Voter** : Une fois authentifié, suivez les instructions à l'écran pour voter pour les candidats.
5. **Visualisation des Résultats** : Une fois que tous les électeurs ont voté ou que le temps imparti est écoulé, les résultats sont affichés.

## Identifiants des Utilisateurs

Les identifiants des utilisateurs sont stockés dans le fichier `votersData.csv`. Voici quelques exemples d'identifiants pour les tests :

| Nom  | Date de Naissance | Numéro d'Étudiant | Mot de Passe |
| ---- | ----------------- |-------------------|--------------|
| Alice | 01/01/1990       | AlicePass         | password1    |
| Bob   | 05/05/1992       | AbdelPass         | password2    |

(Note: Les vraies valeurs dépendent du contenu du fichier `votersData.csv`)

## Conclusion

Le système de vote électronique a été conçu et développé avec une attention particulière à la sécurité, à la flexibilité et à la facilité d'utilisation. Bien que certaines difficultés aient été rencontrées en cours de route, elles ont été surmontées grâce à une conception solide et une programmation soignée.
