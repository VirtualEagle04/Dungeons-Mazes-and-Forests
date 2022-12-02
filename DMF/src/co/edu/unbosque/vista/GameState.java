package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import co.edu.unbosque.controlador.Controlador;
import co.edu.unbosque.modelo.Coord;

public class GameState extends JLayeredPane {

	private Controlador c;
	private JLabel[] celdas;
	private JLayeredPane tablero;
	private PlayerRender player;
	private EnemyRender enemy;
	private KeyRender key;
	private int[][] mazeMatrix;
	private JLabel gsBackground, esc_button, pause_text;
	private Sound GmMusic;
	private Font Alagard;

	private ArrayList<KeyRender> listaLlaves;
	private int listIndex = 0;

	public GameState() {
		listaLlaves = new ArrayList<KeyRender>();
		// Inicialización y Empaquetamiento de la Fuente

		try {
			Alagard = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Fonts/alagard.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//
		
		setSize(1024, 768);
		setLocation(0, 0);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		esc_button = new JLabel(new ImageIcon("src/Assets/Images/key_esc.png"));
		esc_button.setBounds(0,15,128,64);
		
		pause_text = new JLabel();
		pause_text.setText("Pause");
		pause_text.setBounds(30,85,128,25);
		pause_text.setFont(Alagard);
		pause_text.setForeground(Color.white);
		pause_text.setFont(pause_text.getFont().deriveFont(Font.ITALIC, 25));
		
		
		gsBackground = new JLabel(new ImageIcon("src/Assets/Gifs/gsBackground.gif"));
		gsBackground.setLocation(0, 0);
		gsBackground.setSize(1024, 768);
		
		GmMusic = new Sound();

	}

	public void drawMaze() {
		c = new Controlador();

		tablero = new JLayeredPane();
		tablero.setBackground(Color.WHITE);
		tablero.setLayout(null);
		tablero.setSize(32 * (c.getColumns() + 2), 32 * (c.getRows() + 2));
		int mitadX = tablero.getWidth() / 2;
		int mitadY = tablero.getHeight() / 2;
		tablero.setLocation(512 - mitadX, 364 - mitadY);

		celdas = new JLabel[(c.getRows() + 2) * (c.getColumns() + 2)];
		mazeMatrix = c.getMazeMatrix();

		int pos_X = 0;
		int pos_Y = 0;

		// Rows
		for (int i = 0; i < mazeMatrix.length; i++) {
			// Columns
			for (int j = 0; j < mazeMatrix[i].length; j++) {
				if (mazeMatrix[i][j] == 1) {

					// Last row
					if (i == mazeMatrix.length - 1) {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/Top.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
					} else {
						// Top
						try {
							if (mazeMatrix[i + 1][j] == 1) {
								celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/Top.png"));
								celdas[i].setSize(32, 32);
								celdas[i].setLocation(pos_X, pos_Y);
								pos_X += 32;
								tablero.add(celdas[i]);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/Top.png"));
							celdas[i].setSize(32, 32);
							celdas[i].setLocation(pos_X, pos_Y);
							pos_X += 32;
							tablero.add(celdas[i]);
						}
						// IndWall
						try {
							if (mazeMatrix[i + 1][j] != 1) {
								celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/IndWall.png"));
								celdas[i].setSize(32, 32);
								celdas[i].setLocation(pos_X, pos_Y);
								pos_X += 32;
								tablero.add(celdas[i]);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/IndWall.png"));
							celdas[i].setSize(32, 32);
							celdas[i].setLocation(pos_X, pos_Y);
							pos_X += 32;
							tablero.add(celdas[i]);
						}
					}

				}
				if (mazeMatrix[i][j] == 0) {

					Random rnd = new Random();
					int temp = rnd.nextInt(((8 - 0) + 1) + 0);

					switch (temp) {
					case 0: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor1.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 1: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor2.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 2: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor3.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 3: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor4.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 4: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor6.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 5: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor7.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 6: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor8.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 7: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor9.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					case 8: {
						celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor10.png"));
						celdas[i].setSize(32, 32);
						celdas[i].setLocation(pos_X, pos_Y);
						pos_X += 32;
						tablero.add(celdas[i]);
						break;
					}
					default:
					}

				}
				
				// Lethal
				if (mazeMatrix[i][j] == 2) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor10.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);

					EnemyRender lethal = new EnemyRender();
					lethal.lethalRender();
					lethal.setLocation(celdas[i].getLocation());
					tablero.add(lethal, JLayeredPane.MODAL_LAYER);
				}
				
				//Stormy
				if(mazeMatrix[i][j] == 6) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor10.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);
					
					EnemyRender stormy = new EnemyRender();
					stormy.stormyRender();
					stormy.setLocation(celdas[i].getLocation());
					tablero.add(stormy, JLayeredPane.MODAL_LAYER);
				}

				//Llave
				
				if (mazeMatrix[i][j] == 5) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor4.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);
					
					listaLlaves.add(new KeyRender(i, j));
					listaLlaves.get(listIndex).setLocation(celdas[i].getLocation());
					tablero.add(listaLlaves.get(listIndex), JLayeredPane.MODAL_LAYER);
					listIndex++;

//					key = new KeyRender();
//					key.setLocation(celdas[i].getLocation());
//					tablero.add(key, JLayeredPane.MODAL_LAYER);
					

				}

				//Entrada
				if (mazeMatrix[i][j] == 3) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/WallTileStart.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setBackground(Color.GREEN);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);

					player = new PlayerRender();
					player.setLocation(celdas[i].getLocation());
					tablero.add(player, JLayeredPane.DRAG_LAYER);

				}
				//Salida
				if (mazeMatrix[i][j] == 4) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Wall/WallTileFinish.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i]);
				}
			}
			pos_X = 0;
			pos_Y += 32;
		}

		add(esc_button);
		add(pause_text);
		add(tablero);
		add(gsBackground, JLayeredPane.DEFAULT_LAYER);
		
	}
	
	

	public EnemyRender getEnemy() {
		return enemy;
	}

	public void setEnemy(EnemyRender enemy) {
		this.enemy = enemy;
	}

	public KeyRender getKey() {
		return key;
	}

	public void setKey(KeyRender key) {
		this.key = key;
	}

	public JLabel getGsBackground() {
		return gsBackground;
	}

	public void setGsBackground(JLabel gsBackground) {
		this.gsBackground = gsBackground;
	}

	public JLabel getEsc_button() {
		return esc_button;
	}

	public void setEsc_button(JLabel esc_button) {
		this.esc_button = esc_button;
	}

	public JLabel getPause_text() {
		return pause_text;
	}

	public void setPause_text(JLabel pause_text) {
		this.pause_text = pause_text;
	}

	public Sound getGmMusic() {
		return GmMusic;
	}

	public void setGmMusic(Sound gmMusic) {
		GmMusic = gmMusic;
	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public Controlador getC() {
		return c;
	}

	public void setC(Controlador c) {
		this.c = c;
	}

	public JLabel[] getCeldas() {
		return celdas;
	}

	public void setCeldas(JLabel[] celdas) {
		this.celdas = celdas;
	}

	public JLayeredPane getTablero() {
		return tablero;
	}

	public void setTablero(JLayeredPane tablero) {
		this.tablero = tablero;
	}

	public int[][] getMazeMatrix() {
		return mazeMatrix;
	}

	public void setMazeMatrix(int[][] mazeMatrix) {
		this.mazeMatrix = mazeMatrix;
	}

	public PlayerRender getPlayer() {
		return player;
	}

	public void setPlayer(PlayerRender player) {
		this.player = player;
	}
	
	public void playMusic(int i) {

		GmMusic.setFile(i);
		GmMusic.play();
		GmMusic.loop();
	}

	public void stopMusic(int i) {

		GmMusic.stop(i);
	}
	
	public void playSE(int i) {
		
		GmMusic.setFile(i);
		GmMusic.play();
	}

	public ArrayList<KeyRender> getListaLlaves() {
		return listaLlaves;
	}

	public void setListaLlaves(ArrayList<KeyRender> listaLlaves) {
		this.listaLlaves = listaLlaves;
	}

}
