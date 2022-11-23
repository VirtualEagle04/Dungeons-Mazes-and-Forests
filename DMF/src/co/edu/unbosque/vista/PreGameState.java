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

public class PreGameState extends JLayeredPane {

	private JButton back_button, start_button;
	private JLabel prgBackground, characterInf, characterInf_shadow, character_name, character_name_shadow, indicator;
	private JPanel panel_inf;
	private Font Alagard;
	private JTextArea size_inf, size_inf_shadow;
	private JTextField  labSizeX, labSizeY;

	public PreGameState() {

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

		back_button = new JButton();
		back_button.setLocation(10, 10);
		back_button.setSize(90, 50);
		back_button.setBackground(Color.darkGray);
		back_button.setIcon(new ImageIcon("src/Assets/Images/back_button.jpg"));

		prgBackground = new JLabel(new ImageIcon("src/Assets/Gifs/chBackground.gif"));
		prgBackground.setBounds(0, 0, 1024, 768);

		panel_inf = new JPanel(null);
		panel_inf.setBounds(100, 80, 824, 608);
		panel_inf.setBackground(new Color(0, 0, 0, 160));

		characterInf = new JLabel();
		characterInf.setText("You have select: ");
		characterInf.setFont(Alagard);
		characterInf.setFont(characterInf.getFont().deriveFont(Font.ITALIC, 25));
		characterInf.setForeground(Color.white);
		characterInf.setLocation(80, 30);
		characterInf.setSize(200, 40);

		characterInf_shadow = new JLabel();
		characterInf_shadow.setText("You have select: ");
		characterInf_shadow.setFont(Alagard);
		characterInf_shadow.setFont(characterInf.getFont().deriveFont(Font.ITALIC, 25));
		characterInf_shadow.setForeground(Color.DARK_GRAY);
		characterInf_shadow.setLocation(83, 33);
		characterInf_shadow.setSize(200, 40);

		character_name = new JLabel();
		character_name.setLocation(103, 533);
		character_name.setSize(215, 40);
		character_name.setText("character name");
		character_name.setFont(Alagard);
		character_name.setFont(character_name.getFont().deriveFont(Font.ITALIC, 25));
		character_name.setForeground(Color.white);

		character_name_shadow = new JLabel();
		character_name_shadow.setLocation(106, 536);
		character_name_shadow.setSize(215, 40);
		character_name_shadow.setText("character name");
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
		size_inf_shadow.setFont(size_inf.getFont().deriveFont(Font.ITALIC, 25));
		size_inf_shadow.setForeground(Color.DARK_GRAY);
		size_inf_shadow.setBounds(507, 183, 300, 300);
		
		indicator = new JLabel();
		indicator.setForeground(Color.white);
		indicator.setFont(Alagard);
		indicator.setFont(size_inf.getFont().deriveFont(Font.BOLD, 25));
		indicator.setBounds(494,302, 200,40);
		indicator.setText("X:            Y:");
		
		labSizeX = new JTextField();
		labSizeX.setBackground(Color.DARK_GRAY);
		labSizeX.setFont(Alagard);
		labSizeX.setFont(size_inf.getFont().deriveFont(Font.ITALIC, 18));
		labSizeX.setForeground(Color.white);
		labSizeX.setSize(80,40);
		labSizeX.setLocation(524,300);
		
		labSizeY = new JTextField();
		labSizeY.setBackground(Color.DARK_GRAY);
		labSizeY.setFont(Alagard);
		labSizeY.setFont(size_inf.getFont().deriveFont(Font.ITALIC, 18));
		labSizeY.setForeground(Color.white);
		labSizeY.setSize(80,40);
		labSizeY.setLocation(644,300);
		
		panel_inf.add(characterInf);
		panel_inf.add(characterInf_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_inf.add(indicator);
		panel_inf.add(size_inf);
		panel_inf.add(size_inf_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_inf.add(indicator);
		panel_inf.add(start_button);
		panel_inf.add(character_name);
		panel_inf.add(character_name_shadow, JLayeredPane.DEFAULT_LAYER);
		panel_inf.add(labSizeX);
		panel_inf.add(labSizeY);

		add(panel_inf, JLayeredPane.MODAL_LAYER);
		add(back_button, JLayeredPane.MODAL_LAYER);
		add(prgBackground, JLayeredPane.DEFAULT_LAYER);

	}

	

	public JLabel getCharacter_name_shadow() {
		return character_name_shadow;
	}



	public void setCharacter_name_shadow(JLabel character_name_shadow) {
		this.character_name_shadow = character_name_shadow;
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



	public JTextField getLabSizeX() {
		return labSizeX;
	}



	public void setLabSizeX(JTextField labSizeX) {
		this.labSizeX = labSizeX;
	}



	public JTextField getLabSizeY() {
		return labSizeY;
	}



	public void setLabSizeY(JTextField labSizeY) {
		this.labSizeY = labSizeY;
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

}
