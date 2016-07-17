package com.adaptionsoft.games.trivia.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.adaptionsoft.games.trivia.exceptions.TriviaGameExceptions;
import com.adaptionsoft.games.trivia.utils.TriviaGameProperties;

public class Question {

	private final String question;
	
	private final int questionId;
	
	private final String clue;
	
	private final String questionCategory;
	
	private final String answer;

	public final String getQuestion() {
		return question;
	}

	public final int getQuestionId() {
		return questionId;
	}

	public final String getClue() {
		return clue;
	}

	public final String getQuestionCategory() {
		return questionCategory;
	}
	
	public final String getAnswer() {
		return answer;
	}
	
	private Question( int questionId, String question, String clue, String questionCategory, String answer ){
		this.question = question;
		this.questionCategory = questionCategory;
		this.questionId = questionId;
		this.clue = clue;
		this.answer = answer;
	}
		
	public static List<Question> getNewTriviaQuestionListInstance(){
		return configTriviaQuestion();
	}
	
	private static List<Question> configTriviaQuestion(){
		List<Question> triviaQuestions = new ArrayList<>();
		try ( BufferedReader questionsCSV = Files.newBufferedReader( Paths.get(
				                                            TriviaGameProperties.getProperty(
				                                    		          TriviaGameProperties.QUESTION_CSV_FILE_PATH) ) ) ){
			String csvLine = "";
			
			int i = 0;
			
			while ( ( csvLine = questionsCSV.readLine() ) != null ) {				
				String[] questionLine = null;
				if ( i != 0 ){
					questionLine = csvLine.split(",");
					
					triviaQuestions.add( new Question( Integer.parseInt(questionLine[0]), questionLine[1], questionLine[2], questionLine[3], questionLine[4]) );
				}
				i++;
			}
			
		} catch (IOException e) {
			throw new TriviaGameExceptions("[Expertus-Trivia] ERROR --> Problem importing csv questions: ", e );
		}
		return triviaQuestions;
	}
}
