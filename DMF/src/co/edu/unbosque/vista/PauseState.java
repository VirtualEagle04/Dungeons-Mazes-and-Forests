package co.edu.unbosque.vista;

import java.awt.Font;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PauseState extends JLayeredPane {

	private Font Alagard;
	private JPanel pause_panel;
	private JButton resume_button, options_button, back_button;
	private JLabel controls_text, controls_text_shadow, image_keys_wasd, image_keys_arrows;

	public PauseState() {

		// Inicialización y Empaquetamiento de la Fuente

		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//

		setLocation(0, 0);
		setSize(1024, 768);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		pause_panel = new JPanel(null);
		pause_panel.setSize(480, 768);
		pause_panel.setLocation(544, 0);
		pause_panel.setBackground(new Color(0, 0, 0, 140));

		resume_button = new JButton();
		resume_button.setBounds(80, 50, 320, 70);
		resume_button.setText("Resume Game");
		resume_button.setForeground(Color.white);
		resume_button.setFont(Alagard);
		resume_button.setFont(resume_button.getFont().deriveFont(Font.ITALIC, 25));
		resume_button.setBackground(Color.DARK_GRAY);

		options_button = new JButton();
		options_button.setBounds(80, 150, 320, 70);
		options_button.setText("Options");
		options_button.setForeground(Color.white);
		options_button.setFont(Alagard);
		options_button.setFont(resume_button.getFont().deriveFont(Font.ITALIC, 25));
		options_button.setBackground(Color.DARK_GRAY);

		back_button = new JButton();
		back_button.setBounds(80, 600, 320, 70);
		back_button.setText("Quit Game");
		back_button.setForeground(Color.white);
		back_button.setFont(Alagard);
		back_button.setFont(resume_button.getFont().deriveFont(Font.ITALIC, 25));
		back_button.setBackground(Color.DARK_GRAY);

		controls_text = new JLabel();
		controls_text.setText("Controls");
		controls_text.setFont(Alagard);
		controls_text.setFont(resume_button.getFont().deriveFont(Font.ITALIC, 25));
		controls_text.setForeground(Color.white);
		controls_text.setBounds(190, 260, 200, 25);

		controls_text_shadow = new JLabel();
		controls_text_shadow.setText("Controls");
		controls_text_shadow.setFont(Alagard);
		controls_text_shadow.setFont(resume_button.getFont().deriveFont(Font.ITALIC, 25));
		controls_text_shadow.setForeground(Color.DARK_GRAY);
		controls_text_shadow.setBounds(193, 263, 200, 25);

		image_keys_wasd = new JLabel(new ImageIcon("src/Assets/Images/keys_indicator_wasd.png"));
		image_keys_wasd.setBounds(140, 290, 192, 128);

		image_keys_arrows = new JLabel(new ImageIcon("src/Assets/Images/keys_indicator_arrows.png"));
		image_keys_arrows.setBounds(140, 438, 192, 128);

		pause_panel.add(resume_button);
		pause_panel.add(options_button);
		pause_panel.add(back_button);
		pause_panel.add(controls_text);
		pause_panel.add(controls_text_shadow);
		pause_panel.add(image_keys_arrows);
		pause_panel.add(image_keys_wasd);
		

		add(pause_panel, JLayeredPane.MODAL_LAYER);

	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public JPanel getPause_panel() {
		return pause_panel;
	}

	public void setPause_panel(JPanel pause_panel) {
		this.pause_panel = pause_panel;
	}

	public JButton getResume_button() {
		return resume_button;
	}

	public void setResume_button(JButton resume_button) {
		this.resume_button = resume_button;
	}

	public JButton getOptions_button() {
		return options_button;
	}

	public void setOptions_button(JButton options_button) {
		this.options_button = options_button;
	}

	public JButton getBack_button() {
		return back_button;
	}

	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
	}

}
