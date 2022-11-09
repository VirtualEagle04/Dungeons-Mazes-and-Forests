package co.edu.unbosque.vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterState extends JPanel{
	//Components
	private JButton csMage, csPaladin, csWarrior, csBarbarian, csArcher, csRogue;
	private JButton volver, iniciar;
	private JPanel p1;
	private JLabel fondo;

	public CharacterState() {

		setSize(1024, 768);
		setLocation(0, 0);
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		fondo = new JLabel(new ImageIcon("src/Assets/bi.gif"));
		fondo.setBounds(0, 0, 1024, 768);

		p1 = new JPanel(null);
		p1.setSize(480, 768);
		p1.setLocation(0, 0);
		p1.setBackground(Color.black);

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
		
		csRogue = new JButton();
		csRogue.setLocation(40,580);
		csRogue.setSize(400,70);
		csRogue.setIcon(new ImageIcon("src/Assets/background_rogue.jpg"));
		
		p1.add(csMage);
		p1.add(csPaladin);
		p1.add(csWarrior);
		p1.add(csBarbarian);
		p1.add(csArcher);
		p1.add(csRogue);
		add(p1);
		add(fondo);
		setVisible(true);

	}
	
}
