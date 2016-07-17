package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.entities.TriviaGame;

public class TriviaRunner {

	public static void main(String[] args) {
		
		TriviaRunner triviaRunner = new TriviaRunner();
		triviaRunner.launchTriviaGame();

	}

	private void launchTriviaGame(){
		
		TriviaGame triviaGame = new TriviaGame();
		triviaGame.launchTriviaGame();
	}
	
}
