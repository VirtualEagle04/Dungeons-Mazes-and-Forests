package co.edu.unbosque.modelo;

import java.util.ArrayList;

import co.edu.unbosque.controlador.Controlador;

public class KeysHavePath {
	
	private Controlador c;
	private KeyBFS keyBFS;

	private int[][] mazeMatrix;
	private int[][] individualKeyMatrix;
	private ArrayList<Coord> llaves;


	private boolean[] solucionLlaves;
	private boolean solucion_total = true;

	public KeysHavePath() {
		c = new Controlador();
		llaves = new ArrayList<Coord>();
		mazeMatrix = c.getMazeMatrix();
		llaves = c.getLlaves();
		keyBFS = new KeyBFS();

		solucionLlaves = new boolean[llaves.size()];
		int solLlaveIndex = 0;
		
		for (Coord coordLlave : llaves) {
			
			individualKeyMatrix = mazeMatrix;
			//individualKeyMatrix[r][c] == 2 ||
			//Rows
			for (int r = 0; r < individualKeyMatrix.length; r++) {
				//Columns
				for (int c = 0; c < individualKeyMatrix[r].length; c++) {
					//Reemplazar todo enemigo y llave (excepto la actual), por un camino = 0
					if ( (individualKeyMatrix[r][c] == 5 && (r != coordLlave.getRow() && c != coordLlave.getCol()))){
						individualKeyMatrix[r][c] = 0;
					}
				}
			}
			solucionLlaves[solLlaveIndex] = keyBFS.KeySearchBFS(individualKeyMatrix);
			solLlaveIndex++;
		}
		//Comprobar que para cada llave, haya un camino
		for (boolean element : solucionLlaves) {
			if (element == false) {
				solucion_total = false;

			}
		}
	}

	public boolean isSolucion_total() {
		return solucion_total;
	}

	public void setSolucion_total(boolean solucion_total) {
		this.solucion_total = solucion_total;
	}
}
