package co.edu.unbosque.vista;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/Assets/Music/DMF-MainTitleComp.wav");
		soundURL[1] = getClass().getResource("/Assets/Music/DMF-OptionsMenuComp.wav");
		soundURL[2] = getClass().getResource("/Assets/Music/DMF-CreditsMenuComp.wav");
		soundURL[3] = getClass().getResource("/Assets/Music/DMF-CharacterSelectionComp.wav");
		soundURL[4] = getClass().getResource("/Assets/Music/DMF-HowToPlayMenuComp.wav");
		soundURL[5] = getClass().getResource("/Assets/Music/DMF-GameSoundComp.wav");
		soundURL[6] = getClass().getResource("/Assets/Music/DMF-NewGameSE.wav");
		soundURL[7] = getClass().getResource("/Assets/Music/DMF-ButtomsSE.wav");
		soundURL[8] = getClass().getResource("/Assets/Music/DMF-OptionsMenuSE.wav");
		soundURL[9] = getClass().getResource("/Assets/Music/DMF-PlayerSelectionSE.wav");
		soundURL[10] = getClass().getResource("/Assets/Music/DMF-KeySE.wav");
		soundURL[10] = getClass().getResource("/Assets/Music/DMF-MonsterSE.wav");
		soundURL[11] = getClass().getResource("/Assets/Music/DMF-MonkSE.wav");
		soundURL[12] = getClass().getResource("/Assets/Music/DMF-FinishSE.wav");
		
		
		

	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop(int i) {
		
		clip.stop();
	}
	

}
