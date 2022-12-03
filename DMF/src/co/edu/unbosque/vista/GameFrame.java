package co.edu.unbosque.vista;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class GameFrame extends JFrame{
	
	private GameState gameState;
	private TitleState tsState;
	private CharacterState chState;
	private PauseState pState;
	private TutorialState tuState;
	private CreditsState crState;
	private PreGameState prgState;
	private EndState eState;

	public GameFrame() {
		tsState = new TitleState();
		chState = new CharacterState();
		pState = new PauseState();
		tuState = new TutorialState();
		crState = new CreditsState();
		prgState = new PreGameState();
		gameState = new GameState();
		eState = new EndState();
		
		
		
		//GameFrame
		setTitle("Dungeons, Mazes and Forest");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setFocusable(true);
		
		add(eState, JLayeredPane.DRAG_LAYER);
		this.eState.setVisible(true);
		add(tsState).setVisible(true);
		add(chState).setVisible(false);
		add(pState, JLayeredPane.MODAL_LAYER);
		this.pState.setVisible(false);
		add(tuState).setVisible(false);
		add(crState).setVisible(false);
		add(prgState).setVisible(false);
		add(gameState).setVisible(false);

		setVisible(true);
	}

	public EndState geteState() {
		return eState;
	}

	public void seteState(EndState eState) {
		this.eState = eState;
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
