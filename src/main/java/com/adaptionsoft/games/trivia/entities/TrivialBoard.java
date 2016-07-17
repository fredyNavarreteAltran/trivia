package com.adaptionsoft.games.trivia.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adaptionsoft.games.trivia.utils.TriviaGameProperties;

public class TrivialBoard {

	private final int boardSize;
	
	private final List<Integer> penaltyBoxes;
	
	private final Map<Integer, String> boxesBoardCategory;
	
	private TrivialBoard( int boardSize, List<Integer> penaltyBoxes, Map<Integer, String> boxesBoardCategory ){
		this.boardSize = boardSize;
		this.boxesBoardCategory = boxesBoardCategory;
		this.penaltyBoxes = penaltyBoxes;
	}

	public final int getBoardSize() {
		return boardSize;
	}

	public final List<Integer> getPenaltyBoxes() {
		return penaltyBoxes;
	}

	public final Map<Integer, String> getBoxesBoardCategory() {
		return boxesBoardCategory;
	}
	
	public static TrivialBoard getNewTrivialBoardInstance(){
		return configTriviaBoard();
	}
	
	private static TrivialBoard configTriviaBoard(){
		List<String> penaltyStringboxes = 
				Arrays.asList(  TriviaGameProperties.getProperty( TriviaGameProperties.BOARD_PENALTY_BOXES).split(",") );
		
		List<Integer> penaltyBoxes = new ArrayList<>(); 
		
		for(String penaltyBox : penaltyStringboxes) 
			penaltyBoxes.add(Integer.valueOf(penaltyBox));

		List<String> configCategories = 
				Arrays.asList(  TriviaGameProperties.getProperty( TriviaGameProperties.BOARD_BOXES_CATEGORIES).split(",") );
		
		int boardSize = Integer.valueOf(TriviaGameProperties.getProperty(TriviaGameProperties.BOARD_ZISE));
		
		Map <Integer, String> boxesBoardCategory = new HashMap<>();
		
		int countCategories = 0;
		for( int i=0; i<boardSize; i++ ){
			if (penaltyBoxes.contains(i+1) ){
				boxesBoardCategory.put(i+1, "PENALTY_BOX");
			}else{
				boxesBoardCategory.put(i+1, configCategories.get(countCategories) );
				if (countCategories == configCategories.size() - 1 ){
					countCategories = 0;
				}else{
					countCategories ++;
				}
			}	
			

		}
		
		return new TrivialBoard(boardSize, penaltyBoxes, boxesBoardCategory); 
	
	}
}
