package co.edu.unbosque.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import co.edu.unbosque.modelo.MazeGeneratorMatrix;
import co.edu.unbosque.vista.GameFrame;
import co.edu.unbosque.modelo.MazeBFS;

public class Controlador implements ActionListener, KeyListener{

	private GameFrame gameFrame;
	private MazeGeneratorMatrix mazeGen;
	private MazeBFS mazeBFS;
	
	private static int columns;
	private static int rows;
	private static int[][] mazeMatrix;
	
	private int intentos_generacion;
	private boolean upP, downP, leftP, rightP;
	private int posX, posY, desplazamiento;
	boolean actualESC = false;
	

	public Controlador() {

	}
	
	public void run() {
		
		gameFrame = new GameFrame();
		agregarLectores();

	}
	
	public void newGame(int rows, int columns) {
		boolean generar;
		mazeGen = new MazeGeneratorMatrix();
		mazeBFS = new MazeBFS();
		
		//Generar laberintos hasta generar uno con solucion
		do {
			mazeMatrix = mazeGen.generateMaze(rows, columns);
			generar = mazeBFS.MazeBFS(mazeMatrix);
			intentos_generacion++;
			
		} while (generar == false);
		
		
//		System.out.println("Numero de intentos en la generacion: "+intentos_generacion);
	}
	

	
	
				@Override
				public void keyPressed(KeyEvent e) {
					
					int code = e.getKeyCode();
					if (gameFrame.getpState().isVisible()) {
						//No lee input, no permite que el jugador se mueva
					}else {
						
						//Movimiento
						if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
							
							//Cuando el jugador trata de moverse fuera del length de la matriz
							try {
								 if (mazeMatrix[(posY/32)-1][posX/32] != 1) {
										upP = true;
//										System.out.println("W");
										gameFrame.getGameState().getPlayer().setLocation(posX, posY-desplazamiento);
										gameFrame.getGameState().getPlayer().repaint();
										posX = gameFrame.getGameState().getPlayer().getX();
										posY = gameFrame.getGameState().getPlayer().getY();
										
//										if (mazeMatrix[posY/32][posX/32] == 4) {
//											System.out.println("LLego");
//										}	
								 }
							} catch (ArrayIndexOutOfBoundsException e2) {}
				
						}
						if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
							try {
								 if (mazeMatrix[(posY/32)+1][posX/32] != 1) {
										downP = true;
//										System.out.println("S");
										gameFrame.getGameState().getPlayer().setLocation(posX, posY+desplazamiento);
										gameFrame.getGameState().getPlayer().repaint();
										posX = gameFrame.getGameState().getPlayer().getX();
										posY = gameFrame.getGameState().getPlayer().getY();
										
//										if (mazeMatrix[posY/32][posX/32] == 4) {
//											System.out.println("LLego");
//										}
								 }
							} catch (ArrayIndexOutOfBoundsException e2) {}
				
						}
						if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
							try {
								 if (mazeMatrix[posY/32][(posX/32)-1] != 1) {
										setLeftP(true);
//										System.out.println("A");
										gameFrame.getGameState().getPlayer().setLocation(posX-desplazamiento, posY);
										gameFrame.getGameState().getPlayer().repaint();
										posX = gameFrame.getGameState().getPlayer().getX();
										posY = gameFrame.getGameState().getPlayer().getY();
										
//										if (mazeMatrix[posY/32][posX/32] == 4) {
//											System.out.println("LLego");
//										}
								 }
							} catch (ArrayIndexOutOfBoundsException e2) {}
				
						}
						if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
							try {
								 if (mazeMatrix[posY/32][(posX/32)+1] != 1) {
										rightP = true;
//										System.out.println("D");
										gameFrame.getGameState().getPlayer().setLocation(posX+desplazamiento, posY);
										gameFrame.getGameState().getPlayer().repaint();
										posX = gameFrame.getGameState().getPlayer().getX();
										posY = gameFrame.getGameState().getPlayer().getY();
										

								 }
							} catch (ArrayIndexOutOfBoundsException e2) {}
				
						}
						
						//Chequea si la posicion actualizada actual es 4 (es decir, la salida)
						if (mazeMatrix[posY/32][posX/32] == 4) {
							System.out.println("LLego");

							
							//Ocultar el juego, mostrar passState, generar nuevo mazeMatrix, dibujar nuevo mazeMatrix
						}

					}
					//Pausa
					if(code == KeyEvent.VK_ESCAPE) {
						
						if(actualESC == false) {
							gameFrame.getpState().setVisible(true);
							actualESC = true;
						}
						else if (actualESC = true) {
							gameFrame.getpState().setVisible(false);
							actualESC = false;
						}
						
					}
					

				}
			
				@Override
				public void keyReleased(KeyEvent e) {
					
					int code = e.getKeyCode();
					
					if(code == KeyEvent.VK_W) {
						upP = false;
					}
					if(code == KeyEvent.VK_S) {
						downP = false;
					}
					if(code == KeyEvent.VK_A) {
						setLeftP(false);
					}
					if(code == KeyEvent.VK_D) {
						rightP = false;
					}

				}
				@Override
				public void keyTyped(KeyEvent e) {}
	
	
	

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
		
		//Pre-Game Start Button
		gameFrame.getPrgState().getStart_button().addActionListener(this);
		gameFrame.getPrgState().getStart_button().setActionCommand("prg_start_button");
		
		
		//Pause Buttons
		gameFrame.getpState().getOptions_button().addActionListener(this);
		gameFrame.getpState().getOptions_button().setActionCommand("pause_options_button");
		
		gameFrame.getpState().getResume_button().addActionListener(this);
		gameFrame.getpState().getResume_button().setActionCommand("pause_resume_button");
		
		gameFrame.getpState().getBack_button().addActionListener(this);
		gameFrame.getpState().getBack_button().setActionCommand("pause_back_button");
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "new_game_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic();
			gameFrame.getChState().playMusic(3);

			gameFrame.getChState().setVisible(true);

			break;
		}
		case "options_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic();
			gameFrame.getOpState().playMusic(1);

			gameFrame.getOpState().setVisible(true);
			break;
		}
		case "tutorial_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic();
			gameFrame.getTuState().playMusic(4);

			gameFrame.getTuState().setVisible(true);
			break;
		}
		case "credits_button": {
			gameFrame.getTsState().setVisible(false);
			gameFrame.getTsState().stopMusic();
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
		case "cs_select_button":{
			
			gameFrame.getChState().setVisible(false);
			gameFrame.getPrgState().setVisible(true);
			
			
			break;
		}
		case "cs_back_button": {

			gameFrame.getChState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getChState().stopMusic();
			gameFrame.getTsState().playMusic(0);

			break;
		}
		case "cr_back_button": {

			gameFrame.getCrState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getCrState().stopMusic();
			gameFrame.getTsState().playMusic(0);

			break;
		}
		case "op_back_button": {

			gameFrame.getOpState().setVisible(false);
			gameFrame.getTsState().setVisible(true);
			gameFrame.getOpState().stopMusic();
			gameFrame.getTsState().playMusic(0);

			break;
		}
		case "tu_back_button" :{

			gameFrame.getTuState().setVisible(false);
			gameFrame.getTuState().stopMusic();
			gameFrame.getTsState().playMusic(0);
			
			gameFrame.getTsState().setVisible(true);
			
			
			break;
		}
		case "prg_back_button" :{
			
			gameFrame.getPrgState().setVisible(false);
			gameFrame.getChState().setVisible(true);
			
			break;
		}
		case "prg_start_button" :{
			

			//Recoge los valores ingresados en el JTextField, y los convierte a Integer
			rows = Integer.parseInt(gameFrame.getPrgState().getEntrada_Y().getText());
			columns = Integer.parseInt(gameFrame.getPrgState().getEntrada_X().getText());
			
			//Condicional para el minimo (5) y el maximo (20)
			if((rows < 5 || rows > 20) && (columns < 5 || columns > 20)) {
				gameFrame.getPrgState().getEntrada_Y().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_Y().setText("");
				
				gameFrame.getPrgState().getEntrada_X().setBackground(Color.RED);
				gameFrame.getPrgState().getEntrada_X().setText("");
			}
			else {
				
				//Genera Laberinto con soluci�n
				this.newGame(rows, columns);
				
				gameFrame.getPrgState().setVisible(false);
				gameFrame.getChState().stopMusic();
				
				//Dibuja y Muestra el laberinto
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
			
			gameFrame.getOpState().setVisible(true);
			
			gameFrame.getGameState().setVisible(false);
			gameFrame.getpState().setVisible(false);

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
		default:
		}
	}

	
	
	//Getters & Setters
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

	public static int[][] getMazeMatrix() {
		return mazeMatrix;
	}

	public static void setMazeMatrix(int[][] mazeMatrix) {
		Controlador.mazeMatrix = mazeMatrix;
	}

	public int getIntentos_generacion() {
		return intentos_generacion;
	}

	public void setIntentos_generacion(int intentos_generacion) {
		this.intentos_generacion = intentos_generacion;
	}

	public MazeBFS getMazeBFS() {
		return mazeBFS;
	}

	public void setMazeBFS(MazeBFS mazeBFS) {
		this.mazeBFS = mazeBFS;
	}

	public boolean isLeftP() {
		return leftP;
	}

	public void setLeftP(boolean leftP) {
		this.leftP = leftP;
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

}
