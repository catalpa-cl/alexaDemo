package com.amazon.customskill;

public class AlexaServlet_Parrot extends com.amazon.speech.speechlet.servlet.SpeechletServlet {
	
	/**
     * 
     */
    private static final long serialVersionUID = 3L;

    public AlexaServlet_Parrot() {
	    this.setSpeechlet(new AlexaSkillSpeechlet_Parrot());
	}
	
}
