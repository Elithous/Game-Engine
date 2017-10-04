package me.Elithous.Window;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import me.Elithous.Main.Main;

public class Window extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8775048307113970429L;
	
	public Window(int width, int height, String title,Main main) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(main);
		frame.setVisible(true);
		
		main.start();
		
	}
	
}
