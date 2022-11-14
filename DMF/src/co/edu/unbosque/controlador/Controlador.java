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
		// Title Buttons
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

		// Character Select Buttons
		chState.getCsMage().addActionListener(this);
		chState.getCsMage().setActionCommand("csMage");

		chState.getCsBarbarian().addActionListener(this);
		chState.getCsBarbarian().setActionCommand("csBarbarian");

		chState.getCsArcher().addActionListener(this);
		chState.getCsArcher().setActionCommand("csArcher");

		chState.getCsPaladin().addActionListener(this);
		chState.getCsPaladin().setActionCommand("csPaladin");

		chState.getCsRogue().addActionListener(this);
		chState.getCsRogue().setActionCommand("csRogue");

		chState.getCsWarrior().addActionListener(this);
		chState.getCsWarrior().setActionCommand("csWarrior");
		// Back buttons
		chState.getBack_button().addActionListener(this);
		chState.getBack_button().setActionCommand("cs_back_button");

		opState.getBack_button().addActionListener(this);
		opState.getBack_button().setActionCommand("op_back_button");

		crState.getBack_button().addActionListener(this);
		crState.getBack_button().setActionCommand("cr_back_button");
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "new_game_button": {
			tsState.setVisible(false);
			tsState.stopMusic();
			chState.playMusic(3);
			chState.setVisible(true);

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
		case "csMage": {

			chState.getMage_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getWarrior_concept_art().setVisible(false);
			chState.getPaladin_concept_art().setVisible(false);
			chState.getRogue_concept_art().setVisible(false);
			chState.getArcher_concept_art().setVisible(false);
			chState.getBarbarian_concept_art().setVisible(false);

			break;
		}
		case "csWarrior": {

			chState.getWarrior_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getPaladin_concept_art().setVisible(false);
			chState.getRogue_concept_art().setVisible(false);
			chState.getArcher_concept_art().setVisible(false);
			chState.getBarbarian_concept_art().setVisible(false);
			chState.getMage_concept_art().setVisible(false);

			break;
		}
		case "csPaladin": {

			chState.getPaladin_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getMage_concept_art().setVisible(false);
			chState.getRogue_concept_art().setVisible(false);
			chState.getArcher_concept_art().setVisible(false);
			chState.getBarbarian_concept_art().setVisible(false);
			chState.getWarrior_concept_art().setVisible(false);

			break;
		}
		case "csRogue": {

			chState.getRogue_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getMage_concept_art().setVisible(false);
			chState.getArcher_concept_art().setVisible(false);
			chState.getBarbarian_concept_art().setVisible(false);
			chState.getWarrior_concept_art().setVisible(false);
			chState.getPaladin_concept_art().setVisible(false);

			break;
		}
		case "csArcher": {

			chState.getArcher_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getMage_concept_art().setVisible(false);
			chState.getBarbarian_concept_art().setVisible(false);
			chState.getWarrior_concept_art().setVisible(false);
			chState.getPaladin_concept_art().setVisible(false);
			chState.getRogue_concept_art().setVisible(false);

			break;
		}
		case "csBarbarian": {
			chState.getBarbarian_concept_art().setVisible(true);
			chState.getPanel_text().setVisible(false);
			chState.getPanel_textShadow().setVisible(false);
			chState.getMage_concept_art().setVisible(false);
			chState.getWarrior_concept_art().setVisible(false);
			chState.getPaladin_concept_art().setVisible(false);
			chState.getRogue_concept_art().setVisible(false);
			chState.getArcher_concept_art().setVisible(false);

			break;
		}
		case "cs_back_button": {

			chState.setVisible(false);
			tsState.setVisible(true);
			chState.stopMusic();
			tsState.playMusic(0);

			break;
		}
		case "cr_back_button": {

			crState.setVisible(false);
			tsState.setVisible(true);
			crState.stopMusic();
			tsState.playMusic(0);

			break;
		}
		case "op_back_button": {

			opState.setVisible(false);
			tsState.setVisible(true);
			opState.stopMusic();
			tsState.playMusic(0);

			break;
		}

		default:
		}
	}
}
