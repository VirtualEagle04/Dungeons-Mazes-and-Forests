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
	
	//Getters & Setters
	public TitleState getTsState() {
		return tsState;
	}

	public void setTsState(TitleState tsState) {
		this.tsState = tsState;
	}

	public OptionsState getOpState() {
		return opState;
	}

	public void setOpState(OptionsState opState) {
		this.opState = opState;
	}

	public CharacterState getChState() {
		return chState;
	}

	public void setChState(CharacterState chState) {
		this.chState = chState;
	}

	public GameState getgState() {
		return gState;
	}

	public void setgState(GameState gState) {
		this.gState = gState;
	}

	public PauseState getpState() {
		return pState;
	}

	public void setpState(PauseState pState) {
		this.pState = pState;
	}
	
}
