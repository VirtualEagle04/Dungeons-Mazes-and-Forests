package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TitleState extends JLayeredPane {
	// Components
	private JButton tsNewGame, tsTutorial, tsQuit, tsCredits;
	private JLabel tsLogo, tsBackground, tsLogoShadow, tsInfo, tsTeam;
	private Sound tsMusic;
	private Font Alagard ;

	public TitleState() {
		
		//Inicialización y Empaquetamiento de la Fuente
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
		
		tsBackground = new JLabel(new ImageIcon("src/Assets/Gifs/tsBackground.gif"));
		tsBackground.setBounds(0, 0, 1024, 768);
		
		tsInfo = new JLabel();
		tsInfo.setFont(Alagard);
		tsInfo.setFont(tsInfo.getFont().deriveFont(Font.ITALIC, 15));
		tsInfo.setForeground(Color.WHITE);
		tsInfo.setText("DMF ver. 6.2");
		tsInfo.setSize(380, 330);
		tsInfo.setLocation(10, 538);
		
		tsTeam = new JLabel();
		tsTeam.setFont(Alagard);
		tsTeam.setFont(tsTeam.getFont().deriveFont(Font.ITALIC, 15));
		tsTeam.setForeground(Color.WHITE);
		tsTeam.setText("Arbolitos Transnochados INC.");
		tsTeam.setSize(380, 330);
		tsTeam.setLocation(10, 552);
		
		tsLogo = new JLabel();
		tsLogo.setFont(Alagard);
		tsLogo.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 40));
		tsLogo.setForeground(Color.WHITE);
		tsLogo.setText("Dungeons,\n Mazes and Forests");
		tsLogo.setSize(1024, 40);
		tsLogo.setLocation(230, 230);

		tsLogoShadow = new JLabel();
		tsLogoShadow.setFont(Alagard);
		tsLogoShadow.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 40));
		tsLogoShadow.setForeground(Color.DARK_GRAY);
		tsLogoShadow.setText("Dungeons,\n Mazes and Forests");
		tsLogoShadow.setSize(1024, 40);
		tsLogoShadow.setLocation(233, 233);

		tsNewGame = new JButton();
		tsNewGame.setSize(250, 20);
		tsNewGame.setText("New Game");
		tsNewGame.setFont(Alagard);
		tsNewGame.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 20));
		tsNewGame.setBackground(Color.DARK_GRAY);
		tsNewGame.setForeground(Color.WHITE);
		tsNewGame.setLocation(380, 330);
		tsNewGame.setBorderPainted(false);

		tsTutorial = new JButton();
		tsTutorial.setSize(250, 20);
		tsTutorial.setText("How to Play");
		tsTutorial.setFont(Alagard);
		tsTutorial.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 20));
		tsTutorial.setBackground(Color.DARK_GRAY);
		tsTutorial.setForeground(Color.WHITE);
		tsTutorial.setLocation(380, 370);
		tsTutorial.setBorderPainted(false);

		tsCredits = new JButton();
		tsCredits.setSize(250, 20);
		tsCredits.setText("Credits");
		tsCredits.setFont(Alagard);
		tsCredits.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 20));
		tsCredits.setBackground(Color.DARK_GRAY);
		tsCredits.setForeground(Color.WHITE);
		tsCredits.setLocation(380, 410);
		tsCredits.setBorderPainted(false);

		tsQuit = new JButton();
		tsQuit.setSize(250, 20);
		tsQuit.setText("Quit");
		tsQuit.setFont(Alagard);
		tsQuit.setFont(tsLogo.getFont().deriveFont(Font.ITALIC, 20));
		tsQuit.setBackground(Color.DARK_GRAY);
		tsQuit.setForeground(Color.WHITE);
		tsQuit.setLocation(380, 450);
		tsQuit.setBorderPainted(false);
		
		tsMusic = new Sound();
		playMusic(0);
			
		// Background
		add(tsBackground, JLayeredPane.DEFAULT_LAYER);

		// Buttons
		add(tsNewGame, JLayeredPane.MODAL_LAYER);
		add(tsTutorial, JLayeredPane.MODAL_LAYER);
		add(tsQuit, JLayeredPane.MODAL_LAYER);
		add(tsCredits, JLayeredPane.MODAL_LAYER);

		// Title
		add(tsTeam, JLayeredPane.DRAG_LAYER);
		add(tsInfo, JLayeredPane.DRAG_LAYER);
		add(tsLogoShadow, JLayeredPane.PALETTE_LAYER);
		add(tsLogo, JLayeredPane.MODAL_LAYER);
		
	}
	
	//Funciones Música
	public void playMusic(int i) {
		
		tsMusic.setFile(i);
		tsMusic.play();
		tsMusic.loop();
	}
	
	public void stopMusic(int i) {
		
		tsMusic.stop(i);
	}
	
	public void playSE(int i) {
		
		tsMusic.setFile(i);
		tsMusic.play();
	}

	//Getters & Setters
	public JButton getTsNewGame() {
		return tsNewGame;
	}

	public void setTsNewGame(JButton tsNewGame) {
		this.tsNewGame = tsNewGame;
	}

	public JButton getTsTutorial() {
		return tsTutorial;
	}

	public void setTsTutorial(JButton tsTutorial) {
		this.tsTutorial = tsTutorial;
	}

	public JButton getTsQuit() {
		return tsQuit;
	}

	public void setTsQuit(JButton tsQuit) {
		this.tsQuit = tsQuit;
	}

	public JButton getTsCredits() {
		return tsCredits;
	}

	public void setTsCredits(JButton tsCreadits) {
		this.tsCredits = tsCreadits;
	}

	public JLabel getTsLogo() {
		return tsLogo;
	}

	public void setTsLogo(JLabel tsLogo) {
		this.tsLogo = tsLogo;
	}

	public JLabel getTsBackground() {
		return tsBackground;
	}

	public void setTsBackground(JLabel tsBackground) {
		this.tsBackground = tsBackground;
	}

	public JLabel getTsLogoShadow() {
		return tsLogoShadow;
	}

	public void setTsLogoShadow(JLabel tsLogoShadow) {
		this.tsLogoShadow = tsLogoShadow;
	}
	
	public Sound getTsMusic() {
		return tsMusic;
	}

	public void setTsMusic(Sound tsMusic) {
		this.tsMusic = tsMusic;
	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

}
