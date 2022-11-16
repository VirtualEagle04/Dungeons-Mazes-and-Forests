package co.edu.unbosque.vista;

import javax.swing.JPanel;

public class TutorialState extends JPanel{
	
	private Sound tuMusic;
	
	public TutorialState() {
		
		tuMusic = new Sound();
		
	}
	
	public void playMusic(int i) {

		tuMusic.setFile(i);
		tuMusic.play();
		tuMusic.loop();
	}

	public void stopMusic() {

		tuMusic.stop();
	}
	
}
