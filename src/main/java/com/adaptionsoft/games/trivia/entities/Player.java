package com.adaptionsoft.games.trivia.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private final int position;
	
	private final String playerName;
		
	private Player( int position, String playerName ){
		this.position = position;
		this.playerName = playerName;
	}
	
	public final int getPosition() {
		return position;
	}

	public final String getPlayerName() {
		return playerName;
	}
	
	public static List<Player> getNewTriviaPlayersListInstance(){
		return configTriviaPlayers();
	}
	
	private static List<Player> configTriviaPlayers(){
		List<Player> players = new ArrayList<Player>();
		players.add(new Player( 1, "Chet" ) );
		players.add(new Player( 2, "Pat" ) );
		players.add(new Player( 3, "Sue" ) );	
		
		return players;
	}
	
}
