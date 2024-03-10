package com.postegresql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.postegresql.model.Game;
import java.util.List;
import java.util.Optional;

import com.postegresql.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Game game) {
        return gameRepository.save(game); 
    }
    
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }


    // Jouer un coup
    public Game playMove(Long gameId, int index, String player) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            String board = game.getBoard();
            char[] boardArray = board.toCharArray();
    
            // Valider le joueur actuel et le coup
            if (player.equals(game.getCurrentPlayer()) && boardArray[index] == ' ') {
                boardArray[index] = player.charAt(0);
    
                // Mettre à jour le tableau et alterner le joueur
                game.setBoard(String.valueOf(boardArray));
                game.setCurrentPlayer(game.getCurrentPlayer().equals("X") ? "O" : "X");
    
                // Vérifier le statut après le coup
                String check = game.checkVictory();
                if (check != null) {
                    game.setStatus(check.equals("D") ? "Draw" : check + " wins");
                }
    
                return gameRepository.save(game);
            } else {
                throw new IllegalStateException("Move not valid or wrong player");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with id: " + gameId);
        }
    }
    
    // Vérifier l'état de la victoire
    public String checkVictory(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            return game.checkVictory(); 
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with id: " + gameId);
        }
    }

    // Changer le joueur actuel
    public Game changePlayer(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setCurrentPlayer(game.getCurrentPlayer().equals("X") ? "O" : "X");
            return gameRepository.save(game);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with id: " + gameId);
        }
    }
    
    public void deleteAllGames() {
        gameRepository.deleteAll();
    }
    
    //reeinitialiser partie
    
}
