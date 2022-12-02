package co.edu.unbosque.modelo;

import java.util.ArrayList;
import java.util.Random;

import co.edu.unbosque.controlador.Controlador;

public class KeyGeneratorMatrix {
	private Controlador c;
	private Random rnd;
	private int llaves;
	private int[][] matrizConLlaves;
	private ArrayList<Coord> coordsLlaves;
	
	public KeyGeneratorMatrix(int[][] matrizSinLlaves, ArrayList<Coord> coordsCamino) {
		rnd = new Random();
		llaves = c.getKeys();
		matrizConLlaves = matrizSinLlaves;
		coordsLlaves = new ArrayList<Coord>();
		
		for (int i = 0; i < llaves; i++) {
			int index_rnd = rnd.nextInt(coordsCamino.size() - 0) + 0;
			
			int nuevaLlave_posRow = coordsCamino.get(index_rnd).getRow();
			int nuevaLlave_posCol = coordsCamino.get(index_rnd).getCol();
			
			coordsCamino.remove(index_rnd);
			
			matrizConLlaves[nuevaLlave_posRow][nuevaLlave_posCol] = 5;
			coordsLlaves.add(new Coord(nuevaLlave_posRow, nuevaLlave_posCol));
			
		}
	}

	public int[][] getMatrizConLlaves() {
		return matrizConLlaves;
	}

	public void setMatrizConLlaves(int[][] matrizConLlaves) {
		this.matrizConLlaves = matrizConLlaves;
	}

	public ArrayList<Coord> getCoordsLlaves() {
		return coordsLlaves;
	}

	public void setCoordsLlaves(ArrayList<Coord> coordsLlaves) {
		this.coordsLlaves = coordsLlaves;
	}
	
}
