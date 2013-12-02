/**
 * HitBox.java
 * Handles the click events that change board value.
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

import java.awt.Rectangle;

/**
 * Represents a clickable area on the board.
 * 
 */
public class HitBox {
	/**
	 * The area the hitbox covers.
	 */
	private Rectangle bounds;
	/**
	 * The value the board needs to be adjusted by if clicking this hitbox.
	 */
	private int value;

	/**
	 * Constructor.
	 * 
	 * @param bounds
	 *            The area of the hitbox.
	 * @param value
	 *            The value by which the board should adjust by.
	 */
	public HitBox(Rectangle bounds, int value) {
		this.setBounds(bounds);
		this.setValue(value);
	}

	/**
	 * @return the bounds
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	public int getContainedValueAt(int x, int y) {
		if (bounds.contains(x, y)) {
			// System.err.println("Clicked " + value);
			return value;
		}
		return 0;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param bounds
	 *            the bounds to set
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
