package co.edu.unbosque.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.vista.CharacterState;
import co.edu.unbosque.vista.CreditsState;
import co.edu.unbosque.vista.GameFrame;
import co.edu.unbosque.vista.GameState;
import co.edu.unbosque.vista.OptionsState;
import co.edu.unbosque.vista.PauseState;
import co.edu.unbosque.vista.TitleState;
import co.edu.unbosque.vista.TutorialState;

public class Controlador implements ActionListener {

	GameFrame gameFrame;
	private TitleState tsState = new TitleState();
	private OptionsState opState = new OptionsState();
	private CharacterState chState = new CharacterState();
	private GameState gState = new GameState();
	private PauseState pState = new PauseState();
	private TutorialState tuState = new TutorialState();
	private CreditsState crState = new CreditsState();

	public Controlador() {
		gameFrame = new GameFrame();
		
		gameFrame.add(tsState);
		gameFrame.add(opState).setVisible(false);
		gameFrame.add(chState).setVisible(false);
		gameFrame.add(gState).setVisible(false);
		gameFrame.add(pState).setVisible(false);
		gameFrame.add(tuState).setVisible(false);
		gameFrame.add(crState).setVisible(false);
		agregarLectores();

	}

	public void agregarLectores() {
		
		tsState.getTsNewGame().addActionListener(this);
		tsState.getTsNewGame().setActionCommand("new_game_button");
		
		tsState.getTsOptions().addActionListener(this);
		tsState.getTsOptions().setActionCommand("options_button");
		
		tsState.getTsTutorial().addActionListener(this);
		tsState.getTsTutorial().setActionCommand("tutorial_button");
		
		tsState.getTsCredits().addActionListener(this);
		tsState.getTsCredits().setActionCommand("credits_button");
		
		tsState.getTsQuit().addActionListener(this);
		tsState.getTsQuit().setActionCommand("quit_button");		
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "new_game_button": {
			tsState.setVisible(false);
			tsState.stopMusic();
			gState.setVisible(true);
			break;
		}
		case "options_button": {
			tsState.setVisible(false);
			tsState.stopMusic();
			opState.playMusic(1);

			opState.setVisible(true);
			break;
		}
		case "tutorial_button": {
			tsState.setVisible(false);
			tsState.stopMusic();

			tuState.setVisible(true);
			break;
		}
		case "credits_button": {
			tsState.setVisible(false);
			tsState.stopMusic();
			crState.playMusic(2);

			crState.setVisible(true);
			break;
		}
		case "quit_button": {
			System.exit(0);
			break;
		}
		default:
		}
	}
}
