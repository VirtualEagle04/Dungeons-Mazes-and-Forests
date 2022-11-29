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
		ceros = new ArrayList<Coord>();
		llaves = new ArrayList<Coord>();
		enemigos = new ArrayList<Coord>();
		
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

		
		int muros_max = (columns/2)+1;
		
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
		
		//Llaves
		for (int i = 1; i <= keys; i++) {
			int index_temp = rnd.nextInt(ceros.size());

			int tempRow = ceros.get(index_temp).getRow();
			int tempCol = ceros.get(index_temp).getCol();
			
			llaves.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 5;
			
			ceros.remove(index_temp);
		}
		//Debug
		System.out.println("Posiciones 0 despues de poner llaves: ");
		for (Coord coord : ceros) {
			System.out.println("("+coord.getRow()+", "+coord.getCol()+")");
		}
		
		
		//Enemigos
		for (int i = 1; i <= enemies; i++) {			
			int index_temp = rnd.nextInt(ceros.size());
			System.out.println(ceros.get(index_temp).getRow()+", "+ceros.get(index_temp).getCol());
			
			int tempRow = ceros.get(index_temp).getRow();
			int tempCol = ceros.get(index_temp).getCol();

			enemigos.add(new Coord(tempRow, tempCol));
			maze[tempRow][tempCol] = 2;
			
			ceros.remove(index_temp);
		}
		
		//Debug
		System.out.println("Posiciones 0 despues de poner enemigos: ");
		for (Coord coord : ceros) {
			System.out.println("("+coord.getRow()+", "+coord.getCol()+")");
		}
		System.out.println();
		
		//Debug
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j]+" ");
			}
			System.out.print("\n");
		}
		
		
		//Debug
		System.out.println("Coords llaves: ");
		for (Coord coordLlave : llaves) {
			System.out.println("("+coordLlave.getRow()+", "+coordLlave.getCol()+")");
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

	public ArrayList<Coord> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Coord> enemigos) {
		this.enemigos = enemigos;
	}
	
	
}
