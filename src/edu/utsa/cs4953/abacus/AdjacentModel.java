/**
 * AdjacentModel.java
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
 * Arranges hitboxes for beads that do not overlap beads.
 *
 */
public final class AdjacentModel extends HitBoxModel {
	/* (non-Javadoc)
	 * @see edu.utsa.cs4953.abacus.HitBoxModel#resetOnes(edu.utsa.cs4953.abacus.HitBox[], int, java.awt.Rectangle)
	 */
	@Override
	protected void resetOnes(HitBox[] box, int state, Rectangle bounds) {
		// Set up the hitboxes.

		switch (state) {
		case 0:
			box[7].setBounds(new Rectangle(bounds.x + 5, bounds.y, 38, bounds.height - 1));
			box[6].setBounds(new Rectangle(bounds.x + 43, bounds.y, 38, bounds.height - 1));
			box[5].setBounds(new Rectangle(bounds.x + 81, bounds.y, 38, bounds.height - 1));
			box[4].setBounds(new Rectangle(bounds.x + 119, bounds.y, 38, bounds.height - 1));
			break;
		case 1:
			box[6].setBounds(new Rectangle(bounds.x + 5, bounds.y, 38, bounds.height - 1));
			box[5].setBounds(new Rectangle(bounds.x + 43, bounds.y, 38, bounds.height - 1));
			box[4].setBounds(new Rectangle(bounds.x + 81, bounds.y, 38, bounds.height - 1));
			box[3].setBounds(new Rectangle(bounds.x + 157, bounds.y, 38, bounds.height - 1));
			break;
		case 2:
			box[5].setBounds(new Rectangle(bounds.x + 5, bounds.y, 38, bounds.height - 1));
			box[4].setBounds(new Rectangle(bounds.x + 43, bounds.y, 38, bounds.height - 1));
			box[3].setBounds(new Rectangle(bounds.x + 119, bounds.y, 38, bounds.height - 1));
			box[2].setBounds(new Rectangle(bounds.x + 157, bounds.y, 38, bounds.height - 1));
			break;
		case 3:
			box[4].setBounds(new Rectangle(bounds.x + 5, bounds.y, 38, bounds.height - 1));
			box[3].setBounds(new Rectangle(bounds.x + 81, bounds.y, 38, bounds.height - 1));
			box[2].setBounds(new Rectangle(bounds.x + 119, bounds.y, 38, bounds.height - 1));
			box[1].setBounds(new Rectangle(bounds.x + 157, bounds.y, 38, bounds.height - 1));
			break;
		case 4:
			box[3].setBounds(new Rectangle(bounds.x + 43, bounds.y, 38, bounds.height - 1));
			box[2].setBounds(new Rectangle(bounds.x + 81, bounds.y, 38, bounds.height - 1));
			box[1].setBounds(new Rectangle(bounds.x + 119, bounds.y, 38, bounds.height - 1));
			box[0].setBounds(new Rectangle(bounds.x + 157, bounds.y, 38, bounds.height - 1));
			break;
		}
	}

	/* (non-Javadoc)
	 * @see edu.utsa.cs4953.abacus.HitBoxModel#resetFives(edu.utsa.cs4953.abacus.HitBox[], int, java.awt.Rectangle)
	 */
	@Override
	protected void resetFives(HitBox[] box, int state, Rectangle bounds) {
		// Set up the hitboxes.
		switch (state) {
		case 0:
			box[4].setBounds(new Rectangle(bounds.x + 45, bounds.y, 38, bounds.height - 1));
			box[5].setBounds(new Rectangle(bounds.x + 83, bounds.y, 38, bounds.height - 1));
			box[6].setBounds(new Rectangle(bounds.x + 121, bounds.y, 38, bounds.height - 1));
			break;
		case 1:
			box[3].setBounds(new Rectangle(bounds.x + 7, bounds.y, 38, bounds.height - 1));
			box[4].setBounds(new Rectangle(bounds.x + 83, bounds.y, 38, bounds.height - 1));
			box[5].setBounds(new Rectangle(bounds.x + 121, bounds.y, 38, bounds.height - 1));
			break;
		case 2:
			box[2].setBounds(new Rectangle(bounds.x + 7, bounds.y, 38, bounds.height - 1));
			box[3].setBounds(new Rectangle(bounds.x + 45, bounds.y, 38, bounds.height - 1));
			box[4].setBounds(new Rectangle(bounds.x + 121, bounds.y, 38, bounds.height - 1));
			break;
		case 3:
			box[1].setBounds(new Rectangle(bounds.x + 7, bounds.y, 38, bounds.height - 1));
			box[2].setBounds(new Rectangle(bounds.x + 45, bounds.y, 38, bounds.height - 1));
			box[3].setBounds(new Rectangle(bounds.x + 83, bounds.y, 38, bounds.height - 1));
			break;
		}
	}
}
