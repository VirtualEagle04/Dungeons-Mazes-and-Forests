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

public class CreditsState extends JLayeredPane {

	private Sound crMusic;
	private JLabel crBackground, credits_info, thanks_text, thanks_text_shadow;
	private JButton back_button;
	private Font Alagard;

	public CreditsState() {
		
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
		
		crBackground = new JLabel(new ImageIcon("src/Assets/Gifs/crBackground.gif"));
		crBackground.setBounds(0, 0, 1024, 768);
		
		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));
		
		credits_info = new JLabel(new ImageIcon("src/Assets/Images/credits_info.png"));
		credits_info.setBounds(0,100,1024,609);
		
		thanks_text = new JLabel();
		thanks_text.setText("Special Thanks");
		thanks_text.setFont(Alagard);
		thanks_text.setFont(thanks_text.getFont().deriveFont(Font.ITALIC,25));
		thanks_text.setForeground(Color.white);
		thanks_text.setBounds(420,20,200,25);
		
		thanks_text_shadow = new JLabel();
		thanks_text_shadow.setText("Special Thanks");
		thanks_text_shadow.setFont(Alagard);
		thanks_text_shadow.setFont(thanks_text.getFont().deriveFont(Font.ITALIC,25));
		thanks_text_shadow.setForeground(Color.DARK_GRAY);
		thanks_text_shadow.setBounds(423,23,200,25);
		
		crMusic = new Sound();
		
		
		add(crBackground, JLayeredPane.DEFAULT_LAYER);
		add(back_button, JLayeredPane.DRAG_LAYER);
		add(thanks_text_shadow, JLayeredPane.POPUP_LAYER);
		add(thanks_text, JLayeredPane.DRAG_LAYER);
		add(credits_info, JLayeredPane.MODAL_LAYER);
	
		
		
	}

	public JLabel getCredits_info() {
		return credits_info;
	}


	public void setCredits_info(JLabel credits_info) {
		this.credits_info = credits_info;
	}


	public JLabel getThanks_text() {
		return thanks_text;
	}


	public void setThanks_text(JLabel thanks_text) {
		this.thanks_text = thanks_text;
	}


	public JLabel getThanks_text_shadow() {
		return thanks_text_shadow;
	}


	public void setThanks_text_shadow(JLabel thanks_text_shadow) {
		this.thanks_text_shadow = thanks_text_shadow;
	}


	public Font getAlagard() {
		return Alagard;
	}


	public void setAlagard(Font alagard) {
		Alagard = alagard;
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

	// Función Música
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
