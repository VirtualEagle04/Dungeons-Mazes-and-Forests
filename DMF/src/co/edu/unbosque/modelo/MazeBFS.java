package co.edu.unbosque.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import co.edu.unbosque.controlador.Controlador;

public class MazeBFS {

	static Controlador c = new Controlador();

	static final int tamaņoMatriz = 400;
	static int ady[][] = new int[tamaņoMatriz][tamaņoMatriz];
	static boolean visitado[][] = new boolean[tamaņoMatriz][tamaņoMatriz];
	static Coord prev[][] = new Coord[tamaņoMatriz][tamaņoMatriz];
	
	static int mazeMatrix[][];
	private boolean solucion;
	private static ArrayList<co.edu.unbosque.modelo.Coord> coordsCamino = new ArrayList<co.edu.unbosque.modelo.Coord>();
	
	
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

	public MazeBFS(int[][] mazeMatrixInput) {
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
	}

	static void print(int x, int y) {
		for (int i = x, j = y; prev[i][j].d != -1; i = prev[x][y].x, j = prev[x][y].y) {
			ady[i][j] = 8;
			coordsCamino.add(new co.edu.unbosque.modelo.Coord(i, j));
			x = i;
			y = j;
		}
		coordsCamino.remove(0);
		
//		for (co.edu.unbosque.modelo.Coord coordCamino : coordsCamino) {
//			System.out.println("("+coordCamino.getRow()+", "+coordCamino.getCol()+")");
//		}
//		
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
		for (int i = 0; i < tamaņoMatriz; i++) {
			Arrays.fill(visitado[i], false);
		}

		prev[x][y] = new Coord(-1, -1, -1);

		while (!Q.isEmpty()) {

			Coord actual = Q.peek();

			Q.poll();

			if (ady[actual.x][actual.y] == 4) {

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

	public ArrayList<co.edu.unbosque.modelo.Coord> getCoordsCamino() {
		return coordsCamino;
	}

	public static void setCoordsCamino(ArrayList<co.edu.unbosque.modelo.Coord> coordsCamino) {
		MazeBFS.coordsCamino = coordsCamino;
	}
}
