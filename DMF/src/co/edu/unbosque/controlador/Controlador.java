package co.edu.unbosque.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import co.edu.unbosque.modelo.MazeGeneratorMatrix;
import co.edu.unbosque.vista.GameFrame;
import co.edu.unbosque.vista.KeyRender;
import co.edu.unbosque.modelo.Coord;
import co.edu.unbosque.modelo.KeyGeneratorMatrix;
import co.edu.unbosque.modelo.MazeBFS;

public class Controlador implements ActionListener, KeyListener {

	private String select_button;

	// Conexiones
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

	// Varios
	private int intentos_generacion;
	boolean actualESC = false;
	private ArrayList<Coord> coordsCamino;
	private ArrayList<KeyRender> listaLlaves;

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

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if (gameFrame.getpState().isVisible()) {
		} else {

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

						// Recoleccion de las llaves
						listaLlaves = gameFrame.getGameState().getListaLlaves();

						for (KeyRender key_render : listaLlaves) {
							if (((posY / 32) == key_render.getPosRow()) && ((posX / 32) == key_render.getPosCol())) {
								key_render.setVisible(false);
								mazeMatrix[posY / 32][posX / 32] = 0;
								break;
							}
						}
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

						// Recoleccion de las llaves
						listaLlaves = gameFrame.getGameState().getListaLlaves();

						for (KeyRender key_render : listaLlaves) {
							if (((posY / 32) == key_render.getPosRow()) && ((posX / 32) == key_render.getPosCol())) {
								key_render.setVisible(false);
								mazeMatrix[posY / 32][posX / 32] = 0;
								break;
							}
						}
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

						// Recoleccion de las llaves
						listaLlaves = gameFrame.getGameState().getListaLlaves();

						for (KeyRender key_render : listaLlaves) {
							if (((posY / 32) == key_render.getPosRow()) && ((posX / 32) == key_render.getPosCol())) {
								key_render.setVisible(false);
								mazeMatrix[posY / 32][posX / 32] = 0;
								break;
							}
						}
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

						// Recoleccion de las llaves
						listaLlaves = gameFrame.getGameState().getListaLlaves();

						for (KeyRender key_render : listaLlaves) {
							if (((posY / 32) == key_render.getPosRow()) && ((posX / 32) == key_render.getPosCol())) {
								key_render.setVisible(false);
								mazeMatrix[posY / 32][posX / 32] = 0;
								break;
							}
						}
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
				}

			}

			// Chequea si la posicion actualizada actual es 4 (es decir, la salida)
			if (mazeMatrix[posY / 32][posX / 32] == 4) {
				System.out.println("LLego");

				// Ocultar el juego, mostrar passState, generar nuevo mazeMatrix, dibujar nuevo
				// mazeMatrix
			}

		}
		// Pausa
		if (code == KeyEvent.VK_ESCAPE) {

			if (actualESC == false) {
				gameFrame.getpState().setVisible(true);
				gameFrame.getGameState().getEsc_button().setVisible(false);
				gameFrame.getGameState().getPause_text().setVisible(false);
				actualESC = true;
			} else if (actualESC = true) {
				gameFrame.getpState().setVisible(false);
				gameFrame.getGameState().getEsc_button().setVisible(true);
				gameFrame.getGameState().getPause_text().setVisible(true);
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

		gameFrame.getTsState().getTsOptions().addActionListener(this);
		gameFrame.getTsState().getTsOptions().setActionCommand("options_button");

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

		gameFrame.getOpState().getBack_button().addActionListener(this);
		gameFrame.getOpState().getBack_button().setActionCommand("op_back_button");

		gameFrame.getCrState().getBack_button().addActionListener(this);
		gameFrame.getCrState().getBack_button().setActionCommand("cr_back_button");

		gameFrame.getTuState().getBack_button().addActionListener(this);
		gameFrame.getTuState().getBack_button().setActionCommand("tu_back_button");

		gameFrame.getPrgState().getBack_button().addActionListener(this);
		gameFrame.getPrgState().getBack_button().setActionCommand("prg_back_button");

		// Pre-Game Start Button
		gameFrame.getPrgState().getStart_button().addActionListener(this);
		gameFrame.getPrgState().getStart_button().setActionCommand("prg_start_button");

		// Pause Buttons
		gameFrame.getpState().getOptions_button().addActionListener(this);
		gameFrame.getpState().getOptions_button().setActionCommand("pause_options_button");

		gameFrame.getpState().getResume_button().addActionListener(this);
		gameFrame.getpState().getResume_button().setActionCommand("pause_resume_button");

		gameFrame.getpState().getBack_button().addActionListener(this);
		gameFrame.getpState().getBack_button().setActionCommand("pause_back_button");

		gameFrame.getpState().getInstructions_button().addActionListener(this);
		gameFrame.getpState().getInstructions_button().setActionCommand("pause_instruction_button");

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
		case "options_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic(0);
			gameFrame.getOpState().playSE(8);
			gameFrame.getOpState().playMusic(1);

			gameFrame.getOpState().setVisible(true);
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
			} else if (warrior == true) {
				select_button += "Warrior";
			} else if (barbarian == true) {
				select_button += "Barbarian";
			} else if (mage == true) {
				select_button += "Mage";
			} else if (paladin == true) {
				select_button += "Paladin";
			} else if (rogue == true) {
				select_button += "Rogue";
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
		case "op_back_button": {

			gameFrame.getOpState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getOpState().stopMusic(1);
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

			// Condicional para el minimo (5) y el maximo (20)
			if ((rows < 5 || rows > 20) && (columns < 5 || columns > 20)) {

				gameFrame.getPrgState().getEntrada_Y().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_Y().setText("");

				gameFrame.getPrgState().getEntrada_X().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_X().setText("");

			} else {
				gameFrame.getPrgState().getEntrada_Y().setBackground(Color.DARK_GRAY);
				gameFrame.getPrgState().getEntrada_X().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}

			// Condicional para el minimo (2) y el maximo (5)
			if (keys < 2 || keys > 5) {

				gameFrame.getPrgState().getCantidad_llaves().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_llaves().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_llaves().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}

			// Condicional para el minimo (1) y el maximo (keys) de los enemigos Lethal
			if (lethal < 1 || lethal > keys) {
				gameFrame.getPrgState().getCantidad_lethal().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_lethal().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_lethal().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}
			// Condicional para el minimo (1) y el maximo (stormy) de los enemigos Stormy
			if (stormy < 1 || stormy > keys) {
				gameFrame.getPrgState().getCantidad_stormy().setBackground(Color.RED);
				gameFrame.getPrgState().getCantidad_stormy().setText("");
			} else {
				gameFrame.getPrgState().getCantidad_stormy().setBackground(Color.DARK_GRAY);
				confirmacion_gen++;
			}

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

			gameFrame.getpState().getPanel_tu().setVisible(true);
			gameFrame.getpState().getPanel_op().setVisible(false);

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
}
