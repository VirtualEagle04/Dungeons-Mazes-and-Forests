package co.edu.unbosque.vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TutorialState extends JPanel {

	private Sound tuMusic;
	private JButton back_button;

	public TutorialState() {
		
		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		
		
		add(back_button);
		tuMusic = new Sound();

	}
	

	public Sound getTuMusic() {
		return tuMusic;
	}


	public void setTuMusic(Sound tuMusic) {
		this.tuMusic = tuMusic;
	}


	public JButton getBack_button() {
		return back_button;
	}


	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
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
