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
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class PauseState extends JLayeredPane {

	private Font Alagard;
	private JPanel pause_panel, panel_op, panel_tu;
	private JButton resume_button, options_button, back_button, instructions_button;
	private JLabel controls_text, controls_text_shadow, image_keys_wasd, image_keys_arrows, volume_op, volume_op_shadow,
			sfx_op, sfx_op_shadow;
	private JSlider music_volume, sfx_volume;
	private JTextArea game_info, enemys_info;

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

		// paneles

		pause_panel = new JPanel(null);
		pause_panel.setSize(480, 768);
		pause_panel.setLocation(544, 0);
		pause_panel.setBackground(new Color(0, 0, 0, 140));

		panel_op = new JPanel(null);
		panel_op.setBackground(new Color(0, 0, 0, 140));
		panel_op.setSize(460, 430);
		panel_op.setLocation(40, 150);
		panel_op.setVisible(false);

		panel_tu = new JPanel(null);
		panel_tu.setBackground(new Color(0, 0, 0, 140));
		panel_tu.setSize(460, 480);
		panel_tu.setLocation(40, 120);
		panel_tu.setVisible(false);

		// sliders

		music_volume = new JSlider();
		music_volume.setBounds(70, 70, 320, 20);
		music_volume.setBackground(new Color(0, 0, 0, 180));

		sfx_volume = new JSlider();
		sfx_volume.setBounds(70, 170, 320, 20);
		sfx_volume.setBackground(new Color(0, 0, 0, 180));

		// options buttons

		instructions_button = new JButton();
		instructions_button.setText("Instructions");
		instructions_button.setFont(Alagard);
		instructions_button.setFont(instructions_button.getFont().deriveFont(Font.ITALIC, 25));
		instructions_button.setBounds(70, 310, 320, 70);
		instructions_button.setBackground(Color.DARK_GRAY);
		instructions_button.setForeground(Color.white);

		// options information

		volume_op = new JLabel();
		volume_op.setText("Music Volume");
		volume_op.setBounds(150, 30, 200, 20);
		volume_op.setFont(Alagard);
		volume_op.setFont(volume_op.getFont().deriveFont(Font.ITALIC, 25));
		volume_op.setForeground(Color.white);

		volume_op_shadow = new JLabel();
		volume_op_shadow.setText("Music Volume");
		volume_op_shadow.setBounds(153, 33, 200, 20);
		volume_op_shadow.setFont(Alagard);
		volume_op_shadow.setFont(volume_op_shadow.getFont().deriveFont(Font.ITALIC, 25));

		sfx_op = new JLabel();
		sfx_op.setText("Effects Volume");
		sfx_op.setBounds(150, 130, 200, 20);
		sfx_op.setFont(Alagard);
		sfx_op.setFont(sfx_op.getFont().deriveFont(Font.ITALIC, 25));
		sfx_op.setForeground(Color.white);

		sfx_op_shadow = new JLabel();
		sfx_op_shadow.setText("Effects Volume");
		sfx_op_shadow.setBounds(153, 133, 200, 20);
		sfx_op_shadow.setFont(Alagard);
		sfx_op_shadow.setFont(sfx_op_shadow.getFont().deriveFont(Font.ITALIC, 25));

		// instructions information

		enemys_info = new JTextArea();
		enemys_info.setText(
				"|There are two tipes of enemys, take care|\n\n" + "|The Lethal Assasin will kill you inmediately|\n\n"
						+ "| The Sthormy only reduces your movements,\ntake care of him|\n\n"
						+ "| if you see the Lethal Assasin and is next to\n you, GAME OVER :(|\n\n"
						+ "| and if you see the sthormy and is next to\nyou, movements -5% >:(|\n\n");
		enemys_info.setFont(Alagard);
		enemys_info.setFont(enemys_info.getFont().deriveFont(Font.PLAIN, 21));
		enemys_info.setBounds(8, 8, 537, 290);
		enemys_info.setForeground(Color.white);
		enemys_info.setBackground(new Color(0, 0, 0, 0));
		enemys_info.setEditable(false);

		game_info = new JTextArea();
		game_info.setText("|Take the keys to complete the Game|\n\n" + "| if you don't have all the keys, you"
				+ " can't\nfinish the laberynth, take all|\n\n" + "| your movementes are limited and the\n"
				+ "cuantity of your movements is the" + " size you\nenter before start to play|\n\n");
		game_info.setFont(Alagard);
		game_info.setFont(game_info.getFont().deriveFont(Font.PLAIN, 21));
		game_info.setForeground(Color.white);
		game_info.setEditable(false);
		game_info.setBounds(8, 300, 537, 190);
		game_info.setBackground(new Color(0, 0, 0, 0));

		// principal options

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
		back_button.setText("Back to menu");
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

		// pause panel
		pause_panel.add(resume_button);
		pause_panel.add(options_button);
		pause_panel.add(back_button);
		pause_panel.add(controls_text);
		pause_panel.add(controls_text_shadow);
		pause_panel.add(image_keys_arrows);
		pause_panel.add(image_keys_wasd);

		// options panel
		panel_op.add(music_volume);
		panel_op.add(sfx_volume);
		panel_op.add(instructions_button);
		panel_op.add(volume_op, JLayeredPane.MODAL_LAYER);
		panel_op.add(volume_op_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_op.add(sfx_op, JLayeredPane.MODAL_LAYER);
		panel_op.add(sfx_op_shadow, JLayeredPane.DEFAULT_LAYER);

		// indications panel
		panel_tu.add(enemys_info);
		panel_tu.add(game_info);

		// panels
		add(panel_tu, JLayeredPane.MODAL_LAYER);
		add(panel_op, JLayeredPane.MODAL_LAYER);
		add(pause_panel, JLayeredPane.MODAL_LAYER);

	}

	public JTextArea getGame_info() {
		return game_info;
	}

	public void setGame_info(JTextArea game_info) {
		this.game_info = game_info;
	}

	public JTextArea getEnemys_info() {
		return enemys_info;
	}

	public void setEnemys_info(JTextArea enemys_info) {
		this.enemys_info = enemys_info;
	}

	public JPanel getPanel_op() {
		return panel_op;
	}

	public void setPanel_op(JPanel panel_op) {
		this.panel_op = panel_op;
	}

	public JPanel getPanel_tu() {
		return panel_tu;
	}

	public void setPanel_tu(JPanel panel_tu) {
		this.panel_tu = panel_tu;
	}

	public JButton getInstructions_button() {
		return instructions_button;
	}

	public void setInstructions_button(JButton instructions_button) {
		this.instructions_button = instructions_button;
	}

	public JLabel getControls_text() {
		return controls_text;
	}

	public void setControls_text(JLabel controls_text) {
		this.controls_text = controls_text;
	}

	public JLabel getControls_text_shadow() {
		return controls_text_shadow;
	}

	public void setControls_text_shadow(JLabel controls_text_shadow) {
		this.controls_text_shadow = controls_text_shadow;
	}

	public JLabel getImage_keys_wasd() {
		return image_keys_wasd;
	}

	public void setImage_keys_wasd(JLabel image_keys_wasd) {
		this.image_keys_wasd = image_keys_wasd;
	}

	public JLabel getImage_keys_arrows() {
		return image_keys_arrows;
	}

	public void setImage_keys_arrows(JLabel image_keys_arrows) {
		this.image_keys_arrows = image_keys_arrows;
	}

	public JLabel getVolume_op() {
		return volume_op;
	}

	public void setVolume_op(JLabel volume_op) {
		this.volume_op = volume_op;
	}

	public JLabel getVolume_op_shadow() {
		return volume_op_shadow;
	}

	public void setVolume_op_shadow(JLabel volume_op_shadow) {
		this.volume_op_shadow = volume_op_shadow;
	}

	public JLabel getSfx_op() {
		return sfx_op;
	}

	public void setSfx_op(JLabel sfx_op) {
		this.sfx_op = sfx_op;
	}

	public JLabel getSfx_op_shadow() {
		return sfx_op_shadow;
	}

	public void setSfx_op_shadow(JLabel sfx_op_shadow) {
		this.sfx_op_shadow = sfx_op_shadow;
	}

	public JSlider getMusic_volume() {
		return music_volume;
	}

	public void setMusic_volume(JSlider music_volume) {
		this.music_volume = music_volume;
	}

	public JSlider getSfx_volume() {
		return sfx_volume;
	}

	public void setSfx_volume(JSlider sfx_volume) {
		this.sfx_volume = sfx_volume;
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
