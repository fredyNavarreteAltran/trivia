package com.adaptionsoft.games.trivia.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TriviaGameProperties {
	
	private static Properties triviaProperties;

	public static final String BOARD_ZISE = "trivia.board.size";
	public static final String BOARD_PENALTY_BOXES = "tivia.board.penaltiesBoxes";
	public static final String BOARD_BOXES_CATEGORIES = "trivia.board.boxesCategories";
	
	public static final String DICE_SIDES = "trivia.dice.diceSides";
	
	public static final String RULE_CSV_FILE_PATH = "trivia.rule.csvPath";
	
	public static final String QUESTION_CSV_FILE_PATH = "trivia.question.csvPath";
	
	public static String getProperty( String triviaProperty ) {

		if ( triviaProperties == null ) {			
			triviaProperties = new Properties();
			String propertiesFile = null;
			propertiesFile = System.getProperty( "trivia.propertyFile.location" ) + "\\triviaGame.properties";			
			try ( FileInputStream documentStream = new FileInputStream( propertiesFile ) ) {	
				triviaProperties.load( documentStream );
						
			}  catch (IOException e) {				
				return null;
			}
		}		
		return triviaProperties.getProperty( triviaProperty );	 
	}
}
