package com.adaptionsoft.games.trivia.entities;

public class StatePlayer {

	private final Player player;
	
	private int boardPocition = 0;
	
	private boolean canMove = Boolean.TRUE;
	
	private StatePlayer( Player player ){
		this.player = player;
	}

	public static StatePlayer getNewStatePLayerInstance(Player player){
		return new StatePlayer(player);
	}
	
	public final Player getPlayer() {
		return player;
	}
	
	public final int getBoardPocition() {
		return boardPocition;
	}

	public final void setBoardPocition(int boardPocition) {
		this.boardPocition = boardPocition;
	}

	public final boolean isCanMove() {
		return canMove;
	}

	public final void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
}
