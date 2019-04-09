package com.amazon.customskill;

public class AlexaServlet_Conclusion extends com.amazon.speech.speechlet.servlet.SpeechletServlet {
	
	/**
     * 
     */
    private static final long serialVersionUID = 2L;

    public AlexaServlet_Conclusion() {
	    this.setSpeechlet(new AlexaSkillSpeechlet_Conclusion());
//	    this.setSpeechlet(new AlexaSkillSpeechlet_Parrot());

	}
	
}
