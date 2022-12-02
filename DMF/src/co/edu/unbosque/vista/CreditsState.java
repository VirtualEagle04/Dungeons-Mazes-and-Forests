package co.edu.unbosque.vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class CreditsState extends JLayeredPane{

	private Sound crMusic;
	private JLabel crBackground;
	private JButton back_button;
	
	public CreditsState() {
		
		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		crBackground = new JLabel(new ImageIcon("src/Assets/Gifs/crBackground.gif"));
		crBackground.setBounds(0, 0, 1024, 768);
		
		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));
		
		crMusic = new Sound();
		
		add(back_button);
		add(crBackground);
		
	}
	
	
	public Sound getCrMusic() {
		return crMusic;
	}


	public void setCrMusic(Sound crMusic) {
		this.crMusic = crMusic;
	}


	public JLabel getCrBackground() {
		return crBackground;
	}


	public void setCrBackground(JLabel crBackground) {
		this.crBackground = crBackground;
	}


	public JButton getBack_button() {
		return back_button;
	}


	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
	}


		//Función Música
		public void playMusic(int i) {
			
			crMusic.setFile(i);
			crMusic.play();
			crMusic.loop();
		}
		
		public void stopMusic(int i) {
			
			crMusic.stop(i);
		}
		
		public void playSE(int i) {
			
			crMusic.setFile(i);
			crMusic.play();
		}
}
