package co.edu.unbosque.vista;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	float currentVolume = 4;
	FloatControl fc;
	
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
		soundURL[10] = getClass().getResource("/Assets/Music/DMF-KeySeComp.wav");
		soundURL[11] = getClass().getResource("/Assets/Music/DMF-DeadSE.wav");
		soundURL[12] = getClass().getResource("/Assets/Music/DMF-MonkSE.wav");
		soundURL[13] = getClass().getResource("/Assets/Music/DMF-WinSE.wav");
		soundURL[14] = getClass().getResource("/Assets/Music/DMF-WarningSE.wav");
		
		
		

	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			
		}catch(Exception e) {
			
		}
	}
	
	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public float getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(float currentVolume) {
		this.currentVolume = currentVolume;
	}

	public FloatControl getFc() {
		return fc;
	}

	public void setFc(FloatControl fc) {
		this.fc = fc;
	}

	public URL[] getSoundURL() {
		return soundURL;
	}

	public void setSoundURL(URL[] soundURL) {
		this.soundURL = soundURL;
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
	
	public void volumeUp() {
		currentVolume += 1.0f;
		if(currentVolume > 6.0f) {
			currentVolume = 6.0f;
		}
		fc.setValue(currentVolume);
	}
	
	public void volumeDown() {
		
		currentVolume -= 1.0f;
		if(currentVolume < -80.0f) {
			currentVolume = - 80.0f;
		}
		fc.setValue(currentVolume);
	}
	

}
