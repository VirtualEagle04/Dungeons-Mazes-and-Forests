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
import javax.swing.JTextField;

import co.edu.unbosque.controlador.Controlador;

public class PreGameState extends JLayeredPane {

	private JButton back_button, start_button;
	private JLabel prgBackground, characterInf, characterInf_shadow, character_name, character_name_shadow, indicator,
			enemigos_indicador, llaves_indicador, prg_artBackground;
	private JLabel archer_concept_art, barbarian_concept_art, warrior_concept_art, rogue_concept_art,
			paladin_concept_art, mage_concept_art;
	private JPanel panel_inf;
	private JTextField entrada_X, entrada_Y, cantidad_llaves, cantidad_enemigos;
	private JTextArea size_inf, size_inf_shadow;
	private Font Alagard;
	private Controlador c;
	private Sound PrgMusic;

	public PreGameState() {

		Controlador c = new Controlador();
		
		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		//invert initial position -> boton toggle

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		prgBackground = new JLabel(new ImageIcon("src/Assets/Gifs/chBackground.gif"));
		prgBackground.setBounds(0, 0, 1024, 768);

		prg_artBackground = new JLabel();
		prg_artBackground.setIcon(new ImageIcon("src/Assets/Images/chStatePanelBG.png"));
		prg_artBackground.setSize(400, 500);
		prg_artBackground.setLocation(30, 50);

		panel_inf = new JPanel(null);
		panel_inf.setBounds(100, 80, 824, 608);
		panel_inf.setBackground(new Color(0, 0, 0, 160));

		characterInf = new JLabel();
		characterInf.setText("You have selected: ");
		characterInf.setFont(Alagard);
		characterInf.setFont(characterInf.getFont().deriveFont(Font.ITALIC, 25));
		characterInf.setForeground(Color.white);
		characterInf.setLocation(110, 10);
		characterInf.setSize(230, 40);

		characterInf_shadow = new JLabel();
		characterInf_shadow.setText("You have selected: ");
		characterInf_shadow.setFont(Alagard);
		characterInf_shadow.setFont(characterInf.getFont().deriveFont(Font.ITALIC, 25));
		characterInf_shadow.setForeground(Color.DARK_GRAY);
		characterInf_shadow.setLocation(113, 13);
		characterInf_shadow.setSize(230, 40);

		character_name = new JLabel();
		character_name.setLocation(193, 553);
		character_name.setSize(215, 40);
		character_name.setText("Character Name");
		character_name.setFont(Alagard);
		character_name.setFont(character_name.getFont().deriveFont(Font.ITALIC, 25));
		character_name.setForeground(Color.white);

		character_name_shadow = new JLabel();
		character_name_shadow.setLocation(196, 556);
		character_name_shadow.setSize(215, 40);
		character_name_shadow.setText("Character Name");
		character_name_shadow.setFont(Alagard);
		character_name_shadow.setFont(character_name.getFont().deriveFont(Font.ITALIC, 25));
		character_name_shadow.setForeground(Color.darkGray);

		start_button = new JButton();
		start_button.setText("Start Game");
		start_button.setFont(Alagard);
		start_button.setFont(start_button.getFont().deriveFont(Font.ITALIC, 22));
		start_button.setForeground(Color.white);
		start_button.setBackground(Color.black);
		start_button.setSize(320, 60);
		start_button.setLocation(464, 530);

		size_inf = new JTextArea();
		size_inf.setEditable(false);
		size_inf.setOpaque(false);
		size_inf.setText("    Insert the maze\n             size\n \n    Min: 5   Max: 20");
		size_inf.setFont(Alagard);
		size_inf.setFont(size_inf.getFont().deriveFont(Font.ITALIC, 25));
		size_inf.setForeground(Color.white);
		size_inf.setBounds(504, 180, 300, 100);

		size_inf_shadow = new JTextArea();
		size_inf_shadow.setEditable(false);
		size_inf_shadow.setOpaque(false);
		size_inf_shadow.setText("    Insert the maze\n             size\n \n    Min: 5   Max: 20");
		size_inf_shadow.setFont(Alagard);
		size_inf_shadow.setFont(size_inf_shadow.getFont().deriveFont(Font.ITALIC, 25));
		size_inf_shadow.setForeground(Color.DARK_GRAY);
		size_inf_shadow.setBounds(507, 183, 300, 300);

		archer_concept_art = new JLabel();
		archer_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csArcherldle.gif"));
		archer_concept_art.setSize(400, 480);
		archer_concept_art.setLocation(30, 50);
		archer_concept_art.setVisible(false);

		barbarian_concept_art = new JLabel();
		barbarian_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csBarbariandle.gif"));
		barbarian_concept_art.setSize(400, 400);
		barbarian_concept_art.setLocation(30, 100);
		barbarian_concept_art.setVisible(false);
		
		warrior_concept_art = new JLabel();
		warrior_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csWarriorldle.gif"));
		warrior_concept_art.setSize(400, 480);
		warrior_concept_art.setLocation(30, 50);
		warrior_concept_art.setVisible(false);

		mage_concept_art = new JLabel();
		mage_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csMageldle.gif"));
		mage_concept_art.setSize(400, 480);
		mage_concept_art.setLocation(30, 50);
		mage_concept_art.setVisible(false);

		paladin_concept_art = new JLabel();
		paladin_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csPaladindle.gif"));
		paladin_concept_art.setSize(400, 480);
		paladin_concept_art.setLocation(30, 50);
		paladin_concept_art.setVisible(false);

		rogue_concept_art = new JLabel();
		rogue_concept_art.setIcon(new ImageIcon("src/Assets/Gifs/csRogueldle.gif"));
		rogue_concept_art.setSize(400, 480);
		rogue_concept_art.setLocation(30, 50);
		rogue_concept_art.setVisible(false);

		// Ancho y Largo del Laberinto
		indicator = new JLabel();
		indicator.setForeground(Color.white);
		indicator.setFont(Alagard);
		indicator.setFont(indicator.getFont().deriveFont(Font.ITALIC, 25));
		indicator.setBounds(494, 302, 200, 40);
		indicator.setText("X:            Y:");

		entrada_X = new JTextField();
		entrada_X.setLocation(524, 300);
		entrada_X.setSize(80, 40);
		entrada_X.setEditable(true);
		entrada_X.setFont(Alagard);
		entrada_X.setFont(entrada_X.getFont().deriveFont(Font.ITALIC, 30));
		entrada_X.setBackground(Color.DARK_GRAY);
		entrada_X.setForeground(Color.WHITE);
		entrada_X.setToolTipText("Min: 5   Max: 20");

		entrada_Y = new JTextField();
		entrada_Y.setLocation(644, 300);
		entrada_Y.setSize(80, 40);
		entrada_Y.setEditable(true);
		entrada_Y.setFont(Alagard);
		entrada_Y.setFont(entrada_Y.getFont().deriveFont(Font.ITALIC, 30));
		entrada_Y.setBackground(Color.DARK_GRAY);
		entrada_Y.setForeground(Color.WHITE);
		entrada_Y.setToolTipText("Min: 5   Max: 20");

		// Enemigos
		cantidad_enemigos = new JTextField();
		cantidad_enemigos.setLocation(644, 360);
		cantidad_enemigos.setSize(80, 40);
		cantidad_enemigos.setEditable(true);
		cantidad_enemigos.setFont(Alagard);
		cantidad_enemigos.setFont(cantidad_enemigos.getFont().deriveFont(Font.ITALIC, 30));
		cantidad_enemigos.setBackground(Color.DARK_GRAY);
		cantidad_enemigos.setForeground(Color.WHITE);

		enemigos_indicador = new JLabel();
		enemigos_indicador.setForeground(Color.white);
		enemigos_indicador.setFont(Alagard);
		enemigos_indicador.setFont(enemigos_indicador.getFont().deriveFont(Font.ITALIC, 25));
		enemigos_indicador.setBounds(450, 360, 200, 40);
		enemigos_indicador.setText("# of Enemies: ");

		// Llaves
		cantidad_llaves = new JTextField();
		cantidad_llaves.setLocation(644, 420);
		cantidad_llaves.setSize(80, 40);
		cantidad_llaves.setEditable(true);
		cantidad_llaves.setFont(Alagard);
		cantidad_llaves.setFont(cantidad_llaves.getFont().deriveFont(Font.ITALIC, 30));
		cantidad_llaves.setBackground(Color.DARK_GRAY);
		cantidad_llaves.setForeground(Color.WHITE);
		cantidad_llaves.setToolTipText("Min: 2   Max: 5");
		
		PrgMusic = new Sound();

		llaves_indicador = new JLabel();
		llaves_indicador.setForeground(Color.white);
		llaves_indicador.setFont(Alagard);
		llaves_indicador.setFont(llaves_indicador.getFont().deriveFont(Font.ITALIC, 22));
		llaves_indicador.setBounds(450, 420, 200, 40);
		llaves_indicador.setText("# of Keys (2-5): ");

		//
		
		//

		panel_inf.add(cantidad_enemigos);
		panel_inf.add(enemigos_indicador);
		panel_inf.add(cantidad_llaves);
		panel_inf.add(llaves_indicador);
		panel_inf.add(entrada_Y);
		panel_inf.add(entrada_X);
		panel_inf.add(size_inf);
		panel_inf.add(size_inf_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_inf.add(indicator);
		panel_inf.add(characterInf);
		panel_inf.add(characterInf_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_inf.add(start_button);
		panel_inf.add(character_name);
		panel_inf.add(character_name_shadow, JLayeredPane.DEFAULT_LAYER);

		panel_inf.add(archer_concept_art);
		panel_inf.add(barbarian_concept_art);
		panel_inf.add(warrior_concept_art);
		panel_inf.add(mage_concept_art);
		panel_inf.add(paladin_concept_art);
		panel_inf.add(rogue_concept_art);
		panel_inf.add(prg_artBackground);

		add(panel_inf, JLayeredPane.MODAL_LAYER);
		add(back_button, JLayeredPane.MODAL_LAYER);
		add(prgBackground, JLayeredPane.DEFAULT_LAYER);

	}
	

	
	
	public JLabel getPrg_artBackground() {
		return prg_artBackground;
	}



	public void setPrg_artBackground(JLabel prg_artBackground) {
		this.prg_artBackground = prg_artBackground;
	}



	public JLabel getArcher_concept_art() {
		return archer_concept_art;
	}



	public void setArcher_concept_art(JLabel archer_concept_art) {
		this.archer_concept_art = archer_concept_art;
	}



	public JLabel getBarbarian_concept_art() {
		return barbarian_concept_art;
	}



	public void setBarbarian_concept_art(JLabel barbarian_concept_art) {
		this.barbarian_concept_art = barbarian_concept_art;
	}



	public JLabel getWarrior_concept_art() {
		return warrior_concept_art;
	}



	public void setWarrior_concept_art(JLabel warrior_concept_art) {
		this.warrior_concept_art = warrior_concept_art;
	}



	public JLabel getRogue_concept_art() {
		return rogue_concept_art;
	}



	public void setRogue_concept_art(JLabel rogue_concept_art) {
		this.rogue_concept_art = rogue_concept_art;
	}



	public JLabel getPaladin_concept_art() {
		return paladin_concept_art;
	}



	public void setPaladin_concept_art(JLabel paladin_concept_art) {
		this.paladin_concept_art = paladin_concept_art;
	}



	public JLabel getMage_concept_art() {
		return mage_concept_art;
	}



	public void setMage_concept_art(JLabel mage_concept_art) {
		this.mage_concept_art = mage_concept_art;
	}



	public JLabel getCharacter_name_shadow() {
		return character_name_shadow;
	}

	public void setCharacter_name_shadow(JLabel character_name_shadow) {
		this.character_name_shadow = character_name_shadow;
	}

	public JButton getStart_button() {
		return start_button;
	}

	public void setStart_button(JButton start_button) {
		this.start_button = start_button;
	}

	public JLabel getPrgBackground() {
		return prgBackground;
	}

	public void setPrgBackground(JLabel prgBackground) {
		this.prgBackground = prgBackground;
	}

	public JLabel getCharacterInf() {
		return characterInf;
	}

	public void setCharacterInf(JLabel characterInf) {
		this.characterInf = characterInf;
	}

	public JLabel getCharacterInf_shadow() {
		return characterInf_shadow;
	}

	public void setCharacterInf_shadow(JLabel characterInf_shadow) {
		this.characterInf_shadow = characterInf_shadow;
	}

	public JLabel getCharacter_name() {
		return character_name;
	}

	public void setCharacter_name(JLabel character_name) {
		this.character_name = character_name;
	}

	public JPanel getPanel_inf() {
		return panel_inf;
	}

	public void setPanel_inf(JPanel panel_inf) {
		this.panel_inf = panel_inf;
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

	public JLabel getIndicator() {
		return indicator;
	}

	public void setIndicator(JLabel indicator) {
		this.indicator = indicator;
	}

	public JTextField getEntrada_X() {
		return entrada_X;
	}

	public void setEntrada_X(JTextField entrada_X) {
		this.entrada_X = entrada_X;
	}

	public JTextField getEntrada_Y() {
		return entrada_Y;
	}

	public void setEntrada_Y(JTextField entrada_Y) {
		this.entrada_Y = entrada_Y;
	}

	public JTextArea getSize_inf() {
		return size_inf;
	}

	public void setSize_inf(JTextArea size_inf) {
		this.size_inf = size_inf;
	}

	public JTextArea getSize_inf_shadow() {
		return size_inf_shadow;
	}

	public void setSize_inf_shadow(JTextArea size_inf_shadow) {
		this.size_inf_shadow = size_inf_shadow;
	}

	public JLabel getEnemigos_indicador() {
		return enemigos_indicador;
	}

	public void setEnemigos_indicador(JLabel enemigos_indicador) {
		this.enemigos_indicador = enemigos_indicador;
	}

	public JLabel getLlaves_indicador() {
		return llaves_indicador;
	}

	public void setLlaves_indicador(JLabel llaves_indicador) {
		this.llaves_indicador = llaves_indicador;
	}

	public JTextField getCantidad_llaves() {
		return cantidad_llaves;
	}

	public void setCantidad_llaves(JTextField cantidad_llaves) {
		this.cantidad_llaves = cantidad_llaves;
	}

	public JTextField getCantidad_enemigos() {
		return cantidad_enemigos;
	}

	public void setCantidad_enemigos(JTextField cantidad_enemigos) {
		this.cantidad_enemigos = cantidad_enemigos;
	}

	public Controlador getC() {
		return c;
	}

	public void setC(Controlador c) {
		this.c = c;
	}
	
public void playSE(int i) {
		
		PrgMusic.setFile(i);
		PrgMusic.play();
	}

}
