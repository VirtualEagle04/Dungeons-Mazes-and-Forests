package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class OptionsState extends JLayeredPane {

	private JLabel opOptionsTitle,opOptionsTitle1,opBackground;
	
	
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
	
	opOptionsTitle1 = new JLabel();
	opOptionsTitle1.setFont(new Font("Alagard", Font.ITALIC, 40));
	opOptionsTitle1.setForeground(Color.GRAY);
	opOptionsTitle1.setText("Options");
	opOptionsTitle1.setSize(1024, 40);
	opOptionsTitle1.setLocation(33, 33);
	
	opBackground = new JLabel(new ImageIcon("src/Assets/CQx.gif"));
	opBackground.setBounds(0, 0, 1024, 768);
	
	setVisible(true);
	
	add(opOptionsTitle);
	add(opOptionsTitle1);
	add(opBackground);
	
	}

}
