/**
 * Row.java
 * Handles the drawing of a single row and its component beads.
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

import java.awt.Graphics;
import java.math.BigInteger;

/**
 * Represents a single row of bead in the abacus.
 * 
 */
public class Row {
	/**
	 * The block in the ones' column.
	 */
	private OnesBlock ones;

	/**
	 * The block in the fives' column.
	 */
	private FivesBlock fives;

	/**
	 * The width of the row.
	 */
	private int width;

	/**
	 * The x-coordinate of the left edge of the row.
	 */
	private int offset;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            The horizontal position of the block.
	 * @param y
	 *            The vertical position of the block.
	 * @param width
	 *            The horizontal size of the block.
	 * @param height
	 *            The vertical size of the block.
	 */
	public Row(int x, int y, int width, int height, int rownum) {
		this.offset = x;
		this.width = width;
		ones = new OnesBlock(x, y, width / 2 + 8, height, rownum);
		fives = new FivesBlock(x + (width / 2) + 16, y, (width / 2) - 16,
				height, rownum);
		setValue(0);
	}

	/**
	 * Draws the row onto the given graphics context.
	 * 
	 * @param g
	 *            The graphics context of the parent container.
	 */
	public void Draw(Graphics g) {
		ones.Draw(g);
		fives.Draw(g);
	}

	/**
	 * The fives' block in this row.
	 * 
	 * @return The object representing the fives' block.
	 */
	public FivesBlock getFives() {
		return fives;
	}

	/**
	 * The ones' block in this row.
	 * 
	 * @return The object representing the ones' block.
	 */
	public OnesBlock getOnes() {
		return ones;
	}

	/**
	 * The value of this row without regard to position on the board.
	 * 
	 * @return The combined value of the ones and fives block in this row.
	 */
	public int getValue() {
		return ones.getValue() + 5 * fives.getValue();
	}

	/**
	 * Gets the value of a click in that row.
	 * 
	 * @param x
	 *            the mouse's X coordinate.
	 * @param y
	 *            The mouse's Y coordinate.
	 * @return The value the board should be adjusted by.
	 */
	public int getValueAt(int x, int y) {
		int value = ones.getValueAt(x, y) + (5 * fives.getValueAt(x, y));
		return value;

	}

	/**
	 * The value of this row without regard to position on the board.
	 * 
	 * @return The combined value of the ones and fives block in this row.
	 */
	public BigInteger getValueBig() {
		return BigInteger.valueOf(getValue());
	}

	/**
	 * Sets a new value to the row
	 * 
	 * @param value
	 *            An integer between 0 and 19.
	 */
	public void setValue(int value) {
		if (value < 0 && value > 19)
			throw new IllegalArgumentException(
					"Argument must be between 0 and 19, inclusive.");
		fives.setValue(value / 5);
		ones.setValue(value % 5);
	}

	/**
	 * Changes the horizontal offset of the row.
	 * 
	 * @param i
	 *            The number of pixels to shift the row.
	 */
	public void setX(int i) {
		ones.setX(offset + i);
		fives.setX(offset + i + (width / 2) + 16);
		// this nasty hack resets the hit box locations.
		setValue(getValue());
	}

	/**
	 * The string representation of the row's value.
	 */
	@Override
	public String toString() {
		String result = "";
		result += ones.getValue() + "\t" + (5 * fives.getValue());
		return result;

	}
}
