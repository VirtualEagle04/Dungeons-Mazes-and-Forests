package co.edu.unbosque.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import co.edu.unbosque.modelo.MazeGeneratorMatrix;
import co.edu.unbosque.vista.EnemyRender;
import co.edu.unbosque.vista.GameFrame;
import co.edu.unbosque.vista.KeyRender;
import co.edu.unbosque.modelo.Coord;
import co.edu.unbosque.modelo.KeyGeneratorMatrix;
import co.edu.unbosque.modelo.MazeBFS;

public class Controlador implements ActionListener, KeyListener, ChangeListener {



	// Conexiones
	private AplMain aplMain = new AplMain();
	private GameFrame gameFrame;
	private MazeGeneratorMatrix mazeGen;
	private MazeBFS mazeBFS;
	private KeyGeneratorMatrix keyGen;

	// Info del Laberinto
	private static int columns;
	private static int rows;
	private static int keys;
	private static int lethal;
	private static int stormy;
	private static int[][] mazeMatrix;
	

	// Movimiento
	private boolean upP, downP, leftP, rightP;
	private int posX, posY, desplazamiento;
	private int llaves_acumuladas;
	private int mov_max;
	private int mov_actuales = 0;
	private int llaves_restantes;

	// Varios
	private static int apariencia;
	private int intentos_generacion;
	boolean actualInvert = false;
	boolean actualESC = false;
	private ArrayList<Coord> coordsCamino;
	private ArrayList<KeyRender> listaLlaves;
	private ArrayList<EnemyRender> listaLethal;
	private ArrayList<EnemyRender> listaStormy;
	private float globalVolume;
	private String select_button;

	// Contructor donde no tiene que ir NADA
	public Controlador() {
	}

	public void run() {

		gameFrame = new GameFrame();
		agregarLectores();

	}

	public void newGame(int rows, int columns) {
		boolean generar = false;

		// Generar laberintos hasta generar uno con solucion
		do {
			mazeGen = new MazeGeneratorMatrix(rows, columns);
			mazeMatrix = mazeGen.getMaze();

			// BFS Maze
			mazeBFS = new MazeBFS(mazeMatrix);
			boolean laberinto = mazeBFS.isSolucion();
			coordsCamino = mazeBFS.getCoordsCamino();

			if (laberinto) {
				generar = true;
			}
			intentos_generacion++;
		} while (generar == false);

		keyGen = new KeyGeneratorMatrix(mazeMatrix, coordsCamino);

		mazeMatrix = keyGen.getMatrizConLlaves();
		mov_max = rows * columns;

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if (gameFrame.getpState().isVisible()) {
			//Menu de Pausa = no mover
		} else {
			if(gameFrame.geteState().getLost_panel().isVisible() == false) {
				
				//Condicion para restringir el movimiento una vez haya usado todos los movimientos posibles
				if(mov_actuales < mov_max) {

					// Movimiento
					if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {

						// Cuando el jugador trata de moverse fuera del length de la matriz
						try {
							if (mazeMatrix[(posY / 32) - 1][posX / 32] != 1) {
								
								upP = true;
								gameFrame.getGameState().getPlayer().setLocation(posX, posY - desplazamiento);
								gameFrame.getGameState().getPlayer().repaint();
								posX = gameFrame.getGameState().getPlayer().getX();
								posY = gameFrame.getGameState().getPlayer().getY();
								mov_actuales++;
								gameFrame.getGameState().getRestant_movements().setText(String.valueOf(mov_max-mov_actuales));
								
								//Entra al incio
								if(mazeMatrix[posY / 32][posX / 32] == 3) {
									mazeMatrix[posY / 32][posX / 32] = 3;
								}
								//Entra a la salida
								if(mazeMatrix[posY / 32][posX / 32] == 4) {
									mazeMatrix[posY / 32][posX / 32] = 4;
								}
								
								// Recoleccion de las llaves
								listaLlaves = gameFrame.getGameState().getListaLlaves();
								if (mazeMatrix[posY / 32][posX / 32] == 5) {
									
									for (KeyRender key_render : listaLlaves) {
										if (((posY / 32) == key_render.getPosRow())
												&& ((posX / 32) == key_render.getPosCol())) {
											key_render.setVisible(false);
											llaves_acumuladas++;
											llaves_restantes--;
											gameFrame.getGameState().getRestant_keys().setText(String.valueOf(llaves_restantes));
											gameFrame.getGameState().playSE(10);
											mazeMatrix[posY / 32][posX / 32] = 0;
											break;
										}
									}
								}
								//Acaba de salir 
								if(mov_actuales == 1) {
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								if(mazeMatrix[(posY / 32) + 1][posX / 32] == 9) {
									mazeMatrix[(posY / 32) + 1][posX / 32] = 0;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								//Sale del final
								if(mazeMatrix[(posY / 32) + 1][posX / 32] == 4) {
									mazeMatrix[(posY / 32) + 1][posX / 32] = 4;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								
								
								
								
								
								
								//Movimiento de los enemigos
								listaLethal = gameFrame.getGameState().getListaLethal();
								listaStormy = gameFrame.getGameState().getListaStormy();
								Random rnd = new Random();
								
								for (EnemyRender lethal_enemy : listaLethal) {
									int lethal_posX = lethal_enemy.getX();
									int lethal_posY = lethal_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32) -1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {

										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) - 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()-desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) + 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()+desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
								} //Para cada Lethal
									
								
								for (EnemyRender stormy_enemy : listaStormy) {
									int stormy_posX = stormy_enemy.getX();
									int stormy_posY = stormy_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) - 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) + 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) - 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()-desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) + 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()+desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
								} //Para cada Stormy
								
								//Verificar contacto con el enemigo Lethal en la cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								
								//Verificar contacto con el enemigo Stormy en las cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								
//								//Debug
//								for(int i = 0; i < mazeMatrix.length; i++) {
//									for (int j = 0; j < mazeMatrix[i].length; j++) {
//										System.out.print(mazeMatrix[i][j]+" ");
//									}
//									System.out.println();
//								}
//								System.out.println();
								
							}
						} catch (ArrayIndexOutOfBoundsException e2) {
						}

					}
					if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
						try {
							if (mazeMatrix[(posY / 32) + 1][posX / 32] != 1) {

								downP = true;
								gameFrame.getGameState().getPlayer().setLocation(posX, posY + desplazamiento);
								gameFrame.getGameState().getPlayer().repaint();
								posX = gameFrame.getGameState().getPlayer().getX();
								posY = gameFrame.getGameState().getPlayer().getY();
								mov_actuales++;
								gameFrame.getGameState().getRestant_movements().setText(String.valueOf(mov_max-mov_actuales));
								
								//Entra al inicio
								if(mazeMatrix[posY / 32][posX / 32] == 3) {
									mazeMatrix[posY / 32][posX / 32] = 3;
								}
								//Entra a la salida
								if(mazeMatrix[posY / 32][posX / 32] == 4) {
									mazeMatrix[posY / 32][posX / 32] = 4;
									mazeMatrix[(posY / 32) - 1][posX / 32] = 0;
								}
								
								// Recoleccion de las llaves
								listaLlaves = gameFrame.getGameState().getListaLlaves();
								if ((mazeMatrix[posY / 32][posX / 32] == 5)) {
									for (KeyRender key_render : listaLlaves) {
										if (((posY / 32) == key_render.getPosRow())
												&& ((posX / 32) == key_render.getPosCol())) {
											key_render.setVisible(false);
											llaves_acumuladas++;
											llaves_restantes--;
											gameFrame.getGameState().getRestant_keys().setText(String.valueOf(llaves_restantes));
											gameFrame.getGameState().playSE(10);
											mazeMatrix[posY / 32][posX / 32] = 0;
											break;
										}
									}
								}
								//Acaba de salir 
								if(mov_actuales == 1) {
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								if(mazeMatrix[(posY / 32) - 1][posX / 32] == 9) {
									mazeMatrix[(posY / 32) - 1][posX / 32] = 0;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}

								
								
								//Movimiento de los enemigos
								listaLethal = gameFrame.getGameState().getListaLethal();
								listaStormy = gameFrame.getGameState().getListaStormy();
								Random rnd = new Random();
								
								for (EnemyRender lethal_enemy : listaLethal) {
									int lethal_posX = lethal_enemy.getX();
									int lethal_posY = lethal_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32) -1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {

										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) - 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()-desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) + 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()+desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
								} //Para cada Lethal
									
								
								for (EnemyRender stormy_enemy : listaStormy) {
									int stormy_posX = stormy_enemy.getX();
									int stormy_posY = stormy_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) - 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) + 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) - 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()-desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) + 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()+desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
								} //Para cada Stormy
								
								//Verificar contacto con el enemigo Lethal en la cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								
								//Verificar contacto con el enemigo Stormy en las cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								
//								//Debug
//								for(int i = 0; i < mazeMatrix.length; i++) {
//									for (int j = 0; j < mazeMatrix[i].length; j++) {
//										System.out.print(mazeMatrix[i][j]+" ");
//									}
//									System.out.println();
//								}
//								System.out.println();
								
							}
						} catch (ArrayIndexOutOfBoundsException e2) {
						}

					}
					if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
						try {
							if (mazeMatrix[posY / 32][(posX / 32) - 1] != 1) {

								setLeftP(true);
								gameFrame.getGameState().getPlayer().setLocation(posX - desplazamiento, posY);
								gameFrame.getGameState().getPlayer().repaint();
								posX = gameFrame.getGameState().getPlayer().getX();
								posY = gameFrame.getGameState().getPlayer().getY();
								mov_actuales++;
								gameFrame.getGameState().getRestant_movements().setText(String.valueOf(mov_max-mov_actuales));

								//Entra al inicio
								if(mazeMatrix[posY / 32][posX / 32] == 3) {
									mazeMatrix[posY / 32][posX / 32] = 3;
								}
								//Entra a la salida
								if(mazeMatrix[posY / 32][posX / 32] == 4) {
									mazeMatrix[posY / 32][posX / 32] = 4;
								}
								
								// Recoleccion de las llaves
								listaLlaves = gameFrame.getGameState().getListaLlaves();
								if (mazeMatrix[posY / 32][posX / 32] == 5) {
									for (KeyRender key_render : listaLlaves) {
										if (((posY / 32) == key_render.getPosRow())
												&& ((posX / 32) == key_render.getPosCol())) {
											key_render.setVisible(false);
											llaves_acumuladas++;
											llaves_restantes--;
											gameFrame.getGameState().getRestant_keys().setText(String.valueOf(llaves_restantes));
											gameFrame.getGameState().playSE(10);
											mazeMatrix[posY / 32][posX / 32] = 0;
											break;
										}
									}
								}
								//Acaba de salir 
								if(mov_actuales == 1) {
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								if(mazeMatrix[posY / 32][(posX / 32) + 1] == 9) {
									mazeMatrix[posY / 32][(posX / 32) + 1] = 0;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								//Sale del final
								if(mazeMatrix[posY / 32][(posX / 32) + 1] == 4) {
									mazeMatrix[posY / 32][(posX / 32) + 1] = 4;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}

								
								
								
								//Movimiento de los enemigos
								listaLethal = gameFrame.getGameState().getListaLethal();
								listaStormy = gameFrame.getGameState().getListaStormy();
								Random rnd = new Random();
								
								for (EnemyRender lethal_enemy : listaLethal) {
									int lethal_posX = lethal_enemy.getX();
									int lethal_posY = lethal_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32) -1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {

										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) - 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()-desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) + 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()+desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
								} //Para cada Lethal
									
								
								for (EnemyRender stormy_enemy : listaStormy) {
									int stormy_posX = stormy_enemy.getX();
									int stormy_posY = stormy_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) - 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) + 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) - 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()-desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) + 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()+desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
								} //Para cada Stormy
								
								//Verificar contacto con el enemigo Lethal en la cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								
								//Verificar contacto con el enemigo Stormy en las cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								
//								//Debug
//								for(int i = 0; i < mazeMatrix.length; i++) {
//									for (int j = 0; j < mazeMatrix[i].length; j++) {
//										System.out.print(mazeMatrix[i][j]+" ");
//									}
//									System.out.println();
//								}
//								System.out.println();
								
							}
						} catch (ArrayIndexOutOfBoundsException e2) {
						}

					}
					if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
						try {
							if (mazeMatrix[posY / 32][(posX / 32) + 1] != 1) {

								rightP = true;
								gameFrame.getGameState().getPlayer().setLocation(posX + desplazamiento, posY);
								gameFrame.getGameState().getPlayer().repaint();
								posX = gameFrame.getGameState().getPlayer().getX();
								posY = gameFrame.getGameState().getPlayer().getY();
								mov_actuales++;
								gameFrame.getGameState().getRestant_movements().setText(String.valueOf(mov_max-mov_actuales));

								//Entra al inicio
								if(mazeMatrix[posY / 32][posX / 32] == 3) {
									mazeMatrix[posY / 32][posX / 32] = 3;
								}
								//Entra a la salida
								if(mazeMatrix[posY / 32][posX / 32] == 4) {
									mazeMatrix[posY / 32][posX / 32] = 4;
									mazeMatrix[posY / 32][(posX / 32) - 1] = 0;
								}
								
								
								// Recoleccion de las llaves
								listaLlaves = gameFrame.getGameState().getListaLlaves();
								if ((mazeMatrix[posY / 32][posX / 32] == 5)) {
									for (KeyRender key_render : listaLlaves) {
										if (((posY / 32) == key_render.getPosRow())
												&& ((posX / 32) == key_render.getPosCol())) {
											key_render.setVisible(false);
											llaves_acumuladas++;
											llaves_restantes--;
											gameFrame.getGameState().getRestant_keys().setText(String.valueOf(llaves_restantes));
											gameFrame.getGameState().playSE(10);
											mazeMatrix[posY / 32][posX / 32] = 0;
											break;
										}
									}
								}
								//Acaba de salir 
								if(mov_actuales == 1) {
									mazeMatrix[posY / 32][posX / 32] = 9;
								}
								if(mazeMatrix[posY / 32][(posX / 32) - 1] == 9) {
									mazeMatrix[posY / 32][(posX / 32) - 1] = 0;
									mazeMatrix[posY / 32][posX / 32] = 9;
								}

								
								//Movimiento de los enemigos
								listaLethal = gameFrame.getGameState().getListaLethal();
								listaStormy = gameFrame.getGameState().getListaStormy();
								Random rnd = new Random();
								
								for (EnemyRender lethal_enemy : listaLethal) {
									int lethal_posX = lethal_enemy.getX();
									int lethal_posY = lethal_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)-1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32) -1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 1) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 5)
										&&	(mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 3) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 4)
										&& (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 9) && (mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[(lethal_posY / 32)+1][lethal_posX / 32] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX(), lethal_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {

										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)-1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) - 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()-desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 1) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 5)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 3) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 4)
										&& (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 9) && (mazeMatrix[(lethal_posY / 32)][(lethal_posX / 32)+1] != 6)){
											
											mazeMatrix[lethal_posY / 32][lethal_posX / 32] = 0; //La posicion previa es cero ahora
											mazeMatrix[lethal_posY / 32][(lethal_posX / 32) + 1] = 2; //La posicion nueva es 2, donde está el enemigo.
											lethal_enemy.setLocation(lethal_enemy.getX()+desplazamiento, lethal_enemy.getY());
											gameFrame.getGameState().repaint();
											lethal_enemy.repaint();
											break;
										}
									}
								} //Para cada Lethal
									
								
								for (EnemyRender stormy_enemy : listaStormy) {
									int stormy_posX = stormy_enemy.getX();
									int stormy_posY = stormy_enemy.getY();
									
									
									int direccion = rnd.nextInt(5 - 1) + 1;
									
									if(direccion == 1) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)-1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) - 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()-desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 2) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 1) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 5)
										&& (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 3) && (mazeMatrix[(stormy_posY / 32)+1][stormy_posX / 32] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[(stormy_posY / 32) + 1][stormy_posX / 32] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX(), stormy_enemy.getY()+desplazamiento);
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 3) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)-1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) - 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()-desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
									if(direccion == 4) {
										//Colisiones de los Enemigos
										if((mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 1) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 5)
										&& (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 3) && (mazeMatrix[(stormy_posY / 32)][(stormy_posX / 32)+1] != 4)){
											
											mazeMatrix[stormy_posY / 32][stormy_posX / 32] = 0;
											mazeMatrix[stormy_posY / 32][(stormy_posX / 32) + 1] = 6;
											stormy_enemy.setLocation(stormy_enemy.getX()+desplazamiento, stormy_enemy.getY());
											gameFrame.getGameState().repaint();
											stormy_enemy.repaint();
											break;
										}
									}
								} //Para cada Stormy
								
								//Verificar contacto con el enemigo Lethal en la cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 2) {
									System.out.println("Contacto con Lethal");
									gameFrame.geteState().setVisible(true);
									gameFrame.geteState().getLost_panel().setVisible(true);
									gameFrame.geteState().getVictory_panel().setVisible(false);
								}
								
								//Verificar contacto con el enemigo Stormy en las cuatro direcciones
								if (mazeMatrix[(posY / 32) - 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[(posY / 32) + 1][posX / 32] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) - 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								if (mazeMatrix[posY / 32][(posX / 32) + 1] == 6) {
									System.out.println("Contacto con Stormy");
									mov_max = mov_max-((5*mov_max)/100);
								}
								
//								//Debug
//								for(int i = 0; i < mazeMatrix.length; i++) {
//									for (int j = 0; j < mazeMatrix[i].length; j++) {
//										System.out.print(mazeMatrix[i][j]+" ");
//									}
//									System.out.println();
//								}
//								System.out.println();
								
							}
						} catch (ArrayIndexOutOfBoundsException e2) {
						}

					}

					// Chequea si la posicion actualizada actual es 4 (es decir, la salida)
					if (mazeMatrix[posY / 32][posX / 32] == 4) {
						
						if(llaves_acumuladas == keys) {
							System.out.println("Pestaña Victoria");
							gameFrame.geteState().setVisible(true);
							gameFrame.geteState().getLost_panel().setVisible(false);
							gameFrame.geteState().getVictory_panel().setVisible(true);
							gameFrame.getGameState().getAdvise_key().setVisible(false);
							
						}
						else if(llaves_acumuladas < keys) {
							System.out.println("Pestaña Perdio");
							gameFrame.getGameState().getAdvise_key().setVisible(true);
						}

					}
				}else if(mov_actuales == mov_max) {
					System.out.println("Ha usado todos los movimientos posibles");
				}


			}

		}
	// Pausa
	if(code==KeyEvent.VK_ESCAPE)

	{

		if (actualESC == false) {
			gameFrame.getpState().setVisible(true);
			gameFrame.getGameState().getPause_text().setVisible(false);
			gameFrame.getGameState().getEsc_button().setVisible(false);
			actualESC = true;
		} else if (actualESC = true) {
			gameFrame.getpState().setVisible(false);
			gameFrame.getGameState().getPause_text().setVisible(true);
			gameFrame.getGameState().getEsc_button().setVisible(true);

			actualESC = false;
		}

	}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upP = false;
		}
		if (code == KeyEvent.VK_S) {
			downP = false;
		}
		if (code == KeyEvent.VK_A) {
			setLeftP(false);
		}
		if (code == KeyEvent.VK_D) {
			rightP = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void agregarLectores() {
		// Title Buttons
		gameFrame.getTsState().getTsNewGame().addActionListener(this);
		gameFrame.getTsState().getTsNewGame().setActionCommand("new_game_button");

		gameFrame.getTsState().getTsTutorial().addActionListener(this);
		gameFrame.getTsState().getTsTutorial().setActionCommand("tutorial_button");

		gameFrame.getTsState().getTsCredits().addActionListener(this);
		gameFrame.getTsState().getTsCredits().setActionCommand("credits_button");

		gameFrame.getTsState().getTsQuit().addActionListener(this);
		gameFrame.getTsState().getTsQuit().setActionCommand("quit_button");

		// Character Select Buttons
		gameFrame.getChState().getCsMage().addActionListener(this);
		gameFrame.getChState().getCsMage().setActionCommand("csMage");

		gameFrame.getChState().getCsBarbarian().addActionListener(this);
		gameFrame.getChState().getCsBarbarian().setActionCommand("csBarbarian");

		gameFrame.getChState().getCsArcher().addActionListener(this);
		gameFrame.getChState().getCsArcher().setActionCommand("csArcher");

		gameFrame.getChState().getCsPaladin().addActionListener(this);
		gameFrame.getChState().getCsPaladin().setActionCommand("csPaladin");

		gameFrame.getChState().getCsRogue().addActionListener(this);
		gameFrame.getChState().getCsRogue().setActionCommand("csRogue");

		gameFrame.getChState().getCsWarrior().addActionListener(this);
		gameFrame.getChState().getCsWarrior().setActionCommand("csWarrior");

		gameFrame.getChState().getSelect_button().addActionListener(this);
		gameFrame.getChState().getSelect_button().setActionCommand("cs_select_button");

		// Back buttons
		gameFrame.getChState().getBack_button().addActionListener(this);
		gameFrame.getChState().getBack_button().setActionCommand("cs_back_button");

		gameFrame.getCrState().getBack_button().addActionListener(this);
		gameFrame.getCrState().getBack_button().setActionCommand("cr_back_button");

		gameFrame.getTuState().getBack_button().addActionListener(this);
		gameFrame.getTuState().getBack_button().setActionCommand("tu_back_button");

		gameFrame.getPrgState().getBack_button().addActionListener(this);
		gameFrame.getPrgState().getBack_button().setActionCommand("prg_back_button");

		// Pre-Game Start Button
		gameFrame.getPrgState().getStart_button().addActionListener(this);
		gameFrame.getPrgState().getStart_button().setActionCommand("prg_start_button");
		
		gameFrame.getPrgState().getInvert_button().addActionListener(this);
		gameFrame.getPrgState().getInvert_button().setActionCommand("prg_invert_button");

		// Pause Buttons
		gameFrame.getpState().getOptions_button().addActionListener(this);
		gameFrame.getpState().getOptions_button().setActionCommand("pause_options_button");

		gameFrame.getpState().getResume_button().addActionListener(this);
		gameFrame.getpState().getResume_button().setActionCommand("pause_resume_button");

		gameFrame.getpState().getBack_button().addActionListener(this);
		gameFrame.getpState().getBack_button().setActionCommand("pause_back_button");
		
		gameFrame.getpState().getInstructions_button().addActionListener(this);
		gameFrame.getpState().getInstructions_button().setActionCommand("pause_instruction_button");
		
		gameFrame.getpState().getMusic_volume().addChangeListener(this);
		
		// End State Buttons
		
		gameFrame.geteState().getBack_menu_lost().addActionListener(this);
		gameFrame.geteState().getBack_menu_lost().setActionCommand("lost_back_button");
		
		gameFrame.geteState().getCredits_button_lost().addActionListener(this);
		gameFrame.geteState().getCredits_button_lost().setActionCommand("lost_credits_button");
		
		gameFrame.geteState().getBack_menu_win().addActionListener(this);
		gameFrame.geteState().getBack_menu_win().setActionCommand("win_back_button");
		
		gameFrame.geteState().getCredits_button_win().addActionListener(this);
		gameFrame.geteState().getCredits_button_win().setActionCommand("win_credits_button");
		

	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		globalVolume = gameFrame.getpState().getMusic_volume().getValue();
		
		gameFrame.getGameState().getGmMusic().setCurrentVolume(globalVolume);
		gameFrame.getGameState().getGmMusic().getFc().setValue(gameFrame.getGameState().getGmMusic().getCurrentVolume());
		if(gameFrame.getGameState().getGmMusic().getCurrentVolume() == - 30) {
			gameFrame.getGameState().getGmMusic().setCurrentVolume( -80);		
			}
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "new_game_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic(0);
			gameFrame.getChState().playSE(6);
			gameFrame.getChState().playMusic(3);

			gameFrame.getChState().setVisible(true);

			break;
		}
		case "tutorial_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic(0);
			gameFrame.getTuState().playSE(7);
			gameFrame.getTuState().playMusic(4);

			gameFrame.getTuState().setVisible(true);
			break;
		}
		case "credits_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic(0);
			gameFrame.getCrState().playSE(7);
			gameFrame.getCrState().playMusic(2);

			gameFrame.getCrState().setVisible(true);
			break;
		}
		case "quit_button": {
			System.exit(0);
			break;
		}
		case "csMage": {

			gameFrame.getChState().getMage_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getWarrior_concept_art().setVisible(false);
			gameFrame.getChState().getPaladin_concept_art().setVisible(false);
			gameFrame.getChState().getRogue_concept_art().setVisible(false);
			gameFrame.getChState().getArcher_concept_art().setVisible(false);
			gameFrame.getChState().getBarbarian_concept_art().setVisible(false);

			break;
		}
		case "csWarrior": {

			gameFrame.getChState().getWarrior_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getPaladin_concept_art().setVisible(false);
			gameFrame.getChState().getRogue_concept_art().setVisible(false);
			gameFrame.getChState().getArcher_concept_art().setVisible(false);
			gameFrame.getChState().getBarbarian_concept_art().setVisible(false);
			gameFrame.getChState().getMage_concept_art().setVisible(false);

			break;
		}
		case "csPaladin": {

			gameFrame.getChState().getPaladin_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getMage_concept_art().setVisible(false);
			gameFrame.getChState().getRogue_concept_art().setVisible(false);
			gameFrame.getChState().getArcher_concept_art().setVisible(false);
			gameFrame.getChState().getBarbarian_concept_art().setVisible(false);
			gameFrame.getChState().getWarrior_concept_art().setVisible(false);

			break;
		}
		case "csRogue": {

			gameFrame.getChState().getRogue_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getMage_concept_art().setVisible(false);
			gameFrame.getChState().getArcher_concept_art().setVisible(false);
			gameFrame.getChState().getBarbarian_concept_art().setVisible(false);
			gameFrame.getChState().getWarrior_concept_art().setVisible(false);
			gameFrame.getChState().getPaladin_concept_art().setVisible(false);

			break;
		}
		case "csArcher": {

			gameFrame.getChState().getArcher_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getMage_concept_art().setVisible(false);
			gameFrame.getChState().getBarbarian_concept_art().setVisible(false);
			gameFrame.getChState().getWarrior_concept_art().setVisible(false);
			gameFrame.getChState().getPaladin_concept_art().setVisible(false);
			gameFrame.getChState().getRogue_concept_art().setVisible(false);

			break;
		}
		case "csBarbarian": {
			gameFrame.getChState().getBarbarian_concept_art().setVisible(true);
			gameFrame.getChState().getPanel_text().setVisible(false);
			gameFrame.getChState().getPanel_textShadow().setVisible(false);
			gameFrame.getChState().getMage_concept_art().setVisible(false);
			gameFrame.getChState().getWarrior_concept_art().setVisible(false);
			gameFrame.getChState().getPaladin_concept_art().setVisible(false);
			gameFrame.getChState().getRogue_concept_art().setVisible(false);
			gameFrame.getChState().getArcher_concept_art().setVisible(false);

			break;
		}
		case "cs_select_button": {

			select_button = "";

			boolean archer = gameFrame.getChState().getArcher_concept_art().isVisible();
			boolean warrior = gameFrame.getChState().getWarrior_concept_art().isVisible();
			boolean barbarian = gameFrame.getChState().getBarbarian_concept_art().isVisible();
			boolean mage = gameFrame.getChState().getMage_concept_art().isVisible();
			boolean paladin = gameFrame.getChState().getPaladin_concept_art().isVisible();
			boolean rogue = gameFrame.getChState().getRogue_concept_art().isVisible();

			if (rogue == true || warrior == true || barbarian == true || mage == true || paladin == true
					|| archer == true) {
				gameFrame.getPrgState().playSE(9);
			}

			if (archer == false && warrior == false && barbarian == false && mage == false && paladin == false
					&& rogue == false) {

				gameFrame.getChState().getCharacter_select().setVisible(true);

				break;

			} else if (archer == true) {
				select_button += "Archer";
				apariencia = 0;
			} else if (warrior == true) {
				select_button += "Warrior";
				apariencia = 1;
			} else if (barbarian == true) {
				select_button += "Barbarian";
				apariencia = 3;
			} else if (mage == true) {
				select_button += "Mage";
				apariencia = 5;
			} else if (paladin == true) {
				select_button += "Paladin";
				apariencia = 2;
			} else if (rogue == true) {
				select_button += "Rogue";
				apariencia = 4;
			}
			switch (select_button) {
			case "Archer": {

				gameFrame.getPrgState().getArcher_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Archer");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Archer");
				

				break;
			}
			case "Warrior": {

				gameFrame.getPrgState().getWarrior_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Warrior");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Warrior");
				

				break;
			}
			case "Paladin": {

				gameFrame.getPrgState().getPaladin_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Paladin");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Paladin");
				

				break;
			}
			case "Barbarian": {

				gameFrame.getPrgState().getBarbarian_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Barbarian");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Barbarian");
				

				break;
			}
			case "Rogue": {

				gameFrame.getPrgState().getRogue_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Rogue");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Rogue");
				

				break;
			}
			case "Mage": {

				gameFrame.getPrgState().getMage_concept_art().setVisible(true);
				gameFrame.getPrgState().getCharacter_name().setText("Mage");
				gameFrame.getPrgState().getCharacter_name_shadow().setText("Mage");
				

				break;
			}
			default:
			}
			gameFrame.getChState().setVisible(false);
			gameFrame.getPrgState().setVisible(true);

			break;
		}
		case "cs_back_button": {

			gameFrame.getChState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getChState().stopMusic(3);
			gameFrame.getTsState().playMusic(0);

			break;
		}
		case "cr_back_button": {

			gameFrame.getCrState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getCrState().stopMusic(2);
			gameFrame.getTsState().playMusic(0);

			break;
		}
		case "tu_back_button": {

			gameFrame.getTuState().setVisible(false);
			gameFrame.getTuState().stopMusic(4);
			gameFrame.getTsState().playMusic(0);

			gameFrame.getTsState().setVisible(true);

			break;
		}
		case "prg_back_button": {

			gameFrame.getPrgState().setVisible(false);
			gameFrame.getChState().setVisible(true);

			gameFrame.getPrgState().getEntrada_X().setText("");
			gameFrame.getPrgState().getEntrada_Y().setText("");
			gameFrame.getPrgState().getCantidad_llaves().setText("");
			gameFrame.getPrgState().getCantidad_lethal().setText("");
			gameFrame.getPrgState().getCantidad_stormy().setText("");

			gameFrame.getPrgState().getEntrada_X().setBackground(Color.DARK_GRAY);
			gameFrame.getPrgState().getEntrada_Y().setBackground(Color.DARK_GRAY);

			gameFrame.getPrgState().getBarbarian_concept_art().setVisible(false);
			gameFrame.getPrgState().getArcher_concept_art().setVisible(false);
			gameFrame.getPrgState().getWarrior_concept_art().setVisible(false);
			gameFrame.getPrgState().getRogue_concept_art().setVisible(false);
			gameFrame.getPrgState().getMage_concept_art().setVisible(false);
			gameFrame.getPrgState().getPaladin_concept_art().setVisible(false);
			gameFrame.getChState().getCharacter_select().setVisible(false);

			gameFrame.getPrgState().getCantidad_llaves().setBackground(Color.DARK_GRAY);
			gameFrame.getPrgState().getInvert_button().setBackground(Color.BLACK);
			gameFrame.getPrgState().getInvert_button().setForeground(Color.WHITE);

			break;
		}
		case "prg_start_button": {
			// Resetea todos los valores
			int confirmacion_gen = 0;

			// Recoge los valores ingresados en el JTextField, y los convierte a Integer
			rows = Integer.parseInt(gameFrame.getPrgState().getEntrada_Y().getText());
			columns = Integer.parseInt(gameFrame.getPrgState().getEntrada_X().getText());
			keys = Integer.parseInt(gameFrame.getPrgState().getCantidad_llaves().getText());
			lethal = Integer.parseInt(gameFrame.getPrgState().getCantidad_lethal().getText());
			stormy = Integer.parseInt(gameFrame.getPrgState().getCantidad_stormy().getText());
			llaves_restantes = keys;

			// Condicional para las dimensiones del Laberinto
			if ((rows < 5 || rows > 20) || (columns < 5 || columns > 20)) {
				gameFrame.getPrgState().getEntrada_Y().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_Y().setText("");

				gameFrame.getPrgState().getEntrada_X().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_X().setText("");

			} else {
				gameFrame.getPrgState().getEntrada_Y().setBackground(Color.DARK_GRAY);
				gameFrame.getPrgState().getEntrada_X().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}
			// Condicional de las Llaves
			if (keys < 2 || keys > 5) {
				gameFrame.getPrgState().getCantidad_llaves().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_llaves().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_llaves().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}
			// Condicional de los Asesinos Letales
			if (lethal < 1 || lethal > keys) {
				gameFrame.getPrgState().getCantidad_lethal().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_lethal().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_lethal().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}
			// Condicional de los Tormentosos
			if (stormy < 1 || stormy > keys) {
				gameFrame.getPrgState().getCantidad_stormy().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_stormy().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_stormy().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}
			//Verificacion para todas las condiciones anteriores
			if (confirmacion_gen == 4) {
				gameFrame.getGameState().playMusic(5);
				this.newGame(rows, columns);

				gameFrame.getPrgState().setVisible(false);
				gameFrame.getChState().stopMusic(3);

				// Dibuja y Muestra el laberinto
				gameFrame.getGameState().drawMaze();
				gameFrame.getGameState().setVisible(true);

				gameFrame.addKeyListener(this);
				posX = gameFrame.getGameState().getPlayer().getX();
				posY = gameFrame.getGameState().getPlayer().getY();
				desplazamiento = 32;
			}
			break;

		}
		case "prg_invert_button": {
			//actualInvert	
			
			if(actualInvert == false) {
				actualInvert = true;
				gameFrame.getPrgState().getInvert_button().setBackground(Color.LIGHT_GRAY);
				gameFrame.getPrgState().getInvert_button().setForeground(Color.BLACK);
			}
			else if(actualInvert == true) {
				actualInvert = false;
				gameFrame.getPrgState().getInvert_button().setBackground(Color.BLACK);
				gameFrame.getPrgState().getInvert_button().setForeground(Color.WHITE);
			}
			break;
		}
		case "pause_options_button": {


			boolean options = gameFrame.getpState().getPanel_op().isVisible();
			boolean instructions = gameFrame.getpState().getPanel_tu().isVisible();

			if (options == true) {
				gameFrame.getpState().getPanel_op().setVisible(false);
			} else {
				gameFrame.getpState().getPanel_op().setVisible(true);
			}

			if (instructions == true) {
				gameFrame.getpState().getPanel_tu().setVisible(false);
			}

			break;
		}
		case "pause_resume_button": {

			gameFrame.getpState().setVisible(false);
			this.actualESC = false;

			break;
		}
		case "pause_back_button": {

			gameFrame.getpState().setVisible(false);
			gameFrame.getGameState().setVisible(false);

			gameFrame.getTsState().setVisible(true);

			break;
		}
		case "pause_instruction_button": {

			gameFrame.dispose();
			
			gameFrame.getpState().getPanel_tu().setVisible(true);
			gameFrame.getpState().getPanel_op().setVisible(false);
			break;

		}
		case "lost_back_button": {
			

			break;
		}
		case "lost_credits_button": {


			break;
		}
		case "win_back_button": {


			break;
		}
		case "win_credits_button": {


			break;
		}
		default:
		}
	}

	// Getters & Setters
	public String getSelect_button() {
		return select_button;
	}

	public void setSelect_button(String select_button) {
		this.select_button = select_button;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public MazeGeneratorMatrix getMazeGen() {
		return mazeGen;
	}

	public void setMazeGen(MazeGeneratorMatrix mazeGen) {
		this.mazeGen = mazeGen;
	}

	public MazeBFS getMazeBFS() {
		return mazeBFS;
	}

	public void setMazeBFS(MazeBFS mazeBFS) {
		this.mazeBFS = mazeBFS;
	}

	public KeyGeneratorMatrix getKeyGen() {
		return keyGen;
	}

	public void setKeyGen(KeyGeneratorMatrix keyGen) {
		this.keyGen = keyGen;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		Controlador.columns = columns;
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		Controlador.rows = rows;
	}

	public static int getKeys() {
		return keys;
	}

	public static void setKeys(int keys) {
		Controlador.keys = keys;
	}

	public static int getLethal() {
		return lethal;
	}

	public static void setLethal(int lethal) {
		Controlador.lethal = lethal;
	}

	public static int getStormy() {
		return stormy;
	}

	public static void setStormy(int stormy) {
		Controlador.stormy = stormy;
	}

	public static int[][] getMazeMatrix() {
		return mazeMatrix;
	}

	public static void setMazeMatrix(int[][] mazeMatrix) {
		Controlador.mazeMatrix = mazeMatrix;
	}

	public boolean isUpP() {
		return upP;
	}

	public void setUpP(boolean upP) {
		this.upP = upP;
	}

	public boolean isDownP() {
		return downP;
	}

	public void setDownP(boolean downP) {
		this.downP = downP;
	}

	public boolean isLeftP() {
		return leftP;
	}

	public void setLeftP(boolean leftP) {
		this.leftP = leftP;
	}

	public boolean isRightP() {
		return rightP;
	}

	public void setRightP(boolean rightP) {
		this.rightP = rightP;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(int desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public int getIntentos_generacion() {
		return intentos_generacion;
	}

	public void setIntentos_generacion(int intentos_generacion) {
		this.intentos_generacion = intentos_generacion;
	}

	public boolean isActualESC() {
		return actualESC;
	}

	public void setActualESC(boolean actualESC) {
		this.actualESC = actualESC;
	}

	public ArrayList<co.edu.unbosque.modelo.Coord> getCoordsCamino() {
		return coordsCamino;
	}

	public void setCoordsCamino(ArrayList<co.edu.unbosque.modelo.Coord> coordsCamino) {
		this.coordsCamino = coordsCamino;
	}

	public int getApariencia() {
		return apariencia;
	}

	public void setApariencia(int apariencia) {
		this.apariencia = apariencia;
	}

	public int getMov_max() {
		return mov_max;
	}

	public void setMov_max(int mov_max) {
		this.mov_max = mov_max;
	}

	public int getMov_actuales() {
		return mov_actuales;
	}

	public void setMov_actuales(int mov_actuales) {
		this.mov_actuales = mov_actuales;
	}

	public int getLlaves_acumuladas() {
		return llaves_acumuladas;
	}

	public void setLlaves_acumuladas(int llaves_acumuladas) {
		this.llaves_acumuladas = llaves_acumuladas;
	}
}
