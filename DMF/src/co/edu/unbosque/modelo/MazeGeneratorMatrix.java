package co.edu.unbosque.modelo;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.controlador.Controlador;

public class MazeGeneratorMatrix {
	
	private ArrayList<Coord> ceros;
	private ArrayList<Coord> llaves;
	private ArrayList<Coord> enemigos;
	private Controlador c;
	private int[][] maze;

	public MazeGeneratorMatrix() {}
	
	public MazeGeneratorMatrix(int rows, int columns) {
		c = new Controlador();
		int keys = c.getKeys();
		int enemies = c.getEnemies();
		
		maze = new int[rows+2][columns+2]; //Dimensiones del usuario + bordes (arriba, abajo, izq, drch)
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
						//int posInicio = rnd.nextInt(columns + 1 - 1) + 1;  // (max + 1 - min) + min
						maze[0][1] = 3; 
						
						//Final Borde Abajo
						//int posFinal = rnd.nextInt(columns + 1 - 1) + 1;
						maze[maze.length-1][columns] = 4;
						
					}
					//Izquierda / Derecha
					else if (temp == 1) {
						//Inicio Borde Izquierda
						//int posInicio = rnd.nextInt(rows + 1 - 1) + 1;
						maze[2][0] = 3;
						
						//Final Borde Derecha
						//int posFinal = rnd.nextInt(rows + 1 - 1) + 1;
						maze[rows][columns+1] = 4;
					}
		
		//Para almacenar las coordenadas X, Y de todos los ceros en la matriz
		ceros = new ArrayList<Coord>();
		
		//Rows
		for (int i = 1; i <= rows; i++) {
			//Columns
			for (int j = 1; j <= columns; j++) {
				int cero_uno = rnd.nextInt(2);
				
				if(cero_uno == 0) {
					ceros.add(new Coord(i, j));
				}
				
				maze[i][j] = cero_uno;
			}
		}
		
		llaves = new ArrayList<Coord>();
		enemigos = new ArrayList<Coord>();
		

		
		//Llaves
		for (int i = 1; i <= keys; i++) {
			int pos_temp = rnd.nextInt(ceros.size());

			int tempRow = ceros.get(pos_temp).getRow();
			int tempCol = ceros.get(pos_temp).getCol();
			
			llaves.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 5;
			
			ceros.remove(pos_temp);
		}
		
		//Enemigos
		for (int i = 1; i <= enemies; i++) {			
			int pos_temp = rnd.nextInt(ceros.size());
			
			int tempRow = ceros.get(pos_temp).getRow();
			int tempCol = ceros.get(pos_temp).getCol();

			enemigos.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 2;
			
			ceros.remove(pos_temp);
		}
		

		

		maze = maze;
	}


	public ArrayList<Coord> getLlaves() {
		return llaves;
	}


	public void setLlaves(ArrayList<Coord> llaves) {
		this.llaves = llaves;
	}

	public int[][] getMaze() {
		return maze;
	}

	public void setMaze(int[][] maze) {
		this.maze = maze;
	}

	public ArrayList<Coord> getCeros() {
		return ceros;
	}

	public void setCeros(ArrayList<Coord> ceros) {
		this.ceros = ceros;
	}

	public ArrayList<Coord> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Coord> enemigos) {
		this.enemigos = enemigos;
	}
	
	
}
