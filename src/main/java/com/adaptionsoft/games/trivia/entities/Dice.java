package com.adaptionsoft.games.trivia.entities;

import java.util.Random;

import com.adaptionsoft.games.trivia.utils.TriviaGameProperties;

public final class Dice {

	private final int diceSides;
	
	private Random rand = new Random();
	
	public final int getDiceSides() {
		return diceSides;
	}
	
	private Dice(int diceSides){
		this.diceSides = diceSides;
	}
	
	public static Dice getNewDiceInstance(){
		return configDice();
	}
	
	private static Dice configDice(){
		
		return new Dice( Integer.valueOf(TriviaGameProperties.getProperty( TriviaGameProperties.DICE_SIDES)) );
	}
	
	public final int rollDice(){
		
		return rand.nextInt(this.getDiceSides() - 1) + 1;
	}
}
