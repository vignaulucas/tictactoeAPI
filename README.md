# Tic Tac Toe API

Ce projet constitue une API pour jouer au jeu Tic Tac Toe (morpion) développée en utilisant Spring Boot.

## Prérequis

Avant de pouvoir exécuter l'API, assurez-vous d'avoir installé :

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) version 11 ou supérieure
- [Maven](https://maven.apache.org/download.cgi)

## Installation

1. Clonez ce dépôt sur votre machine locale :

    ```bash
    git clone https://github.com/votre-utilisateur/tictactoeApi.git
    ```

2. Accédez au répertoire du projet :

    ```bash
    cd tictactoeAPI
    ```

3. Compilez le projet en utilisant Maven :

    ```bash
    mvn clean install
    ```

## Exécution

Une fois le projet compilé, vous pouvez démarrer l'API en exécutant la commande suivante :

```bash
java -jar target/tictactoeApi.jar
```

L'API sera alors accessible à l'adresse suivante : `http://localhost:8080`.

## Utilisation

### Endpoints

L'API propose les endpoints suivants :

- `POST /api/game/newGame` : Initialise une nouvelle partie de Tic Tac Toe et retourne l'état initial du jeu.
- `GET /api/game/allGames` : Récupère la liste de tous les jeux enregistrés.
- `DELETE /api/game/deleteAllGames` : Supprime tous les jeux enregistrés.
- `POST /api/game/playMove` : Joue un coup dans la partie spécifiée.
- `GET /api/game/checkVictory/{gameId}` : Vérifie l'état de la victoire pour la partie spécifiée.
- `GET /api/game/changePlayer/{gameId}` : Change le joueur actuel de la partie spécifiée.

### Exemple d'utilisation

Pour démarrer une nouvelle partie, envoyez une requête POST à l'endpoint `/api/game/newGame`. Vous obtiendrez alors un identifiant de partie (`gameId`) ainsi que l'état initial du jeu.

Pour effectuer un mouvement dans la partie, envoyez une requête POST à l'endpoint `/api/game/playMove`, en spécifiant le mouvement dans le corps de la requête.

Pour obtenir l'état actuel de la partie, envoyez une requête GET à l'endpoint `/api/game/{gameId}`.

Pour terminer une partie, envoyez une requête DELETE à l'endpoint `/api/game/{gameId}`.

Pour vérifier l'état de la victoire dans une partie, envoyez une requête GET à l'endpoint `/api/game/checkVictory/{gameId}`.

Pour changer le joueur actuel dans une partie, envoyez une requête GET à l'endpoint `/api/game/changePlayer/{gameId}`.

