package co.edu.unbosque.vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import co.edu.unbosque.controlador.Controlador;

public class PlayerRender extends JLabel{
	
	private Controlador c;

	public PlayerRender() {

		c = new Controlador();
		
		if(c.getApariencia() == 0) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_archer.gif"));
			setSize(32,32);
		}
		else if(c.getApariencia() == 1) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_warrior.gif"));
			setSize(32,32);
		}
		else if(c.getApariencia() == 2) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_paladin.gif"));
			setSize(32,32);
		}
		else if(c.getApariencia() == 3) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_barbarian.gif"));
			setSize(32,32);
		}
		else if(c.getApariencia() == 4) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_rogue.gif"));
			setSize(32,32);
		}
		else if(c.getApariencia() == 5) {
			setIcon(new ImageIcon("src/Assets/Entity/sprite_mage.gif"));
			setSize(32,32);
		}
	}
}
