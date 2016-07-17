package com.adaptionsoft.games.trivia.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.adaptionsoft.games.trivia.exceptions.TriviaGameExceptions;
import com.adaptionsoft.games.trivia.interfaces.Game;
import com.adaptionsoft.games.trivia.templates.TriviaTemplate;

public class TriviaGame extends TriviaTemplate implements Game{

	private List<Rule> trivaRules;
	
	private List<Player> players;
	
	private List<Question> questions;
	
	private Random questionRand = new Random();
	
	private TrivialBoard trivialBoard;
	
	private List<StatePlayer> statePlayers;
	
	private Dice dice;
	
	private Scanner askQuestion;
	
	@Override
	public void pauseGame(){
		
	}
	@Override
	public void resumeGame(){
		
	}
	@Override
	protected void configureTrivia() {
		configTriviaBoard();
		configTriviaDice();
		configTriviaRules();
		configTriviaQuestions();
		configTriviaPlayers();	
	}
	
	@Override
	protected void startTrivia() {
		boolean thereIsWinner = true;
		
		do{
			thereIsWinner = playTrivia();
		}while(!thereIsWinner);
		
	}
	@Override
	protected void finishTrivia() {
		System.out.println("Thanks for play Trivia with Expertus :) ");
		askQuestion.close();
	}
	
	private void configTriviaBoard(){		
		this.trivialBoard = TrivialBoard.getNewTrivialBoardInstance(); 
	}
	
	private void configTriviaDice(){
		this.dice = Dice.getNewDiceInstance();
	}
	
	private void configTriviaRules(){
		this.trivaRules = Rule.getNewTriviaRulesListInstance();
	}
	
	private void configTriviaQuestions(){
		this.questions = Question.getNewTriviaQuestionListInstance();
	}
	
	private void configTriviaPlayers(){
		this.players = Player.getNewTriviaPlayersListInstance();
		this.statePlayers = new ArrayList<>();
		for (Rule rule : trivaRules) {
			if (rule.getRuleName().equalsIgnoreCase("MinNumberPlayers")){
				if (players.size() < rule.getValue()){
					throw new TriviaGameExceptions("[Expertus-Trivia] ERROR --> We need more players to start the Trivia Game");
				}
			}
		}
		for (Player player : players) {
			statePlayers.add( StatePlayer.getNewStatePLayerInstance(player) );			
		}
		
	}
	
	private boolean playTrivia(){
		boolean thereIsWinner = Boolean.FALSE;
		Rule penaltyRule = null;
		for (Rule rule : trivaRules) {
			if (rule.getRuleName().equalsIgnoreCase("PenaltyBox")){
				penaltyRule = rule;
			}
		}
		boolean wrongAnswer = Boolean.FALSE;
		for (StatePlayer statePlayer : statePlayers) {
			if (thereIsWinner)
				break;
			
			do{
				if ( statePlayer.isCanMove() ){					
					int newBoardPocition = dice.rollDice();
					System.out.println( statePlayer.getPlayer().getPlayerName()+ " you can move " + newBoardPocition + " positions!");
					if ( ( newBoardPocition + statePlayer.getBoardPocition() ) > trivialBoard.getBoardSize() ){
						System.out.println("Expertus TRIVIA Hava a Winner -->  " + statePlayer.getPlayer().getPlayerName() );
						thereIsWinner = Boolean.TRUE;
						break;
					}
					statePlayer.setBoardPocition( newBoardPocition += statePlayer.getBoardPocition());
					if (trivialBoard.getBoxesBoardCategory().get( newBoardPocition ).equalsIgnoreCase( "PENALTY_BOX" )){
						newBoardPocition -= penaltyRule.getValue();
						if ( newBoardPocition > 0 ){
							statePlayer.setBoardPocition( newBoardPocition );
						}						
						if ( newBoardPocition < 0 ){
							statePlayer.setBoardPocition( 0 );
						}
						System.out.println( statePlayer.getPlayer().getPlayerName()+ " Sorry you are in Penalty Box :( your new position are: " + newBoardPocition );
						wrongAnswer = Boolean.TRUE;
						statePlayer.setCanMove(Boolean.FALSE);
					}else{
						wrongAnswer = askTriviaQuestion( trivialBoard.getBoxesBoardCategory().get( newBoardPocition ), statePlayer );
						if (wrongAnswer){
							statePlayer.setCanMove(Boolean.FALSE);
						}else {//only for clarification purposes
							statePlayer.setCanMove(Boolean.TRUE);
						}

					}
				}else {
					wrongAnswer = askTriviaQuestion( trivialBoard.getBoxesBoardCategory().get( statePlayer.getBoardPocition() ), statePlayer );
					if (wrongAnswer){
						statePlayer.setCanMove(Boolean.FALSE);
					}else {//only for clarification purposes
						statePlayer.setCanMove(Boolean.TRUE);
					}
				}
				
			}while(!wrongAnswer);
		}

		return thereIsWinner;
	}
	
	private boolean askTriviaQuestion(String category, StatePlayer statePlayer){
	   boolean matchCategory = Boolean.FALSE;
	   Question question;
	   
	   do{//get Random question
		   question = questions.get( questionRand.nextInt(questions.size() - 1) + 1 );
		   matchCategory = question.getQuestionCategory().equalsIgnoreCase(category);
	   }while ( !matchCategory);
	   System.out.println("Hey "+ statePlayer.getPlayer().getPlayerName() + "!, you are in " + statePlayer.getBoardPocition() + " board position. \nPlease type T for True or F for False, in this " + category + " question: \n");
	   askQuestion = new Scanner(System.in);
	   System.out.println("Question:  ( "  + question.getQuestion() + " )  T or F?");	
	   String typeAnswer = askQuestion.next();	   
	   if (question.getAnswer().equalsIgnoreCase("true") ){
		   return typeAnswer.equalsIgnoreCase("f");//return value for wrongAnswer
	   }else {
		   return typeAnswer.equalsIgnoreCase("t");
	   }
	}
}
