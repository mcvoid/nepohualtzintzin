/**
 * BeadBlock.java
 * A representation of a grouping of beads in a row.
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
import java.awt.Rectangle;

/**
 * A group of beads.
 * 
 */
public abstract class BeadBlock {
	/**
	 * The value of all the beads in the block.
	 */
	protected int value;

	/**
	 * The position and size of the block in pixels.
	 */
	protected Rectangle bounds;

	/**
	 * The clickable areas in the block.
	 */
	protected HitBox[] hotspots = new HitBox[8];

	/**
	 * The row of the bead block.
	 */
	protected int row;

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            The horizontal position of the block, in pixels.
	 * @param y
	 *            The vertical position of the block, in pixels.
	 * @param width
	 *            The width of the block, in pixels.
	 * @param height
	 *            The height of the block, in pixels.
	 */
	public BeadBlock(int x, int y, int width, int height, int rownum) {
		value = 0;
		bounds = new Rectangle(x, y, width, height);
		for (int i = 0; i < hotspots.length; i++)
			hotspots[i] = new HitBox(new Rectangle(x, y, 0, height), i
					- ((i < 4) ? 4 : 3));
		row = rownum;
	}

	/**
	 * Draws the block onto the current graphics context.
	 * 
	 * @param g
	 *            The graphics context the block will draw to.
	 */
	public abstract void Draw(Graphics g);

	/**
	 * Returns the current value of all the beads in the block without respect
	 * to the base of the board or the block's position.
	 * 
	 * @return A integer determining the state of the block.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the value of a click in that block.
	 * 
	 * @param x
	 *            the mouse's X coordinate.
	 * @param y
	 *            The mouse's Y coordinate.
	 * @return The value the board should be adjusted by.
	 */
	public int getValueAt(int x, int y) {
		int val = 0;
		for (HitBox h : hotspots)
			val += h.getContainedValueAt(x, y);
		return val;
	}

	/**
	 * Changes the block's state to a new one.
	 * 
	 * @param value
	 *            The value of the block's new state.
	 */
	public abstract void setValue(int value);

	/**
	 * Changes the X coordinate of the bead block.
	 * 
	 * @param x
	 *            the new x in pixels.
	 */
	public void setX(int x) {
		bounds.x = x;
	}
}
