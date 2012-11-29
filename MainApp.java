package StockMarktApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/*
 *  create main display for the application
 *  subclass of JFrame
 */
public class MainApp extends JFrame {
	
	// constructor method
	public MainApp(){
		// use superclass constructor
		super("Stock Market Application");
		// close frame operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// add MenuBar to frame
		menuBar();
		
		// set layout to frame
		setLayout(new BorderLayout());
		
		StockMarket stockMarket = new StockMarket();
		
		// add StockMarket class to frame on CENTER, integer number of tabs
		add(stockMarket, BorderLayout.CENTER);
		
		// add Keyboard class to frame on SOUTH
		add(new Keyboard(stockMarket), BorderLayout.SOUTH);
		
		// pack all managers
		pack();

		// place frame in the middle of the screen
		setLocationRelativeTo(null);
	}
	
	// MenuBar
	private void menuBar(){
		// create MenuBar
		JMenuBar jmb = new JMenuBar(); 
		// add MenuBar to frame
		setJMenuBar(jmb);
		
		// create student name Menu
		JMenu jm = new JMenu("Student Name");
		// add Menu to MenuBar
		jmb.add(jm);
		// create student name MenuItem
		JMenuItem jmi = new JMenuItem("Gustavo Costa");
		// add MenuItem to Menu(student name)
		jm.add(jmi);
		
		// create student number Menu
		jm = new JMenu("Student Number");
		// add Menu to MenuBar
		jmb.add(jm);
		// create student number MenuItem
		jmi = new JMenuItem("1144702");
		// add MenuItem to Menu(student number)
		jm.add(jmi);		
	}

	// create Main tester
	public static void main(String[] args){
		// call constructor and make frame visible
		new MainApp().setVisible(true);
	}
}