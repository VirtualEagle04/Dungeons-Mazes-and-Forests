package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class OptionsState extends JLayeredPane {

	private JLabel opTitle,opTitleShadow,opBackground;
	private Sound OsMusic;
	
	public OptionsState() {
	setLocation(0,0);
	setSize(1024, 768);
	setLayout(null);
	setBackground(Color.DARK_GRAY);

	opTitle = new JLabel();
	opTitle.setFont(new Font("Alagard", Font.ITALIC, 40));
	opTitle.setForeground(Color.WHITE);
	opTitle.setText("Options");
	opTitle.setSize(1024, 40);
	opTitle.setLocation(30, 30);
	
	opTitleShadow = new JLabel();
	opTitleShadow.setFont(new Font("Alagard", Font.ITALIC, 40));
	opTitleShadow.setForeground(Color.DARK_GRAY);
	opTitleShadow.setText("Options");
	opTitleShadow.setSize(1024, 40);
	opTitleShadow.setLocation(33, 33);
	
	opBackground = new JLabel(new ImageIcon("src/Assets/si.gif"));
	opBackground.setLocation(0,0);
	opBackground.setSize(1024, 768);
	
	OsMusic = new Sound();
	
	setVisible(true);
	
	add(opTitle);
	add(opTitleShadow);
	add(opBackground);
	
	}

	//Getters & Setters
	public JLabel getOpTitle() {
		return opTitle;
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
