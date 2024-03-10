package com.postegresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;  
import jakarta.persistence.GenerationType;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String board; // Représente la grille de jeu, initialement vide
    private String currentPlayer; // 'X' ou 'O'
    private String status; // 'En cours', 'Victoire', 'Nul'

    public Game() {
        this.board = "         ";
        this.currentPlayer = "X";
        this.status = "En cours";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String checkVictory() {
        // Supposons que board est un String de 9 caractères où chaque caractère représente une case du tableau de tic-tac-toe.
        String[] lines = new String[]{
            "" + board.charAt(0) + board.charAt(1) + board.charAt(2), // Première ligne horizontale
            "" + board.charAt(3) + board.charAt(4) + board.charAt(5), // Deuxième ligne horizontale
            "" + board.charAt(6) + board.charAt(7) + board.charAt(8), // Troisième ligne horizontale
            "" + board.charAt(0) + board.charAt(3) + board.charAt(6), // Première ligne verticale
            "" + board.charAt(1) + board.charAt(4) + board.charAt(7), // Deuxième ligne verticale
            "" + board.charAt(2) + board.charAt(5) + board.charAt(8), // Troisième ligne verticale
            "" + board.charAt(0) + board.charAt(4) + board.charAt(8), // Première diagonale
            "" + board.charAt(2) + board.charAt(4) + board.charAt(6)  // Deuxième diagonale
        };
    
        // Vérifier les conditions de victoire pour 'X' et 'O'
        for (String line : lines) {
            if (line.equals("XXX")) {
                return "X"; // 'X' gagne
            } else if (line.equals("OOO")) {
                return "O"; // 'O' gagne
            }
        }
    
        // Vérifier le match nul (si aucune case n'est vide)
        if (!board.contains(" ")) {
            return "D"; // D pour Draw (Match Nul)
        }
    
        // Le jeu continue si aucune condition de victoire n'est remplie et s'il reste des cases vides
        return null; // Le jeu n'est pas encore terminé
    }
    
    public void resetGame() {
        this.board = "         ";
        this.currentPlayer = "X";
        this.status = "En cours";
    }
    
}

