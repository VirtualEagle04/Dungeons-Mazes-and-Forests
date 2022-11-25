package co.edu.unbosque.vista;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class GameFrame extends JFrame{
	
	private GameState gameState;
	private TitleState tsState;
	private OptionsState opState;
	private CharacterState chState;
	private PauseState pState;
	private TutorialState tuState;
	private CreditsState crState;
	private PreGameState prgState;

	public GameFrame() {
		tsState = new TitleState();
		opState = new OptionsState();
		chState = new CharacterState();
		pState = new PauseState();
		tuState = new TutorialState();
		crState = new CreditsState();
		prgState = new PreGameState();
		gameState = new GameState();
		
		
		
		//GameFrame
		setTitle("Dungeons, Mazes and Forest");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setFocusable(true);
		
		add(tsState).setVisible(true);
		add(opState).setVisible(false);
		add(chState).setVisible(false);
		add(pState, JLayeredPane.MODAL_LAYER);
		this.pState.setVisible(false);
		add(tuState).setVisible(false);
		add(crState).setVisible(false);
		add(prgState).setVisible(false);
		add(gameState).setVisible(false);

		setVisible(true);
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

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

	public PauseState getpState() {
		return pState;
	}

	public void setpState(PauseState pState) {
		this.pState = pState;
	}

	public TutorialState getTuState() {
		return tuState;
	}

	public void setTuState(TutorialState tuState) {
		this.tuState = tuState;
	}

	public CreditsState getCrState() {
		return crState;
	}

	public void setCrState(CreditsState crState) {
		this.crState = crState;
	}

	public PreGameState getPrgState() {
		return prgState;
	}

	public void setPrgState(PreGameState prgState) {
		this.prgState = prgState;
	}
	
	
	
}
