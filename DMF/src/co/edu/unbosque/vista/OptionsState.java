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

public class OptionsState extends JLayeredPane {

	private JLabel opTitle,opTitleShadow,opBackground;
	private Sound OsMusic;
	private Font Alagard;
	private JButton back_button;
	
	public OptionsState() {
	
		//Inicialización y Empaquetamiento de la Fuente
		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		
		setLocation(0,0);
		setSize(1024, 768);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
	
		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));
		
		opTitle = new JLabel();
		opTitle.setFont(Alagard);
		opTitle.setFont(opTitle.getFont().deriveFont(Font.ITALIC, 40));
		opTitle.setForeground(Color.WHITE);
		opTitle.setText("Options");
		opTitle.setSize(1024, 40);
		opTitle.setLocation(410, 30);
		
		opTitleShadow = new JLabel();
		opTitleShadow.setFont(Alagard);
		opTitleShadow.setFont(opTitleShadow.getFont().deriveFont(Font.ITALIC, 40));
		opTitleShadow.setForeground(Color.DARK_GRAY);
		opTitleShadow.setText("Options");
		opTitleShadow.setSize(1024, 40);
		opTitleShadow.setLocation(413, 33);
		
		opBackground = new JLabel(new ImageIcon("src/Assets/Gifs/opBackground.gif"));
		opBackground.setLocation(0,0);
		opBackground.setSize(1024, 768);
		
		OsMusic = new Sound();
		
		setVisible(true);
		
		add(back_button);
		add(opTitle);
		add(opTitleShadow);
		add(opBackground);
	
	}

	//Getters & Setters
	
	
	public JLabel getOpTitle() {
		return opTitle;
	}


	public Sound getOsMusic() {
		return OsMusic;
	}

	public void setOsMusic(Sound osMusic) {
		OsMusic = osMusic;
	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public JButton getBack_button() {
		return back_button;
	}

	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
	}

	public void setOpTitle(JLabel opTitle) {
		this.opTitle = opTitle;
	}


	public JLabel getOpTitleShadow() {
		return opTitleShadow;
	}


	public void setOpTitleShadow(JLabel opTitleShadow) {
		this.opTitleShadow = opTitleShadow;
	}


	public JLabel getOpBackground() {
		return opBackground;
	}


	public void setOpBackground(JLabel opBackground) {
		this.opBackground = opBackground;
	}
	
	//Función Música
	public void playMusic(int i) {
		
		OsMusic.setFile(i);
		OsMusic.play();
		OsMusic.loop();
	}
	
	public void stopMusic() {
		
		OsMusic.stop();
	}

}
