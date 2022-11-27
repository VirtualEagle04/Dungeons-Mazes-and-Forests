package co.edu.unbosque.modelo;

import java.util.Random;

public class MazeGeneratorMatrix {

	public MazeGeneratorMatrix() {}
	
	public int[][] generateMaze(int rows, int columns){
		int[][] maze = new int[rows+2][columns+2]; //Dimensiones del usuario + bordes (arriba, abajo, izq, drch)
		Random rnd = new Random();
		
		//Borde Superior
		for (int i = 0; i < columns+2; i++) {
			maze[0][i] = 1;
		}
		
		//Borde Inferior
		for (int i = 0; i < columns+2; i++) {
			maze[maze.length-1][i] = 1;
		}
		
		//Borde Izquierdo
		for (int i = 0; i < rows+2; i++) {
			maze[i][0] = 1;
		}
		
		//Borde Derecho
		for (int i = 0; i < rows+2; i++) {
			maze[i][columns+1] = 1;
		}
		
			//Entrada / Salida
					int temp = rnd.nextInt(2); //Para decidir en qué borde estará la entrada y la salida
					//Arriba / Abajo
					if (temp == 0) {
						//Inicio Borde Arriba
						int posInicio = rnd.nextInt(columns + 1 - 1) + 1;  // (max + 1 - min) + min
						maze[0][posInicio] = 3; 
						
						//Final Borde Abajo
						int posFinal = rnd.nextInt(columns + 1 - 1) + 1;
						maze[maze.length-1][posFinal] = 4;
						
					}
					//Izquierda / Derecha
					else if (temp == 1) {
						//Inicio Borde Izquierda
						int posInicio = rnd.nextInt(rows + 1 - 1) + 1;
						maze[posInicio][0] = 3;
						
						//Final Borde Derecha
						int posFinal = rnd.nextInt(rows + 1 - 1) + 1;
						maze[posFinal][columns+1] = 4;
					}
		
		
		
		
		//Rows
		for (int i = 1; i <= rows; i++) {
			
			//Columns
			for (int j = 1; j <= columns; j++) {
				int muroCamino = rnd.nextInt(2);
				
				//Muro
				if (muroCamino == 1) {
					maze[i][j] = 1;
				}
				//Camino
				else if (muroCamino == 0) {
					int enemigo_llave_camino = rnd.nextInt(20); //Elegir entre 0-1-2 para: camino, enemigo, llave
					
					if (enemigo_llave_camino == 0) {
						maze[i][j] = 0; //Camino
					}
					else if (enemigo_llave_camino == 1) {
						maze[i][j] = 2; //Enemigo
					}
					else if (enemigo_llave_camino == 2) {
						maze[i][j] = 5; //LLave
					}
					
				}
			}
			
		}
		 
		return maze;
	}
	
	
}
