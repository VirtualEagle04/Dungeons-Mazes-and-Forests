package co.edu.unbosque.vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyRender extends JLabel{

	private int posRow;
	private int posCol;
	
	public EnemyRender(int pos_Row, int pos_Col) {
		this.posRow = pos_Row;
		this.posCol = pos_Col;
		
	}
	
	public void lethalRender() {
		setIcon(new ImageIcon("src/Assets/Entity/sprite_lethal_assasin.gif"));
		setSize(32, 32);
	}
	
	public void stormyRender() {
		setIcon(new ImageIcon("src/Assets/Entity/sprite_stormy.gif"));
		setSize(32, 32);
	}

	public int getPosRow() {
		return posRow;
	}

	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	public int getPosCol() {
		return posCol;
	}

	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}
	
	
}
