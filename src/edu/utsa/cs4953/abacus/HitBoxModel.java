/**
 * HitBoxModel.java
 * Interface for objects that rearrange hit boxes on the fly.
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
 * Rearranges the clickable hotspots into appropriate configurations.
 *
 */
public abstract class HitBoxModel {
	/**
	 * The block being handled has four hit boxes.
	 */
	public static final int ONES = 0;
	/**
	 * The block being handled has three hit boxes.
	 */
	public static final int FIVES = 1;
	/**
	 * Automatically arranges the given array of hit boxes appropriately for the given state.
	 * @param box The list of hit boxes to modify.
	 * @param type Whether the calling block wants a one's block arrangement or a five's arrangement.
	 * @param state What block state the beads are representing.
	 * @param bounds The block boundaries.
	 */
	public void resetHitBoxes(HitBox[] box, int type, int state, Rectangle bounds) {
		if (box == null) {
			System.err.println("Null array");
			return;
		} else if (box.length != 8) {
			System.err.println("Array size: " + box.length);
			return;
		}
		for (HitBox h : box)
			if (h == null) {
				System.err.println("Null hitbox");
				return;
			}
		if (state < 0) {
			System.err.println("Invalid state");
			return;
		}
		if (type == HitBoxModel.ONES) {
			if (state > 4) {
				System.err.println("Invalid state");
				return;
			}
			for (HitBox h : box)
				h.setBounds(new Rectangle(-1, bounds.y, 0, bounds.height - 1));
			resetOnes(box, state, bounds);
			return;
		} else if (type == HitBoxModel.FIVES) {
			if (state > 3) {
				System.err.println("Invalid state");
				return;
			}
			for (HitBox h : box)
				h.setBounds(new Rectangle(-1, bounds.y, 0, bounds.height - 1));
			resetFives(box, state, bounds);
			return;
		}
		System.err.println("Invalid type");
		throw new IllegalArgumentException();
	}
	
	/**
	 *  Arranges the one's beads.
	 * @param box The list of hit boxes to modify.
	 * @param state What block state the beads are representing.
	 * @param bounds The block boundaries.
	 */
	protected abstract void resetOnes(HitBox[] box, int state, Rectangle bounds);
	
	/**
	 *  Arranges the five's beads.
	 * @param box The list of hit boxes to modify.
	 * @param state What block state the beads are representing.
	 * @param bounds The block boundaries.
	 */
	protected abstract void resetFives(HitBox[] box, int state, Rectangle bounds);
}
