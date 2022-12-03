package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import co.edu.unbosque.controlador.Controlador;

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
	private ArrayList<EnemyRender> listaLethal;
	private ArrayList<EnemyRender> listaStormy;

	private int listIndexKeys = 0;
	private int listIndexLethal = 0;
	private int listIndexStormy = 0;

	// keys & movement indicator

	private JTextArea key_indicator, key_indicator_shadow;
	private JLabel restant_keys;

	private JTextArea movement_indicator, movement_indicator_shadow, advise_key;
	private JLabel restant_movements;

	public GameState() {
		c = new Controlador();
		listaLlaves = new ArrayList<KeyRender>();
		listaLethal = new ArrayList<EnemyRender>();
		listaStormy = new ArrayList<EnemyRender>();
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

		// movements indicators

		movement_indicator = new JTextArea();
		movement_indicator.setText("Remaining \nMoves:");
		movement_indicator.setFont(Alagard);
		movement_indicator.setFont(movement_indicator.getFont().deriveFont(Font.BOLD, 20));
		movement_indicator.setBackground(new Color(0, 0, 0, 0));
		movement_indicator.setForeground(Color.white);
		movement_indicator.setBounds(20, 160, 120, 40);
		movement_indicator.setEditable(false);

		movement_indicator_shadow = new JTextArea();
		movement_indicator_shadow.setText("Remaining \nMoves:");
		movement_indicator_shadow.setFont(Alagard);
		movement_indicator_shadow.setFont(movement_indicator.getFont().deriveFont(Font.BOLD, 20));
		movement_indicator_shadow.setBackground(new Color(0, 0, 0, 0));
		movement_indicator_shadow.setForeground(Color.DARK_GRAY);
		movement_indicator_shadow.setBounds(23, 163, 120, 40);
		movement_indicator_shadow.setEditable(false);

		restant_movements = new JLabel();
		restant_movements.setText(String.valueOf(c.getMov_max()));
		restant_movements.setFont(Alagard);
		restant_movements.setFont(restant_movements.getFont().deriveFont(Font.BOLD, 18));
		restant_movements.setForeground(Color.white);
		restant_movements.setBounds(110, 183, 45, 20);

		//keys indicators
		
		key_indicator = new JTextArea();
		key_indicator.setText("Remaining \nKeys:");
		key_indicator.setFont(Alagard);
		key_indicator.setFont(key_indicator.getFont().deriveFont(Font.BOLD,20));
		key_indicator.setBackground(new Color(0,0,0,0));
		key_indicator.setForeground(Color.white);
		key_indicator.setBounds(20,230,120,120);
		key_indicator.setEditable(false);
		
		key_indicator_shadow = new JTextArea();
		key_indicator_shadow.setText("Remaining \nKeys:");
		key_indicator_shadow.setFont(Alagard);
		key_indicator_shadow.setFont(key_indicator.getFont().deriveFont(Font.BOLD,20));
		key_indicator_shadow.setBackground(new Color(0,0,0,0));
		key_indicator_shadow.setForeground(Color.DARK_GRAY);
		key_indicator_shadow.setBounds(23,233,120,120);
		key_indicator_shadow.setEditable(false);
		
		restant_keys = new JLabel();
		restant_keys.setFont(Alagard);
		restant_keys.setFont(restant_keys.getFont().deriveFont(Font.BOLD, 18));
		restant_keys.setForeground(Color.white);
		restant_keys.setBounds(110,253,20,20);
		restant_keys.setEnabled(true);
		
		advise_key = new JTextArea();
		advise_key.setText("Take all the\nkeys to finish\nthe laberynth");
		advise_key.setFont(Alagard);
		advise_key.setFont(advise_key.getFont().deriveFont(Font.BOLD,20));
		advise_key.setForeground(new Color(166,23,35));
		advise_key.setBackground(new Color(0,0,0,0));
		advise_key.setBounds(867,656,140,60);
		advise_key.setEditable(false);
		advise_key.setVisible(false);
		
		
		//

		esc_button = new JLabel(new ImageIcon("src/Assets/Images/key_esc.png"));
		esc_button.setBounds(0, 15, 128, 64);

		pause_text = new JLabel();
		pause_text.setText("Pause");
		pause_text.setBounds(30, 85, 128, 25);
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

					listaLethal.add(new EnemyRender(i, j));
					listaLethal.get(listIndexLethal).lethalRender();
					listaLethal.get(listIndexLethal).setLocation(celdas[i].getLocation());
					tablero.add(listaLethal.get(listIndexLethal), JLayeredPane.MODAL_LAYER);
					listIndexLethal++;

				}

				// Stormy
				if (mazeMatrix[i][j] == 6) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor10.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);

					listaStormy.add(new EnemyRender(i, j));
					listaStormy.get(listIndexStormy).stormyRender();
					listaStormy.get(listIndexStormy).setLocation(celdas[i].getLocation());
					tablero.add(listaStormy.get(listIndexStormy), JLayeredPane.MODAL_LAYER);
					listIndexStormy++;
				}

				// Llave
				if (mazeMatrix[i][j] == 5) {
					celdas[i] = new JLabel(new ImageIcon("src/Assets/Floor/floor4.png"));
					celdas[i].setSize(32, 32);
					celdas[i].setLocation(pos_X, pos_Y);
					pos_X += 32;
					tablero.add(celdas[i], JLayeredPane.DEFAULT_LAYER);

					listaLlaves.add(new KeyRender(i, j));
					listaLlaves.get(listIndexKeys).setLocation(celdas[i].getLocation());
					tablero.add(listaLlaves.get(listIndexKeys), JLayeredPane.MODAL_LAYER);
					listIndexKeys++;

				}

				// Entrada
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

				// Salida
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

		add(tablero, JLayeredPane.MODAL_LAYER);
		add(key_indicator, JLayeredPane.MODAL_LAYER);
		add(key_indicator_shadow, JLayeredPane.PALETTE_LAYER);
		add(restant_keys, JLayeredPane.DRAG_LAYER);
		add(advise_key, JLayeredPane.DRAG_LAYER);
		add(movement_indicator, JLayeredPane.DRAG_LAYER);
		add(movement_indicator_shadow, JLayeredPane.POPUP_LAYER);
		add(restant_movements, JLayeredPane.DRAG_LAYER);
		add(esc_button);
		add(pause_text);
		
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

	public ArrayList<EnemyRender> getListaLethal() {
		return listaLethal;
	}

	public void setListaLethal(ArrayList<EnemyRender> listaLethal) {
		this.listaLethal = listaLethal;
	}

	public ArrayList<EnemyRender> getListaStormy() {
		return listaStormy;
	}

	public void setListaStormy(ArrayList<EnemyRender> listaStormy) {
		this.listaStormy = listaStormy;
	}

	public int getListIndexKeys() {
		return listIndexKeys;
	}

	public void setListIndexKeys(int listIndexKeys) {
		this.listIndexKeys = listIndexKeys;
	}

	public int getListIndexLethal() {
		return listIndexLethal;
	}

	public void setListIndexLethal(int listIndexLethal) {
		this.listIndexLethal = listIndexLethal;
	}

	public int getListIndexStormy() {
		return listIndexStormy;
	}

	public void setListIndexStormy(int listIndexStormy) {
		this.listIndexStormy = listIndexStormy;
	}

	public JTextArea getKey_indicator() {
		return key_indicator;
	}

	public void setKey_indicator(JTextArea key_indicator) {
		this.key_indicator = key_indicator;
	}

	public JTextArea getKey_indicator_shadow() {
		return key_indicator_shadow;
	}

	public void setKey_indicator_shadow(JTextArea key_indicator_shadow) {
		this.key_indicator_shadow = key_indicator_shadow;
	}

	public JLabel getRestant_keys() {
		return restant_keys;
	}

	public void setRestant_keys(JLabel restant_keys) {
		this.restant_keys = restant_keys;
	}

	public JTextArea getMovement_indicator() {
		return movement_indicator;
	}

	public void setMovement_indicator(JTextArea movement_indicator) {
		this.movement_indicator = movement_indicator;
	}

	public JTextArea getMovement_indicator_shadow() {
		return movement_indicator_shadow;
	}

	public void setMovement_indicator_shadow(JTextArea movement_indicator_shadow) {
		this.movement_indicator_shadow = movement_indicator_shadow;
	}

	public JTextArea getAdvise_key() {
		return advise_key;
	}

	public void setAdvise_key(JTextArea advise_key) {
		this.advise_key = advise_key;
	}

	public JLabel getRestant_movements() {
		return restant_movements;
	}

	public void setRestant_movements(JLabel restant_movements) {
		this.restant_movements = restant_movements;
	}

}
