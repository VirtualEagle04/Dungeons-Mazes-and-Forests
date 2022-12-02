package co.edu.unbosque.vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KeyRender extends JLabel{
	
	private int posRow;
	private int posCol;
	
	public KeyRender(int pos_Row, int pos_Col) {
		setIcon(new ImageIcon("src/Assets/Entity/Key.gif"));
		setSize(32, 32);
		
		this.posRow = pos_Row;
		this.posCol = pos_Col;
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
