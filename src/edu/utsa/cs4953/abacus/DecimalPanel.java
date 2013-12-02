/**
 * StatusPanel.Java
 * Handles the drawing of the decimal digits and reset button.
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

//import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Shows the decimal value of the board.
 * 
 */
public class DecimalPanel extends JPanel {
	/**
	 * Required for Serializable interface.
	 */
	private static final long serialVersionUID = 5899981052755169447L;

	/**
	 * Shows the total value of the board.
	 */
	private JLabel value;

	/**
	 * Default Constructor
	 */
	public DecimalPanel() {
		value = new JLabel(Board.getInstance().getValue().toString());
		add(value);
	}

	/**
	 * The routine that is called by the system whenever the window repaints.
	 * 
	 * @param g
	 *            The graphics device being used by the window.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		value.setText(Board.getInstance().getValue().toString());
		Color borderColor = new Color(00, 00, 00);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
	}
}
