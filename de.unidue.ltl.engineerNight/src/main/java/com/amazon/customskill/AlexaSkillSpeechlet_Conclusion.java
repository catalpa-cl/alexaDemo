package com.amazon.customskill;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class AlexaSkillSpeechlet_Conclusion  implements SpeechletV2 {

	public static String userRequest;

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		SsmlOutputSpeech speech = new SsmlOutputSpeech();
		speech.setSsml("<speak>"				
//				+ "Morgen fahre ich nach Grevenbroich auf der Montage."
				+ "OK Torsten, fassen wir zusammen: Ich hoffe, ich konnte einen Einblick über meine Funktionsweise vermitteln. Ich bin ein intelligentes Assistenzsystem basierend auf deep learning Verfahren. Leider verstehe ich Torsten nicht immer richtig, da Sprache mehrdeutig ist. Aber die mir zugrundeliegende Technologie wird laufend weiterentwickelt, auch von Ingenieuren an der Universität Duisburg-Essen. Ich wünsche noch eine schöne Zeit auf der Engineer’s Night 2019."
				+ "</speak>");

        return SpeechletResponse.newTellResponse(speech);
	}
		

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		SsmlOutputSpeech speech = new SsmlOutputSpeech();
		speech.setSsml("<speak>"
				+ "OK Torsten, fassen wir zusammen: <amazon:effect name=\"whispered\">Ich bin ein intelligenter Assistent</amazon:effect> , dessen gesammte Intelligenz in der Cloud liegt und auf deep learning Verfahren beruht. Leider verstehe ich die Menschen nicht immer richtig, weil Sprache mehrdeutig ist. "
				+ "</speak>");

        return SpeechletResponse.newTellResponse(speech);
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		// TODO Auto-generated method stub
		
	}

}
