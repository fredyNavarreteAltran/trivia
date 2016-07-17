package com.adaptionsoft.games.trivia.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.trivia.exceptions.TriviaGameExceptions;
import com.adaptionsoft.games.trivia.utils.TriviaGameProperties;

public class Rule {

	private final String ruleName;
	
	private final int value;
	

	private Rule (String ruleName, int value){
		this.ruleName = ruleName;
		this.value = value;
	}
	
	public final String getRuleName() {
		return ruleName;
	}

	public final int getValue() {
		return value;
	}
	
	public static List<Rule> getNewTriviaRulesListInstance(){
		return configTriviaRules();
	}
	
	private static List<Rule> configTriviaRules(){
		List<Rule> triviaRules = new ArrayList<>();
		try ( BufferedReader rulesCSV = Files.newBufferedReader( Paths.get(
				                                            TriviaGameProperties.getProperty(
				                                    		          TriviaGameProperties.RULE_CSV_FILE_PATH) ) ) ){
			String csvLine = "";
			
			int i = 0;
			
			while ( ( csvLine = rulesCSV.readLine() ) != null ) {				
				String[] ruleLine = null;
				if ( i != 0 ){
					ruleLine = csvLine.split(",");
					triviaRules.add( new Rule( ruleLine[0], Integer.parseInt(ruleLine[1]) ) );
				}
				i++;
			}
			
		} catch (IOException e) {
			throw new TriviaGameExceptions("[Expertus-Trivia] ERROR --> Problem importing csv rules: ", e );
		}
		return triviaRules;
	}
}
