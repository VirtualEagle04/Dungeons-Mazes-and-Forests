package co.edu.unbosque.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.vista.GameFrame;
import co.edu.unbosque.vista.OptionsState;
import co.edu.unbosque.vista.TitleState;

public class Controlador implements ActionListener {

	GameFrame gameFrame;
	private TitleState ts;
	private OptionsState os;

	public Controlador() {
		gameFrame = new GameFrame();
		//ts = new TitleState();
		os = new OptionsState();
		agregarLectores();

	}

	public void agregarLectores() {
		ts.getTsNewGame().addActionListener(this);
		ts.getTsNewGame().setActionCommand("New Game");

		ts.getTsOptions().addActionListener(this);
		ts.getTsOptions().setActionCommand("Options");

	}

	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "New Game": {

			ts.setVisible(false);

		}
		case "Options": {

			ts.setVisible(false);
			os.setVisible(true);

		}
		default:
		}

	}
}
