package co.edu.unbosque.vista;

import javax.swing.JLayeredPane;

public class CreditsState extends JLayeredPane{

	private Sound crMusic;
	
	public CreditsState() {
		
		crMusic = new Sound();
		
	}
	
	//Funci�n M�sica
		public void playMusic(int i) {
			
			crMusic.setFile(i);
			crMusic.play();
			crMusic.loop();
		}
		
		public void stopMusic() {
			
			crMusic.stop();
		}
}
