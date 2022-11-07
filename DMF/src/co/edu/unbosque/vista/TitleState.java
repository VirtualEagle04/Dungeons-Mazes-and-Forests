package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TitleState extends JLayeredPane{
	//Components
	private JButton tsNewGame, tsOptions, tsTutorial, tsQuit, tsCreadits;
	private JLabel tsLogo, tsBackground, tsLogoShadow;
	
	public TitleState() {
		setSize(1024, 768);
		setLocation(0,0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		tsBackground = new JLabel(new ImageIcon("src/Assets/tsBackgroundImage.gif"));
		tsBackground.setBounds(0,0, 1024, 768);
		
		tsLogo = new JLabel();
		tsLogo.setFont(new Font("Alagard", Font.ITALIC, 40));
		tsLogo.setForeground(Color.WHITE);
		tsLogo.setText("Dungeons,\n Mazes and Forests");
		tsLogo.setSize(1024, 40);
		tsLogo.setLocation(30,30);
		
		tsLogoShadow = new JLabel();
		tsLogoShadow.setFont(new Font("Alagard", Font.ITALIC, 40));
		tsLogoShadow.setForeground(Color.DARK_GRAY);
		tsLogoShadow.setText("Dungeons,\n Mazes and Forests");
		tsLogoShadow.setSize(1024, 40);
		tsLogoShadow.setLocation(33, 33);
		
		tsNewGame = new JButton();
		tsNewGame.setSize(250, 20);
		tsNewGame.setText("New Game");
		tsNewGame.setFont(new Font("Alagard", Font.ITALIC, 20));
		tsNewGame.setBackground(Color.DARK_GRAY);
		tsNewGame.setForeground(Color.WHITE);
		tsNewGame.setLocation(30, 150);
		tsNewGame.setBorderPainted(false);
		
		tsOptions = new JButton();
		tsOptions.setSize(250, 20);
		tsOptions.setText("Options");
		tsOptions.setFont(new Font("Alagard", Font.ITALIC, 20));
		tsOptions.setBackground(Color.DARK_GRAY);
		tsOptions.setForeground(Color.WHITE);
		tsOptions.setLocation(30, 190);
		tsOptions.setBorderPainted(false);
		
		tsTutorial = new JButton();
		tsTutorial.setSize(250, 20);
		tsTutorial.setText("How to Play");
		tsTutorial.setFont(new Font("Alagard", Font.ITALIC, 20));
		tsTutorial.setBackground(Color.DARK_GRAY);
		tsTutorial.setForeground(Color.WHITE);
		tsTutorial.setLocation(30, 230);
		tsTutorial.setBorderPainted(false);
		
		tsCreadits = new JButton();
		tsCreadits.setSize(250, 20);
		tsCreadits.setText("Credits");
		tsCreadits.setFont(new Font("Alagard", Font.ITALIC, 20));
		tsCreadits.setBackground(Color.DARK_GRAY);
		tsCreadits.setForeground(Color.WHITE);
		tsCreadits.setLocation(30, 270);
		tsCreadits.setBorderPainted(false);
		
		tsQuit = new JButton();
		tsQuit.setSize(250, 20);
		tsQuit.setText("Quit");
		tsQuit.setFont(new Font("Alagard", Font.ITALIC, 20));
		tsQuit.setBackground(Color.DARK_GRAY);
		tsQuit.setForeground(Color.WHITE);
		tsQuit.setLocation(30, 310);
		tsQuit.setBorderPainted(false);
		
		
		
		//Background
		add(tsBackground, JLayeredPane.DEFAULT_LAYER);
		
		//Buttons
		add(tsNewGame, JLayeredPane.MODAL_LAYER);
		add(tsOptions, JLayeredPane.MODAL_LAYER);
		add(tsTutorial, JLayeredPane.MODAL_LAYER);
		add(tsQuit, JLayeredPane.MODAL_LAYER);
		add(tsCreadits, JLayeredPane.MODAL_LAYER);
		
		//Title
		add(tsLogoShadow, JLayeredPane.PALETTE_LAYER);
		add(tsLogo, JLayeredPane.MODAL_LAYER);
	}

	//Getters & Setters
	public JButton getTsNewGame() {
		return tsNewGame;
	}

	public void setTsNewGame(JButton tsNewGame) {
		this.tsNewGame = tsNewGame;
	}

	public JButton getTsOptions() {
		return tsOptions;
	}

	public void setTsOptions(JButton tsOptions) {
		this.tsOptions = tsOptions;
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

	public JButton getTsCreadits() {
		return tsCreadits;
	}

	public void setTsCreadits(JButton tsCreadits) {
		this.tsCreadits = tsCreadits;
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
	
	
}
