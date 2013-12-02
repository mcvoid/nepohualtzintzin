/**
 * DrawingPanel.java
 * Handles the main drawing area for the abacus.
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;

import javax.swing.JPanel;

/**
 * A panel for drawing stuff.
 * 
 */
public class DrawingPanel extends JPanel {
	/**
	 * Handles mouse clicks on the panel.
	 * 
	 */
	private class MouseHandler implements MouseListener {
		BigInteger previousValue = BigInteger.ZERO;

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			previousValue = board.getRowValueAt(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int button = e.getButton();
			BigInteger currentValue = board.getRowValueAt(e.getX(), e.getY());
			if (currentValue.compareTo(previousValue) != 0) {
				previousValue = BigInteger.ZERO;
				return;
			}
			if (button == MouseEvent.BUTTON1) {
				try {
					if (!currentValue.equals(BigInteger.ZERO))
						Settings.getCurrentSoundEffect().play();
					board.add(currentValue);
				} catch (IllegalArgumentException ex) {
					System.err.println(ex.getMessage());
				}
			}
			previousValue = BigInteger.ZERO;
			Program.content.repaint();

		}

	}

	/**
	 * Included because JPanel implements the Serializable interface.
	 */
	private static final long serialVersionUID = -672141274037444207L;

	/**
	 * The panel's starting height.
	 */
	public static int DEFAULT_HEIGHT = 574;

	/**
	 * A reference to the board logic.
	 */
	private Board board;

	/**
	 * Constructor.
	 */
	public DrawingPanel() {
		super();
		board = Board.getInstance();
		addMouseListener(new MouseHandler());

	}

	/**
	 * The routine that is called by the system whenever the window repaints.
	 * 
	 * @param g
	 *            The graphics device being used by the window.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, Settings.getMode().getWidth(), DEFAULT_HEIGHT);
		if (Settings.getCurrentTheme() == null)
			return;
		if (Settings.getCurrentTheme().getBackground() != null)
			g.drawImage(Settings.getCurrentTheme().getBackground(), 0, 0, null);
		this.setBounds(new Rectangle(Settings.getMode().getWidth(),
				DEFAULT_HEIGHT));
		this.setPreferredSize(new Dimension(Settings.getMode().getWidth(),
				DEFAULT_HEIGHT));
		if (board.needsResizing()) {
			Settings.getProgram().setResizable(true);
			Settings.getProgram().pack();
			Settings.getProgram().setResizable(false);
			Settings.getBoard().setNeedsResizing(false);
		}

		board.Draw(g);
	}
}
