/**
 * BoardMode.java
 * Contains data associated with different board modes.
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

/**
 * Determines whether the board is in split-board mode or normal mode.
 * 
 */
public enum Mode {
	/**
	 * Normal abacus operating mode.
	 */
	NORMAL(400),
	/**
	 * Special split mode used for multiplication, square roots, etc.
	 */
	SPLIT(500);

	/**
	 * The window width in pixels.
	 */
	private int width;

	/**
	 * Constructor
	 * 
	 * @param width
	 *            The window width of the current mode in pixels.
	 */
	private Mode(int width) {
		this.width = width;
	}

	/**
	 * Gets the horizontal offset a row should start at.
	 * 
	 * @param row
	 *            The row in question.
	 * @return The offset in pixels.
	 */
	public int getRowOffset(int row) {
		if (row < 0 || row >= 13)
			throw new IllegalArgumentException("Only rows 0-12 are valid.");
		if (this == NORMAL)
			return 4;
		switch (row) {
		case 0:
		case 1:
		case 2:
		case 6:
		case 7:
		case 8:
		case 12:
			return 4;
		case 3:
		case 4:
		case 5:
		case 9:
		case 10:
		case 11:
			return 4 + SPLIT.width - NORMAL.width;
		}
		return 4;
	}

	/**
	 * Gets the board width according to the current mode.
	 * 
	 * @return The width of the board in pixels.
	 */
	public int getWidth() {
		return width;
	}
}