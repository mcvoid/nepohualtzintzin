/**
 * OnesBlock.java
 * Handles the beads in a single row which are in the one's column.
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
import java.awt.image.BufferedImage;

/**
 * Represents a row on beads in the one's column.
 * 
 */
public final class OnesBlock extends BeadBlock {

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
	public OnesBlock(int x, int y, int width, int height, int rownum) {
		super(x, y, width, height, rownum);
	}

	/**
	 * Draws the block onto the current graphics context.
	 * 
	 * @param g
	 *            The graphics context the block will draw to.
	 */
	@Override
	public void Draw(Graphics g) {
		BufferedImage[] beads = Settings.getCurrentTheme().getOnesImagesForRow(
				row);

		boolean usingText = (beads == null);

		int textX = bounds.x + (bounds.width / 2);
		int textY = bounds.y + (bounds.height / 2);
		int repositionX = textX - 100;
		int repositionY = textY - 21;

		if (!usingText)
			g.drawImage(beads[getValue()], repositionX, repositionY, null);
		else {
			// As there's only 5 possible values, a simple switch ensures best
			// results.
			switch (getValue()) {
			case 0:
				g.drawString("0000        ", textX, textY);
				break;
			case 1:
				g.drawString("000        0", textX, textY);
				break;
			case 2:
				g.drawString("00        00", textX, textY);
				break;
			case 3:
				g.drawString("0        000", textX, textY);
				break;
			case 4:
				g.drawString("        0000", textX, textY);
				break;
			}
		}

		if (Settings.isShowHitboxes()) {
			// draw hotspots
			for (HitBox h : hotspots) {
				Rectangle r = h.getBounds();
				g.draw3DRect(r.x, r.y, r.width, r.height, true);
			}
		}
	}

	/**
	 * Changes the block's state to a new one.
	 * 
	 * @param value
	 *            The value of the block's new state.
	 */
	@Override
	public void setValue(int value) {
		if (value < 0 && value > 4)
			throw new IllegalArgumentException(
					"Argument must be between 0 and 4, inclusive.");
		this.value = value;
		if (Settings.getCurrentTheme() != null)
			Settings.getCurrentTheme().getHitBoxModel().resetHitBoxes(hotspots, HitBoxModel.ONES, value, bounds);
	}

}
