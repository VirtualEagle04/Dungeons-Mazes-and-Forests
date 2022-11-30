package co.edu.unbosque.modelo;

import java.util.ArrayList;

import co.edu.unbosque.controlador.Controlador;

public class KeysHavePath {
	
	private KeyBFS keyBFS;

	private int[][] maze;
	private ArrayList<Coord> coord_llaves;

	private boolean[] solucionLlaves;
	private boolean solucionable = true;

	public KeysHavePath(ArrayList<Coord> llaves, int[][] mazeMatrix) {
		
		maze = mazeMatrix;
		coord_llaves = llaves;
		keyBFS = new KeyBFS();

		solucionLlaves = new boolean[coord_llaves.size()]; //booleano para guardar el resultado del BFS del inicio hasta cada llave
		int solLlaveIndex = 0;
		
		for (Coord coordLlave : coord_llaves) {
			
			int[][] individualKeyMatrix = maze;
			//individualKeyMatrix[i][j] == 2 || 
			for (int i = 0; i < individualKeyMatrix.length; i++) {

				for (int j = 0; j < individualKeyMatrix[i].length; j++) {
					
					//Reemplazar todo enemigo y llave (excepto la actual), por un camino = 0
					if ((individualKeyMatrix[i][j] == 5 && (i != coordLlave.getRow() && j != coordLlave.getCol()))){
						individualKeyMatrix[i][j] = 0;
					}
				}
				
//				//Debug
//				System.out.println("Laberinto reemplazado: ");
//				for (int r = 0; r < individualKeyMatrix.length; r++) {
//					for (int c = 0; c < individualKeyMatrix[i].length; c++) {
//						System.out.print(individualKeyMatrix[r][c]+" ");
//					}
//					System.out.print("\n");
//				}
				
				
			}
			solucionLlaves[solLlaveIndex] = keyBFS.KeySearchBFS(individualKeyMatrix);
			solLlaveIndex++;
		}
		//Comprobar que para cada llave, haya un camino
		for (boolean solLlave : solucionLlaves) {
			System.out.println(solLlave);
			if(solLlave == true){
				solucionable = true;
			}
			else if(solLlave == false){
				solucionable = false;
				break;
			}
		}
	}

	public boolean isSolucionable() {
		return solucionable;
	}

	public void setSolucionable(boolean solucionable) {
		this.solucionable = solucionable;
	}
}
