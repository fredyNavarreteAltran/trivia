package com.adaptionsoft.games.trivia.templates;

public abstract class TriviaTemplate {

	protected abstract void configureTrivia();
	
	protected abstract void startTrivia();
	
	protected abstract void finishTrivia();
	
	public final void launchTriviaGame(){
		
		configureTrivia();
		
		startTrivia();
		
		finishTrivia();
	}
	
}
