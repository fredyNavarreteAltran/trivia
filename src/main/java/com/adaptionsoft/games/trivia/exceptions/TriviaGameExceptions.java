package com.adaptionsoft.games.trivia.exceptions;

public class TriviaGameExceptions extends Error{

	private static final long serialVersionUID = 1148630170452324784L;

	private String message;
	
	public TriviaGameExceptions(){
		super();
	}

    @Override
    public String getMessage(){
        return message;
    }
    
    public TriviaGameExceptions(String message) {
    	this.message = message;
    }
    
    public TriviaGameExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
