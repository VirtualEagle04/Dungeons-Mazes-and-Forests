package co.edu.unbosque.vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyRender extends JLabel{

	public EnemyRender() {}
	
	public void lethalRender() {
		setIcon(new ImageIcon("src/Assets/Entity/Enemy.gif"));
		setSize(32, 32);
	}
	
	public void stormyRender() {
		setIcon(new ImageIcon("src/Assets/Entity/Stormy.png"));
		setSize(32, 32);
	}
	
	
}
