package com.amazon.customskill;

public class AlexaServlet_Intro extends com.amazon.speech.speechlet.servlet.SpeechletServlet {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AlexaServlet_Intro() {
	    this.setSpeechlet(new AlexaSkillSpeechlet_Intro());
	}
	
}
