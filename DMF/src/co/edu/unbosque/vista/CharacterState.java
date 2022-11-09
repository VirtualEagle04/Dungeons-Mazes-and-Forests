package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CharacterState extends JPanel {
	// Components
	private JButton csMage, csPaladin, csWarrior, csBarbarian, csArcher, csRogue;
	private JButton volver, iniciar;
	private JPanel panel_buttons, panel_art;
	private JLabel bacground_principal, archer_concept_art;
	private JTextArea panel_text,panel_text_shadow;

	public CharacterState() {

		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		
		bacground_principal = new JLabel(new ImageIcon("src/Assets/bi.gif"));
		bacground_principal.setBounds(0,0,1024,768);
		
		panel_buttons = new JPanel(null);
		panel_buttons.setSize(480, 768);
		panel_buttons.setLocation(0, 0);
		panel_buttons.setBackground(Color.black);
		
		panel_art = new JPanel(null);
		panel_art.setLocation(540,60);
		panel_art.setSize(400,500);
		panel_art.setBackground(Color.black);
		
		panel_text = new JTextArea();
		panel_text.setBackground(Color.black);
		panel_text.setText("  CHOOSE \n\n       A \n\nCHARACTER");
		panel_text.setFont(new Font("Alagard", Font.ITALIC, 40));
		panel_text.setForeground(Color.white);
		panel_text.setBounds(90,130,400,500);
		panel_text.setVisible(false);
		panel_text.setEditable(false);

		csMage = new JButton();
		csMage.setLocation(40,80);
		csMage.setSize(400,70);
		csMage.setIcon(new ImageIcon("src/Assets/background_mage.png"));
		
		csPaladin = new JButton();
		csPaladin.setLocation(40,180);
		csPaladin.setSize(400,70);
		csPaladin.setIcon(new ImageIcon("src/Assets/background_paladin.png"));
		
		
		csWarrior = new JButton();
		csWarrior.setLocation(40,280);
		csWarrior.setSize(400,70);
		csWarrior.setIcon(new ImageIcon("src/Assets/background_warrior.jpg"));
		
		csBarbarian = new JButton();
		csBarbarian.setLocation(40,380);
		csBarbarian.setSize(400,70);
		csBarbarian.setIcon(new ImageIcon("src/Assets/background_barbarian.png"));
		
		csArcher = new JButton();
		csArcher.setLocation(40,480);
		csArcher.setSize(400,70);
		csArcher.setIcon(new ImageIcon("src/Assets/background_archer.jpg"));
		
		archer_concept_art = new JLabel();
		archer_concept_art.setIcon(new ImageIcon("src/Assets/archer_concept_art.gif"));
		archer_concept_art.setSize(400,400);
		archer_concept_art.setLocation(0,0);
		archer_concept_art.setVisible(true);
		
		
		csRogue = new JButton();
		csRogue.setLocation(40,580);
		csRogue.setSize(400,70);
		csRogue.setIcon(new ImageIcon("src/Assets/background_rogue.jpg"));
		
		//character_art
		
		panel_art.add(panel_text);
		panel_art.add(archer_concept_art);
		
		//buttons
		
		panel_buttons.add(csMage);
		panel_buttons.add(csPaladin);
		panel_buttons.add(csWarrior);
		panel_buttons.add(csBarbarian);
		panel_buttons.add(csArcher);
		panel_buttons.add(csRogue);
		
		//panel & background
		
		add(panel_buttons, JLayeredPane.MODAL_LAYER);
		add(panel_art, JLayeredPane.MODAL_LAYER);
		add(bacground_principal, JLayeredPane.DEFAULT_LAYER);
		setVisible(true);

	}

	public JButton getCsMage() {
		return csMage;
	}

	public void setCsMage(JButton csMage) {
		this.csMage = csMage;
	}

	public JButton getCsPaladin() {
		return csPaladin;
	}

	public void setCsPaladin(JButton csPaladin) {
		this.csPaladin = csPaladin;
	}

	public JButton getCsWarrior() {
		return csWarrior;
	}

	public void setCsWarrior(JButton csWarrior) {
		this.csWarrior = csWarrior;
	}

	public JButton getCsBarbarian() {
		return csBarbarian;
	}

	public void setCsBarbarian(JButton csBarbarian) {
		this.csBarbarian = csBarbarian;
	}

	public JButton getCsArcher() {
		return csArcher;
	}

	public void setCsArcher(JButton csArcher) {
		this.csArcher = csArcher;
	}

	public JButton getCsRogue() {
		return csRogue;
	}

	public void setCsRogue(JButton csRogue) {
		this.csRogue = csRogue;
	}

	public JButton getVolver() {
		return volver;
	}

	public void setVolver(JButton volver) {
		this.volver = volver;
	}

	public JButton getIniciar() {
		return iniciar;
	}

	public void setIniciar(JButton iniciar) {
		this.iniciar = iniciar;
	}

	public JPanel getP1() {
		return p1;
	}

	public void setP1(JPanel p1) {
		this.p1 = p1;
	}

	public JLabel getFondo() {
		return bacground_principal;
	}

	public void setFondo(JLabel fondo) {
		this.bacground_principal = fondo;
	}

}
