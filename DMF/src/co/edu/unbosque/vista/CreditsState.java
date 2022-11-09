package co.edu.unbosque.vista;

import javax.swing.JLayeredPane;

public class CreditsState extends JLayeredPane{

	private Sound CrMusic;
	
	public CreditsState() {
		
		CrMusic = new Sound();
	}
	
	//Función Música
		public void playMusic(int i) {
			
			CrMusic.setFile(i);
			CrMusic.play();
			CrMusic.loop();
		}
		
		public void stopMusic() {
			
			CrMusic.stop();
		}


}
