package com.postegresql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.postegresql.service.GameService;
import com.postegresql.model.Game;
import com.postegresql.model.Move;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // Endpoint pour initialiser une nouvelle partie
    @CrossOrigin(origins = "https://tic-tac-toe-i7hg.onrender.com")
    @PostMapping("/newGame")
    public ResponseEntity<Game> newGame(@RequestBody Game game) {
        String board = "         ";
        game.setBoard(board);
        game.setCurrentPlayer("X");
        game.setStatus("En cours");
        gameService.createGame(game);
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.ok(createdGame);
    }

    // Endpoint pour récuperer tout les jeux
    @GetMapping("/allGames")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // Endpoint pour supprimer tout les jeux
    @DeleteMapping("/deleteAllGames")
    public void deleteAllGames() {
        gameService.deleteAllGames();
    }

    // Endpoint pour jouer un coup
    @CrossOrigin(origins = "https://tic-tac-toe-i7hg.onrender.com")
    @PostMapping("/playMove")
    public ResponseEntity<Game> playMove(@RequestBody Move move) {
        // La logique pour jouer le coup, peut-être dans le service
        Game updatedGame = gameService.playMove(move.getGameId(), move.getIndex(), move.getPlayer());
        return ResponseEntity.ok(updatedGame);
    }


    // Endpoint pour vérifier l'état de la victoire
    @CrossOrigin(origins = "https://tic-tac-toe-i7hg.onrender.com")
    @GetMapping("/checkVictory/{gameId}")
    public ResponseEntity<String> checkVictory(@PathVariable Long gameId) {
        String result = gameService.checkVictory(gameId);
        if (result == null) {
            return ResponseEntity.ok("Game continues");
        } else {
            return ResponseEntity.ok("Result: " + result);
        }
    }

    // Endpoint pour changer le current player (si son tour depasse 30 sec)
    @CrossOrigin(origins = "https://tic-tac-toe-i7hg.onrender.com")
    @GetMapping("/changePlayer/{gameId}")
    public ResponseEntity<Game> changePlayer(@PathVariable Long gameId) {
        Game updatedGame = gameService.changePlayer(gameId);
        return ResponseEntity.ok(updatedGame);
    }

    // Endpoint pour obtenir l'état actuel du jeu

    // Endpoint pour réinitialiser la partie
}

