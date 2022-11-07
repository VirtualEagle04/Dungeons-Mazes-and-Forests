package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	
	//States
	private TitleState tsState;
	private OptionsState opState;
	private CharacterState chState;
	private GameState gState;
	private PauseState pState;

	public GameFrame() {
		//GameFrame
		setTitle("Dungeons, Mazes and Forest");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		tsState = new TitleState();
		
		add(tsState);
		setVisible(true);
	}
	
}
