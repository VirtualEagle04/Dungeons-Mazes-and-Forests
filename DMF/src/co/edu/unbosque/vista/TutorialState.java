package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TutorialState extends JLayeredPane {

	private Sound tuMusic;
	private JButton back_button;
	private JLabel tuBackground, tutorial_indications, key_tutorial, tuTitle, tuTitle_shadow, arrow_key;
	private Font Alagard;

	public TutorialState() {

		// Inicialización y Empaquetamiento de la Fuente

		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//

		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		tuTitle = new JLabel();
		tuTitle.setText("How to play");
		tuTitle.setFont(Alagard);
		tuTitle.setFont(tuTitle.getFont().deriveFont(Font.ITALIC, 40));
		tuTitle.setForeground(Color.white);
		tuTitle.setBounds(414, 20, 230, 40);

		tuTitle_shadow = new JLabel();
		tuTitle_shadow.setText("How to play");
		tuTitle_shadow.setFont(Alagard);
		tuTitle_shadow.setFont(tuTitle.getFont().deriveFont(Font.ITALIC, 40));
		tuTitle_shadow.setForeground(Color.DARK_GRAY);
		tuTitle_shadow.setBounds(414, 23, 230, 40);

		tuBackground = new JLabel(new ImageIcon("src/Assets/Gifs/tuBackground.gif"));
		tuBackground.setLocation(0, 0);
		tuBackground.setSize(1024, 768);

		tutorial_indications = new JLabel(new ImageIcon("src/Assets/Images/tutorial_indications.png"));
		tutorial_indications.setLocation(80, 100);
		tutorial_indications.setSize(448, 593);

		key_tutorial = new JLabel(new ImageIcon("src/Assets/Gifs/tutorial_key.gif"));
		key_tutorial.setLocation(560, 140);
		key_tutorial.setSize(100, 143);

		arrow_key = new JLabel(new ImageIcon("src/Assets/Images/arrow_key.png"));
		arrow_key.setBounds(660, 180, 160, 80);

		add(arrow_key);
		add(tuTitle);
		add(tuTitle_shadow);
		add(tutorial_indications);
		add(key_tutorial);
		add(tuBackground, JLayeredPane.DEFAULT_LAYER);
		add(back_button, JLayeredPane.MODAL_LAYER);

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

	public void stopMusic(int i) {

		tuMusic.stop(i);
	}
	
	public void playSE(int i) {
		
		tuMusic.setFile(i);
		tuMusic.play();
	}

}
