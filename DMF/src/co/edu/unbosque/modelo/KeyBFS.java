package co.edu.unbosque.modelo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import co.edu.unbosque.controlador.Controlador;

public class KeyBFS {

	static Controlador c = new Controlador();

	static final int tamañoMatriz = 400;
	static int ady[][] = new int[tamañoMatriz][tamañoMatriz];
	static boolean visitado[][] = new boolean[tamañoMatriz][tamañoMatriz];
	static Coord prev[][] = new Coord[tamañoMatriz][tamañoMatriz];
	
	static int mazeMatrix[][];
	private boolean solucion;
	
	
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int h, w;
	
	static class Coord {
		int x;
		int y;
		int d;

		Coord(int inicioX, int inicioY, int d1) {
			this.x = inicioX;
			this.y = inicioY;
			this.d = d1;
		}

		Coord() {
		}
	}

	public boolean KeySearchBFS(int[][] mazeMatrixInput) {
		mazeMatrix = mazeMatrixInput;

		int x = 0, y = 0;
		h = c.getRows() + 2;
		w = c.getColumns() + 2;

		//Busca coordenada del inicio (3)
		for (int i = 0; i < mazeMatrix.length; i++) {
			for (int j = 0; j < mazeMatrix[0].length; j++) {
				ady[i][j] = mazeMatrix[i][j];

				if (ady[i][j] == 3) {
					x = i;
					y = j;
				}
			}
		}
		int min = BFS(x, y, h, w);
		if (min != -1) {
			//System.out.println("Menor numero de movimientos: " + min);
			//System.out.println("Laberinto con solucion");
			solucion = true;
		} else {
			//System.out.println("No hay solucion, generando nuevo laberinto");
			solucion = false;
		}
		return solucion;
	}

	static void print(int x, int y) {
		for (int i = x, j = y; prev[i][j].d != -1; i = prev[x][y].x, j = prev[x][y].y) {
			ady[i][j] = 8;
			x = i;
			y = j;
		}
		
//		for (int i = 0; i < h; ++i) {
//			for (int j = 0; j < w; ++j) {
//				System.out.print(ady[i][j]+" ");
//			}
//			System.out.print("\n");
//		}
//		System.out.println("\n");
	}

	static int BFS(int x, int y, int h, int w) {
		Coord inicial = new Coord(x, y, 0);

		Queue<Coord> Q = new LinkedList<>();

		Q.offer(inicial);
		for (int i = 0; i < tamañoMatriz; i++) {
			Arrays.fill(visitado[i], false);
		}

		prev[x][y] = new Coord(-1, -1, -1);

		while (!Q.isEmpty()) {

			Coord actual = Q.peek();

			Q.poll();

			if (ady[actual.x][actual.y] == 5) {

				print(actual.x, actual.y);

				return actual.d;
			}
			

			visitado[actual.x][actual.y] = true;

			for (int i = 0; i < 4; ++i) {

				int nx = dx[i] + actual.x;

				int ny = dy[i] + actual.y;

				if (nx >= 0 && nx < h && ny >= 0 && ny < w && ady[nx][ny] != 1 && !visitado[nx][ny]) {

					Coord adyacente = new Coord(nx, ny, actual.d + 1);

					Q.offer(adyacente);

					prev[nx][ny] = actual;
				}
			}
		}
		return -1;
		
	}
	
	public boolean isSolucion() {
		return solucion;
	}

	public void setSolucion(boolean solucion) {
		this.solucion = solucion;
	}
}
