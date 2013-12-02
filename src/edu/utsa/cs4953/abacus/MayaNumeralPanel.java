/**
 * MayaNumeralPanel
 * Draws the value of the board in Mayan digits.
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A column of Mayan digits representing the value of the board.
 * 
 */
public class MayaNumeralPanel extends JPanel {

	/**
	 * Included because JPanel implements the Serializable interface.
	 */
	private static final long serialVersionUID = 2232618121894401348L;

	/**
	 * The panel's starting height.
	 */
	private static final int DEFAULT_HEIGHT = 574;

	/**
	 * The panel's starting width.
	 */
	private static final int DEFAULT_WIDTH = 50;
	/**
	 * The image set of the mayan numerals.
	 */
	private ImageIcon[] numeral;
	/**
	 * The labels used to position and render the images.
	 */
	private JLabel[] digit;

	/**
	 * Constructor.
	 */
	public MayaNumeralPanel() {
		super();
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		numeral = new ImageIcon[21];
		for (int i = 0; i < numeral.length; i++) {
			numeral[i] = new ImageIcon(getClass().getResource(
					"/images/numerals/Maya_" + i + ".jpg"));
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		digit = new JLabel[13];
		// add in reverse order so least significant digit (digit[0]) is on
		// bottom.
		for (int i = digit.length - 1; i >= 0; i--) {
			digit[i] = new JLabel();
			add(digit[i]);
		}
	}

	/**
	 * The routine that is called by the system whenever the window repaints.
	 * 
	 * @param g
	 *            The graphics device being used by the window.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

		final BigInteger TWENTY = BigInteger.valueOf(20L);

		BigInteger val = Board.getInstance().getValue();
		int i = 0;

		while (val.compareTo(BigInteger.ZERO) != 0 && i < digit.length) {
			int modulo = (int) val.mod(TWENTY).longValue();
			digit[i].setIcon(numeral[modulo]);
			val = val.divide(TWENTY);
			i++;
		}
		// eliminate leading zeros.
		for (/* pick up where last loop left off */; i < digit.length; i++)
			digit[i].setIcon(numeral[20]);

		Color borderColor = new Color(00, 00, 00);
		this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
	}

}
