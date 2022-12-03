package co.edu.unbosque.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class EndState extends JPanel {

	private Font Alagard;

	// win & lost panel

	private JPanel victory_panel, lost_panel;
	private JLabel victory_ad, lost_ad;
	private JButton back_menu_lost, back_menu_win;

	public EndState() {

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

		// Win & lost panels

		victory_panel = new JPanel(null);
		victory_panel.setBackground(new Color(0, 0, 0, 160));
		victory_panel.setBounds(0, 0, 1024, 768);
		victory_panel.setVisible(true);

		victory_ad = new JLabel(new ImageIcon("src/Assets/Images/victory_ad.png"));
		victory_ad.setBounds(0, 250, 1024, 205);

		lost_panel = new JPanel(null);
		lost_panel.setBackground(new Color(0, 0, 0, 160));
		lost_panel.setBounds(0, 0, 1024, 768);
		lost_panel.setVisible(false);

		lost_ad = new JLabel(new ImageIcon("src/Assets/Images/lost_ad.png"));
		lost_ad.setBounds(0, 250, 1024, 205);

		back_menu_lost = new JButton();
		back_menu_lost.setText("Quit");
		back_menu_lost.setFont(Alagard);
		back_menu_lost.setFont(back_menu_lost.getFont().deriveFont(Font.ITALIC, 20));
		back_menu_lost.setBounds(380, 480, 250, 20);
		back_menu_lost.setBackground(Color.black);
		back_menu_lost.setForeground(Color.white);

		back_menu_win = new JButton();
		back_menu_win.setText("Quit");
		back_menu_win.setFont(Alagard);
		back_menu_win.setFont(back_menu_win.getFont().deriveFont(Font.ITALIC, 20));
		back_menu_win.setBounds(380, 480, 250, 20);
		back_menu_win.setBackground(Color.black);
		back_menu_win.setForeground(Color.white);


		add(lost_panel, JLayeredPane.DRAG_LAYER);
		lost_panel.add(lost_ad);
		lost_panel.add(back_menu_lost);
		add(victory_panel, JLayeredPane.DRAG_LAYER);
		victory_panel.add(victory_ad);
		victory_panel.add(back_menu_win);

	}

	public Font getAlagard() {
		return Alagard;
	}

	public void setAlagard(Font alagard) {
		Alagard = alagard;
	}

	public JPanel getVictory_panel() {
		return victory_panel;
	}

	public void setVictory_panel(JPanel victory_panel) {
		this.victory_panel = victory_panel;
	}

	public JPanel getLost_panel() {
		return lost_panel;
	}

	public void setLost_panel(JPanel lost_panel) {
		this.lost_panel = lost_panel;
	}

	public JLabel getVictory_ad() {
		return victory_ad;
	}

	public void setVictory_ad(JLabel victory_ad) {
		this.victory_ad = victory_ad;
	}

	public JLabel getLost_ad() {
		return lost_ad;
	}

	public void setLost_ad(JLabel lost_ad) {
		this.lost_ad = lost_ad;
	}
	
	public JButton getBack_menu_lost() {
		return back_menu_lost;
	}

	public void setBack_menu_lost(JButton back_menu_lost) {
		this.back_menu_lost = back_menu_lost;
	}
	
	public JButton getBack_menu_win() {
		return back_menu_win;
	}

	public void setBack_menu_win(JButton back_menu_win) {
		this.back_menu_win = back_menu_win;
	}

	
	
}
