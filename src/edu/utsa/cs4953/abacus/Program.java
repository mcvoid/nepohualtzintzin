/**
 * Program.java
 * Handles the startup code and creating the main window.
 * 
 * Copyright (c) 2012 The Javanauts
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this 
 * software and associated documentation files (the "Software"), to deal in the Software 
 * without restriction, including without limitation the rights to use, copy, modify, 
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package edu.utsa.cs4953.abacus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The application's main window.
 * 
 */
public class Program extends JFrame {

	/**
	 * Initializes the window and its subcomponents.
	 * 
	 */
	private class WindowInitializer implements Runnable {
		@Override
		public void run() {
			// Set up the board.
			Settings.setBoard();
			board = Settings.getBoard();

			// Make the program exit when you close the window.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);

			// Add the components to the window.
			content = getContentPane();
			content.setLayout(new BorderLayout());
			content.add(Settings.setDrawingPanel(new DrawingPanel()),
					BorderLayout.CENTER);
			content.add(Settings.setMayaPanel(new MayaNumeralPanel()),
					BorderLayout.EAST);
			content.add(Settings.setStatusPanel(new DecimalPanel()),
					BorderLayout.SOUTH);

			// Add the menu bar and menu items for settings control.

			MenuHandler listener = new MenuHandler();

			JMenuItem[] boardItems = {
					SwingHelper
							.createMenuItem(
									"Increment",
									"This is where you increase the value of the board!",
									KeyEvent.VK_I, KeyEvent.VK_UP, listener),
					SwingHelper
							.createMenuItem(
									"Decrement",
									"This is where you decrease the value of the board!",
									KeyEvent.VK_D, KeyEvent.VK_DOWN, listener),
					SwingHelper.createMenuItem("Reset",
							"This is where you reset the board!",
							KeyEvent.VK_R, KeyEvent.VK_R, listener),
					SwingHelper.createMenuItem("Settings",
							"This is where the settings live!", KeyEvent.VK_S,
							KeyEvent.VK_S, listener),
					SwingHelper.createMenuItem("Quit",
							"This is where the settings live!", KeyEvent.VK_Q,
							KeyEvent.VK_Q, listener) };

			JMenuItem[] helpItems = {
					SwingHelper.createMenuItem("Trivia",
							"This is where you can learn while you learn!",
							KeyEvent.VK_T, KeyEvent.VK_T, listener),
					SwingHelper
							.createMenuItem(
									"About",
									"This is where you can see stuff about the program!",
									KeyEvent.VK_A, KeyEvent.VK_A, listener) };

			JMenu[] menu = {
					SwingHelper.createMenu(boardItems, "Board",
							"This is the file menu! By convention, of course.",
							KeyEvent.VK_B),
					SwingHelper.createMenu(helpItems, "Help",
							"This is the help menu!", KeyEvent.VK_H) };

			JMenuBar menuBar = new JMenuBar();
			SwingHelper.addAll(menu, menuBar);
			setJMenuBar(menuBar);

			Settings.loadSettings();

			// Workaround for OS X JDK bug.
			if (!System.getProperty("os.name").contains("OS X"))
				setIconImage(new ImageIcon(getClass().getResource(
						"/images/mayazero_icon.png")).getImage());

			// Shrink-wrap the window size to the components.
			setLocationByPlatform(true);
			pack();

			// Display window.
			setVisible(true);

			Trivia.LoadTriviaFromFile("/trivia.txt");
			if (Settings.isShowTrivia())
				Trivia.displayRandomTrivia();
		}
	}

	/**
	 * Included because JFrame implements the Serializable interface.
	 */
	private static final long serialVersionUID = 9124117387075430331L;

	/**
	 * A reference to the main logical model of the board.
	 */
	public static Board board;

	/**
	 * A reference to the parent of all the GUI components.
	 */
	public static Container content;

	/**
	 * Program entry point.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		Program p = null;
		try {
			p = new Program("Nepohualtzintzin");
			// Initializes the window in the event dispatch thread instead of
			// the
			// main thread. This avoids threading issues involved with window
			// setup.
		} catch (HeadlessException e) {
			// This will only be thrown in an environment where there is no
			// display, mouse, or keyboard attached, like on a "head-less"
			// server in a virtual environment or server farm.
			String err = "Error: Nepohualtzintzin must be run in a graphical environment.";
			System.err.println(err);
			System.err.println(e.getMessage());
		}
		Settings.setProgram(p);
		p.run();
	}

	/**
	 * Default Constructor.
	 * 
	 * @throws HeadlessException
	 *             If the program is not run in a graphical environment.
	 */
	public Program() throws HeadlessException {
		this("Default title");
	}

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The caption in the window's title bar.
	 * @throws HeadlessException
	 *             If the program is not run in a graphical environment.
	 */
	public Program(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * Initialize the window.
	 */
	private void run() {
		EventQueue.invokeLater(new Program.WindowInitializer());
	}
}
