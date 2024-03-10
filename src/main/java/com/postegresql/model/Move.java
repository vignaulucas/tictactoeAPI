package com.postegresql.model;

public class Move {
    private Long gameId;
    private int index; // Index de la case dans le tableau
    private String player; // 'X' ou 'O'

    
    public Move(Long gameId, int index, String player) {
        this.gameId = gameId;
        this.index = index;
        this.player = player;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}

