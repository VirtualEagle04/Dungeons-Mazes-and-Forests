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
import javax.swing.JTextArea;

public class TutorialState extends JLayeredPane {

	private Sound tuMusic;
	private JButton back_button;
	private JLabel tuBackground, tutorial_indications, key_tutorial, tuTitle, tuTitle_shadow, arrow_key, keys_indicator,
			wasd_indicator, lethal_indicator, sthormy_indicator, tuCharacters, move_title, move_title_shadow,
			enemy_title, enemy_title_shadow, game_title, game_title_shadow;
	private JTextArea movement_info, enemys_info, game_info;
	private JPanel panel_ch;
	private Font Alagard;
	private String sex;

	public TutorialState() {

		// Inicialización y Empaquetamiento de la Fuente

		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//

		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		// Title and back button

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		tuTitle = new JLabel();
		tuTitle.setText("How to play");
		tuTitle.setFont(Alagard);
		tuTitle.setFont(tuTitle.getFont().deriveFont(Font.ITALIC, 40));
		tuTitle.setForeground(Color.white);
		tuTitle.setBounds(414, 20, 230, 40);

		tuTitle_shadow = new JLabel();
		tuTitle_shadow.setText("How to play");
		tuTitle_shadow.setFont(Alagard);
		tuTitle_shadow.setFont(tuTitle.getFont().deriveFont(Font.ITALIC, 40));
		tuTitle_shadow.setForeground(Color.DARK_GRAY);
		tuTitle_shadow.setBounds(414, 23, 230, 40);

		// Background

		tuBackground = new JLabel(new ImageIcon("src/Assets/Gifs/tuBackground.gif"));
		tuBackground.setLocation(0, 0);
		tuBackground.setSize(1024, 768);

		// tutorial sprite

		key_tutorial = new JLabel(new ImageIcon("src/Assets/Gifs/tutorial_key.gif"));
		key_tutorial.setLocation(556, 150);
		key_tutorial.setSize(100, 143);

		keys_indicator = new JLabel(new ImageIcon("src/Assets/Images/tuKeys_arrows.png"));
		keys_indicator.setBounds(30, 150, 96, 64);

		wasd_indicator = new JLabel(new ImageIcon("src/Assets/Images/tuKeys_wasd.png"));
		wasd_indicator.setBounds(30, 275, 96, 64);

		lethal_indicator = new JLabel(new ImageIcon("src/Assets/Gifs/tuSprite_lethal.gif"));
		lethal_indicator.setBounds(580, 420, 64, 64);

		sthormy_indicator = new JLabel(new ImageIcon("src/Assets/Gifs/tuSprite_sthormy.gif"));
		sthormy_indicator.setBounds(580, 555, 64, 64);

		tuCharacters = new JLabel(new ImageIcon("src/Assets/Gifs/tuSprites.gif"));
		tuCharacters.setBounds(0, 0, 288, 48);

		panel_ch = new JPanel();
		panel_ch.setBackground(new Color(96, 235, 97, 250));
		panel_ch.setBounds(675, 460, 288, 48);

		// text information

		movement_info = new JTextArea();
		movement_info.setText("|Press any keys of they to move in the laberynth|\n\n"
				+ "|Use KeyW or ArrowUP to move UP|\n\n" + "|Use keyA or ArrowLeft to move to the left|\n\n"
				+ "|Use KeyS or ArrowDown to move down|\n\n" + "|Use KeyD or ArrowRight to move to the right|");
		movement_info.setFont(Alagard);
		movement_info.setFont(movement_info.getFont().deriveFont(Font.PLAIN, 18));
		movement_info.setBackground(new Color(0, 0, 0, 120));
		movement_info.setForeground(Color.white);
		movement_info.setBounds(136, 150, 415, 190);
		movement_info.setEditable(false);

		enemys_info = new JTextArea();
		enemys_info.setText(
				"|There are two tipes of enemys, take care|\n\n" + "|The Lethal Assasin will kill you inmediately|\n\n"
						+ "|The Sthormy only reduces your movements, take care of him|\n\n"
						+ "|if you see the Lethal Assasin and is next to you, GAME OVER|\n\n"
						+ "|and if you see the sthormy and is next to you, movements -5%|\n\n");
		enemys_info.setFont(Alagard);
		enemys_info.setFont(enemys_info.getFont().deriveFont(Font.PLAIN, 18));
		enemys_info.setBounds(30, 420, 537, 200);
		enemys_info.setForeground(Color.white);
		enemys_info.setBackground(new Color(0, 0, 0, 120));
		enemys_info.setEditable(false);

		game_info = new JTextArea();
		game_info.setText("|Take the keys to complete the Game|\n\n" + "|if you don't have all the keys, you\n"
				+ " can't finish the laberynth, take all|\n\n" + "|your movementes are limited and the\n"
				+ " cuantity of your movements is the\n" + " size you enter before start to play|\n\n"
				+ "|you can select one of the six \n" + "characters, look stronger with your \n"
				+ "favourite character >:)|\n\n" + "They are the characters that you can \n" + "select:\n\n\n\n\n\n\n"
				+ "|Exist six class of characters: archer\n" + "warrior, barbarian, mage, paladin and \n" + "rogue|");
		game_info.setFont(Alagard);
		game_info.setFont(game_info.getFont().deriveFont(Font.PLAIN, 18));
		game_info.setForeground(Color.white);
		game_info.setEditable(false);
		game_info.setBounds(660, 150, 320, 470);
		game_info.setBackground(new Color(0, 0, 0, 120));

		// information titles

		enemy_title = new JLabel();
		enemy_title.setText("Enemys");
		enemy_title.setFont(Alagard);
		enemy_title.setFont(enemy_title.getFont().deriveFont(Font.PLAIN, 23));
		enemy_title.setBounds(260, 390, 140, 21);
		enemy_title.setForeground(Color.white);
		
		enemy_title_shadow = new JLabel();
		enemy_title_shadow.setText("Enemys");
		enemy_title_shadow.setFont(Alagard);
		enemy_title_shadow.setFont(enemy_title.getFont().deriveFont(Font.PLAIN, 23));
		enemy_title_shadow.setBounds(263, 393, 140, 21);
		enemy_title_shadow.setForeground(Color.DARK_GRAY);

		move_title = new JLabel();
		move_title.setText("Movement");
		move_title.setFont(Alagard);
		move_title.setFont(move_title.getFont().deriveFont(Font.PLAIN, 23));
		move_title.setBounds(280, 120, 140, 23);
		move_title.setForeground(Color.white);
		
		move_title_shadow = new JLabel();
		move_title_shadow.setText("Movement");
		move_title_shadow.setFont(Alagard);
		move_title_shadow.setFont(move_title.getFont().deriveFont(Font.PLAIN, 23));
		move_title_shadow.setBounds(283, 123, 140, 23);
		move_title_shadow.setForeground(Color.DARK_GRAY);

		game_title = new JLabel();
		game_title.setText("Game info");
		game_title.setFont(Alagard);
		game_title.setFont(game_title.getFont().deriveFont(Font.PLAIN, 23));
		game_title.setForeground(Color.white);
		game_title.setBounds(760, 120, 140, 23);
		
		game_title_shadow = new JLabel();
		game_title_shadow.setText("Game info");
		game_title_shadow.setFont(Alagard);
		game_title_shadow.setFont(game_title.getFont().deriveFont(Font.PLAIN, 23));
		game_title_shadow.setForeground(Color.DARK_GRAY);
		game_title_shadow.setBounds(763, 123, 140, 23);

	
		//add titles
		add(game_title);
		add(game_title_shadow);
		add(move_title);
		add(move_title_shadow);
		add(enemy_title);
		add(enemy_title_shadow);
		add(tuTitle);
		add(tuTitle_shadow);
		
		//information
		add(panel_ch);
		add(enemys_info);
		add(game_info);
		add(movement_info);
		
		//images & gifs
		panel_ch.add(tuCharacters);
		add(sthormy_indicator);
		add(lethal_indicator);
		add(keys_indicator);
		add(wasd_indicator);
		add(key_tutorial);
		add(tuBackground, JLayeredPane.DEFAULT_LAYER);
		
		//back button
		add(back_button, JLayeredPane.MODAL_LAYER);

		tuMusic = new Sound();

	}

	public JLabel getTuCharacters() {
		return tuCharacters;
	}



	public void setTuCharacters(JLabel tuCharacters) {
		this.tuCharacters = tuCharacters;
	}



	public JLabel getMove_title() {
		return move_title;
	}



	public void setMove_title(JLabel move_title) {
		this.move_title = move_title;
	}



	public JLabel getMove_title_shadow() {
		return move_title_shadow;
	}



	public void setMove_title_shadow(JLabel move_title_shadow) {
		this.move_title_shadow = move_title_shadow;
	}



	public JLabel getEnemy_title() {
		return enemy_title;
	}



	public void setEnemy_title(JLabel enemy_title) {
		this.enemy_title = enemy_title;
	}



	public JLabel getEnemy_title_shadow() {
		return enemy_title_shadow;
	}



	public void setEnemy_title_shadow(JLabel enemy_title_shadow) {
		this.enemy_title_shadow = enemy_title_shadow;
	}



	public JLabel getGame_title() {
		return game_title;
	}



	public void setGame_title(JLabel game_title) {
		this.game_title = game_title;
	}



	public JLabel getGame_title_shadow() {
		return game_title_shadow;
	}



	public void setGame_title_shadow(JLabel game_title_shadow) {
		this.game_title_shadow = game_title_shadow;
	}



	public JPanel getPanel_ch() {
		return panel_ch;
	}



	public void setPanel_ch(JPanel panel_ch) {
		this.panel_ch = panel_ch;
	}



	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public JTextArea getMovement_info() {
		return movement_info;
	}

	public void setMovement_info(JTextArea movement_info) {
		this.movement_info = movement_info;
	}

	public JTextArea getEnemys_info() {
		return enemys_info;
	}

	public void setEnemys_info(JTextArea enemys_info) {
		this.enemys_info = enemys_info;
	}

	public JTextArea getGame_info() {
		return game_info;
	}

	public void setGame_info(JTextArea game_info) {
		this.game_info = game_info;
	}

	public JLabel getTuBackground() {
		return tuBackground;
	}

	public void setTuBackground(JLabel tuBackground) {
		this.tuBackground = tuBackground;
	}

	public JLabel getTutorial_indications() {
		return tutorial_indications;
	}

	public void setTutorial_indications(JLabel tutorial_indications) {
		this.tutorial_indications = tutorial_indications;
	}

	public JLabel getKey_tutorial() {
		return key_tutorial;
	}

	public void setKey_tutorial(JLabel key_tutorial) {
		this.key_tutorial = key_tutorial;
	}

	public JLabel getTuTitle() {
		return tuTitle;
	}

	public void setTuTitle(JLabel tuTitle) {
		this.tuTitle = tuTitle;
	}

	public JLabel getTuTitle_shadow() {
		return tuTitle_shadow;
	}

	public void setTuTitle_shadow(JLabel tuTitle_shadow) {
		this.tuTitle_shadow = tuTitle_shadow;
	}

	public JLabel getArrow_key() {
		return arrow_key;
	}

	public void setArrow_key(JLabel arrow_key) {
		this.arrow_key = arrow_key;
	}

	public JLabel getKeys_indicator() {
		return keys_indicator;
	}

	public void setKeys_indicator(JLabel keys_indicator) {
		this.keys_indicator = keys_indicator;
	}

	public JLabel getWasd_indicator() {
		return wasd_indicator;
	}

	public void setWasd_indicator(JLabel wasd_indicator) {
		this.wasd_indicator = wasd_indicator;
	}

	public JLabel getLethal_indicator() {
		return lethal_indicator;
	}

	public void setLethal_indicator(JLabel lethal_indicator) {
		this.lethal_indicator = lethal_indicator;
	}

	public JLabel getSthormy_indicator() {
		return sthormy_indicator;
	}

	public void setSthormy_indicator(JLabel sthormy_indicator) {
		this.sthormy_indicator = sthormy_indicator;
	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public Sound getTuMusic() {
		return tuMusic;
	}

	public void setTuMusic(Sound tuMusic) {
		this.tuMusic = tuMusic;
	}

	public JButton getBack_button() {
		return back_button;
	}

	public void setBack_button(JButton back_button) {
		this.back_button = back_button;
	}

	public void playMusic(int i) {

		tuMusic.setFile(i);
		tuMusic.play();
		tuMusic.loop();
	}

	public void stopMusic() {

		tuMusic.stop();
	}

}
