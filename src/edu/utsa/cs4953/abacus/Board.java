/**
 * Board.java
 * Handles all of the board logic.
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
import java.math.BigInteger;

/**
 * A logical model of the board of an abacus.
 * 
 */
public class Board {

	/**
	 * Determines the numeric base of the board.
	 * 
	 */
	public enum Base {
		/**
		 * Works just like a suanpan: base-10 digits, cannot use the rightmost
		 * two beads.
		 */
		TEN(10),
		/**
		 * Works in base-20 mode.
		 */
		TWENTY(20);

		/**
		 * Numeric representation of the base.
		 */
		int base;

		/**
		 * Constructor.
		 * 
		 * @param b
		 *            The numeric representation of the base.
		 */
		private Base(int b) {
			base = b;
		}

		/**
		 * Value as a BigInteger.
		 * 
		 * @return Base as a BigInteger.
		 */
		public BigInteger toBigInt() {
			return BigInteger.valueOf(base);
		}

		/**
		 * Value as an integer.
		 * 
		 * @return Base as an integer.
		 */
		public int toInt() {
			return base;
		}
	}

	/**
	 * Gets the only running instance of the board. If no instance of the board
	 * exists, it makes a new instance.
	 * 
	 * @return The only board instance.
	 */
	public static Board getInstance() {
		if (instance == null)
			instance = new Board();
		return instance;
	}

	/**
	 * The numeric base of the board.
	 */
	private Base base;

	/**
	 * The individual rows of beads.
	 */
	private Row[] row;

	/**
	 * The size and position values of the board in relation to the Panel.
	 */
	private Rectangle bounds;

	/**
	 * The overall value of the board.
	 */
	private BigInteger value;

	/**
	 * Determines if the board needs resizing.
	 */
	private boolean needsResizing;

	/**
	 * The only allowable board instance.
	 */
	private static Board instance;

	/**
	 * Private constructor. Is only every called by getInstance().
	 */
	private Board() {
		// Initialize values.
		row = new Row[13];
		bounds = new Rectangle(0, 0, 400, 650);
		base = Base.TWENTY;
		value = BigInteger.ZERO;
		needsResizing = true;

		// Set up the rows.
		int rowWidth = bounds.width;
		int rowHeight = (bounds.height - 8) / (row.length - 1) - 8;

		for (int i = 0; i < row.length; i++) {
			int x = bounds.x;
			int y = bounds.y + i * (rowHeight - 1);
			row[12 - i] = new Row(x, y, rowWidth, rowHeight, 12 - i);
		}
	}

	/**
	 * Adds the argument to the current board value and sets the board to that
	 * value.
	 * 
	 * @param arg
	 *            The value to add.
	 * @throws IllegalArgumentException
	 *             The resulting value is to large for the board to handle.
	 */
	public void add(BigInteger arg) throws IllegalArgumentException {
		BigInteger newValue = value.add(arg);
		setValue(newValue);
	}

	/**
	 * Adds the argument to the current board value and sets the board to that
	 * value.
	 * 
	 * @param arg
	 *            The value to add.
	 * @throws IllegalArgumentException
	 *             The resulting value is to large for the board to handle.
	 */
	public void add(int arg) throws IllegalArgumentException {
		add(BigInteger.valueOf(arg));
	}

	/**
	 * Draws the board.
	 * 
	 * @param g
	 *            The Graphics instance of the panel which calls this.
	 */
	public void Draw(Graphics g) {
		for (int i = 0; i < row.length; i++) {
			row[i].setX(bounds.x + Settings.getMode().getRowOffset(i));
			row[i].Draw(g);
		}
	}

	/**
	 * Gets the numeric base of the board.
	 * 
	 * @return An enumeration representing the base.
	 */
	public Base getBase() {
		return base;
	}

	/**
	 * Determines the value of the bead at those particular coordinates.
	 * 
	 * @param x
	 *            The x-coordinate in question, the leftmost value being 0.
	 * @param y
	 *            The y-coordinate in question, the topmost value being 0.
	 * @return The numeric value of a single bead at position (x, y).
	 */
	public BigInteger getRowValueAt(int x, int y) {
		BigInteger value = BigInteger.ZERO;

		for (int i = 0; i < row.length; i++) {
			BigInteger rowval = BigInteger.valueOf(row[i].getValueAt(x, y));
			value = value.add(base.toBigInt().pow(i).multiply(rowval));
		}
		return value;
	}

	/**
	 * Gets the size in pixels of the board.
	 * 
	 * @return the size A Dimension object representing the board's size.
	 */
	public Dimension getSize() {
		return new Dimension(bounds.width, bounds.height);
	}

	/**
	 * Gets the current value of the board with respect to its current base.
	 * 
	 * @return A BigInteger representing its value.
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * Whether the window size doesn't match the board size.
	 * 
	 * @return True if the window needs resizing, false otherwise.
	 */
	public boolean needsResizing() {
		return this.needsResizing;
	}

	/**
	 * Sets the board's current value to zero.
	 */
	public void Reset() {
		setValue(BigInteger.ZERO);
	}

	/**
	 * Changes the numeric base of the board, affecting how the beads represent
	 * numbers.
	 * 
	 * @param base
	 *            An enumeration representing the new base.
	 * @see Board.Base
	 */
	public void setBase(Base base) {
		this.base = base;
	}

	public void setNeedsResizing(boolean val) {
		this.needsResizing = val;
	}

	/**
	 * Sets the size of the board to a new value.
	 * 
	 * @param size
	 *            the size to set in pixels.
	 */
	public void setSize(Dimension size) {
		this.bounds.width = size.width;
		this.bounds.height = size.height;
	}

	/**
	 * Sets the board to a new value, moving the beads accordingly.
	 * 
	 * @param arg
	 *            the value to set.
	 * @throws IllegalArgumentException
	 *             The value set is out of range.
	 */
	private void setValue(BigInteger arg) throws IllegalArgumentException {
		BigInteger largestValue = base.toBigInt();

		// Verify the argument.
		if (arg.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Cannot set value to " + arg
					+ ": Negative numbers are not allowed.");
		largestValue = largestValue.pow(13);
		if (arg.compareTo(largestValue) >= 0)
			throw new IllegalArgumentException("Cannot set value to " + arg
					+ ": Number is too large to be represented.");

		// Set the value.
		value = arg;

		// Update the rows.
		BigInteger currentValue = value;

		for (int i = 0; i < row.length; i++) {
			int rowval = currentValue.mod(base.toBigInt()).intValue();
			row[i].setValue(rowval);
			currentValue = currentValue.divide(base.toBigInt());
		}
	}

	/**
	 * Sets the board to a new value, moving the beads accordingly.
	 * 
	 * @param arg
	 *            the value to set.
	 * @throws IllegalArgumentException
	 *             The value set is out of range.
	 */
	public void setValue(int arg) throws IllegalArgumentException {
		setValue(BigInteger.valueOf(arg));
	}

	/**
	 * Subtracts the argument from the current board value and sets the board to
	 * that value.
	 * 
	 * @param arg
	 *            The value to subtract.
	 * @throws IllegalArgumentException
	 *             The resulting value is to large for the board to handle.
	 */
	public void subtract(BigInteger arg) throws IllegalArgumentException {
		setValue(value.subtract(arg));
	}

	/**
	 * Subtracts the argument from the current board value and sets the board to
	 * that value.
	 * 
	 * @param arg
	 *            The value to subtract.
	 * @throws IllegalArgumentException
	 *             The resulting value is to large for the board to handle.
	 */
	public void subtract(int arg) throws IllegalArgumentException {
		subtract(BigInteger.valueOf(arg));
	}

	/**
	 * The string representation of the board's value.
	 */
	@Override
	public String toString() {
		return getValue().toString();
	}

}
