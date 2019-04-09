/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.amazon.customskill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

import nlp.dkpro.backend.PosTagger;
import nlp.dkpro.backend.NlpSingleton;

/*
 * This class is the actual skill. Here you receive the input and have to produce the speech output. 
 */
public class AlexaSkillSpeechlet_Intro
    implements SpeechletV2
{

    static Logger logger = LoggerFactory.getLogger(AlexaSkillSpeechlet_Intro.class);
    public static String userRequest;

    private int counter;
    private PosTagger p;

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope)
    {
        p = NlpSingleton.getInstance();
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope)
    {
    	this.counter=0;
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope)
    {
       IntentRequest request = requestEnvelope.getRequest();

       Intent intent = request.getIntent();

       userRequest = intent.getSlot("Alles").getValue();
      
       //first iteration
       if(counter==0){
    	   counter++;
    	   String interstingWords="";
    	   List<String> parts= getParts(userRequest);
    
    	   if(parts.size()>0){
    		   interstingWords= StringUtils.join(parts," und ");
        	   return askUserResponse("Ich verstehe leider nicht was du mit "+interstingWords+ " meinst. Versuche es noch einmal!");
    	   }else{
    		   return askUserResponse("Ich verstehe leider nicht was du meinst. Versuche es noch einmal!");
    	   } 
      }
       //second iteration
       else if(counter==1){
    	   counter++;
    	   String interstingWords="";
    	   List<String> parts= getParts(userRequest);
    
    	   if(parts.size()>0){
    		   interstingWords= StringUtils.join(parts," und ");
        	   return askUserResponse("Ich verstehe immer noch nicht was du mit "+interstingWords+ " meinst.");
    	   }else{
    		   return askUserResponse("Ich verstehe leider schon wieder nicht was du meinst.");
    	   } 
       }
       // third iteration
       else{
    	   return response("Torsten, das musst du doch am besten wissen, du bist der Experte."); 
       }
       
    }


	private List<String> getParts(String userRequest2) {
		List<String> parts = null;
		try {
			parts = p.findNouns(userRequest);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return parts;
	}

	@Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope)
    {
        logger.info("Alexa session ends now");
    }

    /*
     * The first question presented to the skill user (entry point)
     */
    private SpeechletResponse getWelcomeResponse(){
      return askUserResponse("Hallo Torsten, hältst du wieder einen Vortrag über mich? Um was geht es da heute?");
//        return askUserResponse("<amazon:effect name=\"whispered\">Hey Leute</amazon:effect>, ich bin ein <phoneme alphabet=\"ipa\" ph=\"ˈfʌni\">funny</phoneme> Nomen <phoneme alphabet=\"ipa\" ph=\"bɒt\">bot</phoneme>! Sag einen Satz und ich nenne dir die enthaltenen Nomen");
    }

    /**
     * Tell the user something - the Alexa session ends after a 'tell'
     */
    private SpeechletResponse response(String text)
    {
        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);

        return SpeechletResponse.newTellResponse(speech);
    }

    /**
     * A response to the original input - the session stays alive after an ask request was send.
     *  have a look on https://developer.amazon.com/de/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html
     * @param text
     * @return
     */
    private SpeechletResponse askUserResponse(String text)
    {
        SsmlOutputSpeech speech = new SsmlOutputSpeech();
        speech.setSsml("<speak>" + text + "</speak>");

        SsmlOutputSpeech repromptSpeech = new SsmlOutputSpeech();
        repromptSpeech.setSsml("<speak><emphasis level=\"strong\">Hey!</emphasis> Bist du noch da?</speak>");

        Reprompt rep = new Reprompt();
        rep.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, rep);
    }

}
