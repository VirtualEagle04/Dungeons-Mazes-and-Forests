package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class OptionsState extends JLayeredPane {

	private JLabel opOptionsTitle,opBackground;
	
	
	public OptionsState() {
	setBounds(0,0,1024,768);
	setLayout(null);
	setBackground(Color.DARK_GRAY);

	opOptionsTitle = new JLabel();
	opOptionsTitle.setFont(new Font("Alagard", Font.ITALIC, 40));
	opOptionsTitle.setForeground(Color.WHITE);
	opOptionsTitle.setText("Options");
	opOptionsTitle.setSize(1024, 40);
	opOptionsTitle.setLocation(30, 30);
	
	opBackground = new JLabel(new ImageIcon("src/Assets/CQx.gif"));
	opBackground.setBounds(0, 0, 1024, 768);
	
	setVisible(true);
	
	add(opOptionsTitle);
	add(opBackground);
	
	}

}
