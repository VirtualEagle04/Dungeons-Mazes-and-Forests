package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	
	private JPanel titleState, pauseState, gameState, optionState, characterState;
	//TitleState Label
	private JLabel tsLogo;
	//TitleState 
	private JButton tsNewGame, tsOptions, tsTutorial, tsQuit;
	//PauseState
	private JButton psOptions, psCharacterSelect;
	//CharacterState
	private JButton csMage, csPaladin, csWarrior, csBarbarian, csArcher, csRogue;
	//OptionsState
	

	public GameFrame() {
		//GameFrame
		setTitle("Dungeons, Mazes and Forest");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		
		//Title State
		titleState = new JPanel();
		titleState.setSize(this.getSize());
		titleState.setBackground(Color.BLACK);
		titleState.setLayout(null);
		
			tsLogo = new JLabel();
			tsLogo.setFont(new Font("JetBrains Mono Light", Font.BOLD, 16));
			tsLogo.setForeground(Color.WHITE);
			tsLogo.setText("Dungeons, Mazes and Forest");
			tsLogo.setBounds(100, 100, 300, 100);
			titleState.add(tsLogo);
			
			tsNewGame = new JButton();
			tsNewGame.setFont(new Font("JetBrains Mono Light", Font.BOLD, 16));
			tsNewGame.setText("Nueva Partida");
			tsNewGame.setForeground(Color.BLACK);
			tsNewGame.setBounds(50, 200, 300, 50);
			titleState.add(tsNewGame);
			
			tsOptions = new JButton();
			tsOptions.setFont(new Font("JetBrains Mono Light", Font.BOLD, 16));
			tsOptions.setText("Opciones");
			tsOptions.setForeground(Color.BLACK);
			tsOptions.setBounds(50, 300, 300, 50);
			tsOptions.setIcon(new ImageIcon("src/co/edu/unbosque/vista/Suuuu.jpg"));
			titleState.add(tsOptions);
		
		
		
		add(titleState);
		setVisible(true);
	}
	
}
