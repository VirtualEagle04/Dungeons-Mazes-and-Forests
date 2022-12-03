package co.edu.unbosque.modelo;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.controlador.Controlador;

public class MazeGeneratorMatrix {
	
	private ArrayList<Coord> ceros;
	private ArrayList<Coord> llaves;
	private ArrayList<Coord> coordsLethal;
	private ArrayList<Coord> coordsStormy;
	private Controlador c;
	private int[][] maze;

	public MazeGeneratorMatrix() {}
	
	public MazeGeneratorMatrix(int rows, int columns, boolean reves) {
		c = new Controlador();
		int keys = c.getKeys();
		int lethal = c.getLethal();
		int stormy = c.getStormy();
		ceros = new ArrayList<Coord>();
		llaves = new ArrayList<Coord>();
		coordsLethal = new ArrayList<Coord>();
		coordsStormy = new ArrayList<Coord>();
		
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
		if(reves == false) {
			int temp = rnd.nextInt(2); //Para decidir en qué borde estará la entrada y la salida
			//Arriba / Abajo
			if (temp == 0) {
				//Inicio Borde Arriba
				maze[0][1] = 3; 
				
				//Final Borde Abajo
				maze[maze.length-1][columns] = 4;
				
			}
			//Izquierda / Derecha
			else if (temp == 1) {
				//Inicio Borde Izquierda
				maze[2][0] = 3;
				
				//Final Borde Derecha
				maze[rows][columns+1] = 4;
			}
		}
		if (reves == true) {
			int temp = rnd.nextInt(2); //Para decidir en qué borde estará la entrada y la salida
			//Arriba / Abajo
			if (temp == 0) {
				//Inicio Borde Arriba
				maze[0][1] = 4; 
				
				//Final Borde Abajo
				maze[maze.length-1][columns] = 3;
				
			}
			//Izquierda / Derecha
			else if (temp == 1) {
				//Inicio Borde Izquierda
				maze[2][0] = 4;
				
				//Final Borde Derecha
				maze[rows][columns+1] = 3;
			}
		}
		
		


		
		int muros_max = (columns + rows) / 2;
		
		//Rows
		for (int i = 1; i <= rows; i++) {
			int conteo_muros_max = muros_max;
			//Columns
			for (int j = 1; j <= columns; j++) {
				
				int cero_uno = rnd.nextInt(2);
				
				if(conteo_muros_max != 0) {
					if(cero_uno == 1) {
						conteo_muros_max--;
						maze[i][j] = cero_uno;
					}
					else if (cero_uno == 0) {
						maze[i][j] = cero_uno;
						ceros.add(new Coord(i, j));
					}
				}else {
					maze[i][j] = 0;
					ceros.add(new Coord(i, j));
				}
			}
		}
		
		
		
		//Lethal
		for (int i = 1; i <= lethal; i++) {			
			int index_temp = rnd.nextInt(ceros.size());
			
			int tempRow = ceros.get(index_temp).getRow();
			int tempCol = ceros.get(index_temp).getCol();

			coordsLethal.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 2;
			
			ceros.remove(index_temp);
		}
		
		
		//Stormy
		for (int i = 1; i <= stormy; i++) {
			int index_temp = rnd.nextInt(ceros.size());
			
			int tempRow = ceros.get(index_temp).getRow();
			int tempCol = ceros.get(index_temp).getCol();
			
			coordsStormy.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 6;
			
			ceros.remove(index_temp);
		}
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

	public ArrayList<Coord> getCoordsLethal() {
		return coordsLethal;
	}

	public void setCoordsLethal(ArrayList<Coord> coordsLethal) {
		this.coordsLethal = coordsLethal;
	}

	public ArrayList<Coord> getCoordsStormy() {
		return coordsStormy;
	}

	public void setCoordsStormy(ArrayList<Coord> coordsStormy) {
		this.coordsStormy = coordsStormy;
	}
}
