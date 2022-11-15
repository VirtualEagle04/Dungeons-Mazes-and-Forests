package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class OptionsState extends JLayeredPane {

	private JLabel opTitle, opTitleShadow, opBackground, txMusic_volume, txMusic_volume_shadow, txEffects_volume,
			txEffects_volume_shadow, screen_resize, screen_resize_shadow;
	private JPanel options_panel;
	private Sound OsMusic;
	private Font Alagard;
	private JButton back_button, btSize_screen, btDefault_screen;
	private JSlider music_volume, sfx_volume;

	public OptionsState() {

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

		// Options Panel

		options_panel = new JPanel(null);
		options_panel.setSize(480, 768);
		options_panel.setLocation(544, 0);
		options_panel.setBackground(new Color(0, 0, 0, 180));

		// Text indicator

		txMusic_volume = new JLabel();
		txMusic_volume.setText("Music Volume");
		txMusic_volume.setBounds(150, 130, 200, 20);
		txMusic_volume.setFont(Alagard);
		txMusic_volume.setFont(txMusic_volume.getFont().deriveFont(Font.ITALIC, 25));
		txMusic_volume.setForeground(Color.white);

		txMusic_volume_shadow = new JLabel();
		txMusic_volume_shadow.setText("Music Volume");
		txMusic_volume_shadow.setBounds(153, 133, 200, 20);
		txMusic_volume_shadow.setFont(Alagard);
		txMusic_volume_shadow.setFont(txMusic_volume_shadow.getFont().deriveFont(Font.ITALIC, 25));

		txEffects_volume = new JLabel();
		txEffects_volume.setText("Effects Volume");
		txEffects_volume.setBounds(140, 210, 200, 20);
		txEffects_volume.setFont(Alagard);
		txEffects_volume.setFont(txEffects_volume.getFont().deriveFont(Font.ITALIC, 25));
		txEffects_volume.setForeground(Color.white);

		txEffects_volume_shadow = new JLabel();
		txEffects_volume_shadow.setText("Effects Volume");
		txEffects_volume_shadow.setBounds(143, 213, 200, 20);
		txEffects_volume_shadow.setFont(Alagard);
		txEffects_volume_shadow.setFont(txEffects_volume_shadow.getFont().deriveFont(Font.ITALIC, 25));

		screen_resize = new JLabel();
		screen_resize.setText("Screen size");
		screen_resize.setBounds(170, 550, 200, 20);
		screen_resize.setFont(Alagard);
		screen_resize.setFont(txMusic_volume.getFont().deriveFont(Font.ITALIC, 25));
		screen_resize.setForeground(Color.white);

		screen_resize_shadow = new JLabel();
		screen_resize_shadow.setText("Screen size");
		screen_resize_shadow.setBounds(173, 553, 200, 20);
		screen_resize_shadow.setFont(Alagard);
		screen_resize_shadow.setFont(txMusic_volume.getFont().deriveFont(Font.ITALIC, 25));

		// Volume sliders

		music_volume = new JSlider();
		music_volume.setBounds(70, 170, 320, 20);
		music_volume.setBackground(new Color(0, 0, 0, 180));

		sfx_volume = new JSlider();
		sfx_volume.setBounds(70, 250, 320, 20);
		sfx_volume.setBackground(new Color(0, 0, 0, 180));

		// Buttons

		btSize_screen = new JButton();
		btSize_screen.setSize(190, 50);
		btSize_screen.setLocation(40, 590);
		btSize_screen.setBackground(Color.DARK_GRAY);
		btSize_screen.setText("Full Screen");
		btSize_screen.setFont(Alagard);
		btSize_screen.setFont(btSize_screen.getFont().deriveFont(Font.ITALIC, 25));
		btSize_screen.setForeground(Color.white);

		btDefault_screen = new JButton();
		btDefault_screen.setSize(190, 50);
		btDefault_screen.setLocation(240, 590);
		btDefault_screen.setBackground(Color.DARK_GRAY);
		btDefault_screen.setText("Default Size");
		btDefault_screen.setFont(Alagard);
		btDefault_screen.setFont(btSize_screen.getFont().deriveFont(Font.ITALIC, 25));
		btDefault_screen.setForeground(Color.white);

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		// Title

		opTitle = new JLabel();
		opTitle.setFont(Alagard);
		opTitle.setFont(opTitle.getFont().deriveFont(Font.ITALIC, 40));
		opTitle.setForeground(Color.WHITE);
		opTitle.setText("Options");
		opTitle.setSize(1024, 40);
		opTitle.setLocation(210, 30);

		opTitleShadow = new JLabel();
		opTitleShadow.setFont(Alagard);
		opTitleShadow.setFont(opTitleShadow.getFont().deriveFont(Font.ITALIC, 40));
		opTitleShadow.setForeground(Color.DARK_GRAY);
		opTitleShadow.setText("Options");
		opTitleShadow.setSize(1024, 40);
		opTitleShadow.setLocation(213, 33);

		// Background

		opBackground = new JLabel(new ImageIcon("src/Assets/Gifs/opBackground.gif"));
		opBackground.setLocation(0, 0);
		opBackground.setSize(1024, 768);

		OsMusic = new Sound();

		setVisible(true);

		options_panel.add(music_volume, JLayeredPane.MODAL_LAYER);
		options_panel.add(sfx_volume, JLayeredPane.MODAL_LAYER);
		options_panel.add(txMusic_volume, JLayeredPane.MODAL_LAYER);
		options_panel.add(txMusic_volume_shadow, JLayeredPane.DEFAULT_LAYER);
		options_panel.add(txEffects_volume, JLayeredPane.MODAL_LAYER);
		options_panel.add(txEffects_volume_shadow, JLayeredPane.DEFAULT_LAYER);
		options_panel.add(screen_resize, JLayeredPane.MODAL_LAYER);
		options_panel.add(screen_resize_shadow, JLayeredPane.DEFAULT_LAYER);
		options_panel.add(btDefault_screen, JLayeredPane.MODAL_LAYER);
		options_panel.add(btSize_screen, JLayeredPane.MODAL_LAYER);

		add(options_panel);
		add(back_button);
		add(opTitle);
		add(opTitleShadow);
		add(opBackground, JLayeredPane.DEFAULT_LAYER);

	}

	// Getters & Setters

	public JLabel getOpTitle() {
		return opTitle;
	}

	public JLabel getTxMusic_volume() {
		return txMusic_volume;
	}

	public void setTxMusic_volume(JLabel txMusic_volume) {
		this.txMusic_volume = txMusic_volume;
	}

	public JLabel getTxMusic_volume_shadow() {
		return txMusic_volume_shadow;
	}

	public void setTxMusic_volume_shadow(JLabel txMusic_volume_shadow) {
		this.txMusic_volume_shadow = txMusic_volume_shadow;
	}

	public JLabel getTxEffects_volume() {
		return txEffects_volume;
	}

	public void setTxEffects_volume(JLabel txEffects_volume) {
		this.txEffects_volume = txEffects_volume;
	}

	public JLabel getTxEffects_volume_shadow() {
		return txEffects_volume_shadow;
	}

	public void setTxEffects_volume_shadow(JLabel txEffects_volume_shadow) {
		this.txEffects_volume_shadow = txEffects_volume_shadow;
	}

	public JLabel getScreen_resize() {
		return screen_resize;
	}

	public void setScreen_resize(JLabel screen_resize) {
		this.screen_resize = screen_resize;
	}

	public JLabel getScreen_resize_shadow() {
		return screen_resize_shadow;
	}

	public void setScreen_resize_shadow(JLabel screen_resize_shadow) {
		this.screen_resize_shadow = screen_resize_shadow;
	}

	public JPanel getOptions_panel() {
		return options_panel;
	}

	public void setOptions_panel(JPanel options_panel) {
		this.options_panel = options_panel;
	}

	public JButton getBtSize_screen() {
		return btSize_screen;
	}

	public void setBtSize_screen(JButton btSize_screen) {
		this.btSize_screen = btSize_screen;
	}

	public JButton getBtDefault_screen() {
		return btDefault_screen;
	}

	public void setBtDefault_screen(JButton btDefault_screen) {
		this.btDefault_screen = btDefault_screen;
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

	public Sound getOsMusic() {
		return OsMusic;
	}

	public void setOsMusic(Sound osMusic) {
		OsMusic = osMusic;
	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public JButton getBack_button() {
		return back_button;
	}

	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
	}

	public void setOpTitle(JLabel opTitle) {
		this.opTitle = opTitle;
	}

	public JLabel getOpTitleShadow() {
		return opTitleShadow;
	}

	public void setOpTitleShadow(JLabel opTitleShadow) {
		this.opTitleShadow = opTitleShadow;
	}

	public JLabel getOpBackground() {
		return opBackground;
	}

	public void setOpBackground(JLabel opBackground) {
		this.opBackground = opBackground;
	}

	// Función Música
	public void playMusic(int i) {

		OsMusic.setFile(i);
		OsMusic.play();
		OsMusic.loop();
	}

	public void stopMusic() {

		OsMusic.stop();
	}

}
