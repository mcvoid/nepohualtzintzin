/**
 * Trivia.java
 * Handles the loading and display of bits of educational trivia.
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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Displays bits of educational trivia.
 * 
 */
public class Trivia {
	/**
	 * A list of all trivia loaded from a file.
	 */
	private static ArrayList<String> trivia;
	/**
	 * A random number generator.
	 */
	private static Random r = new Random();

	/**
	 * Show a message dialog with a random bit of trivia.
	 */
	public static void displayRandomTrivia() {
		if (trivia == null)
			JOptionPane.showMessageDialog(null, "Trivia file not loaded.");
		int i = Math.abs(r.nextInt() % trivia.size());
		JOptionPane.showMessageDialog(null,
				"Did you know...\n" + trivia.get(i), "Random knowledge",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Trivia.class
						.getClass().getResource("/images/mayazero_small.png")));
	}

	/**
	 * Loads bits of trivia from a text file, one line per tidbit.
	 * 
	 * @param filename
	 *            The file containing trivia.
	 * @throws FileNotFoundException
	 *             The file in question is missing.
	 */
	public static void LoadTriviaFromFile(String filename) {
		Scanner s = new Scanner(Trivia.class.getClass().getResourceAsStream(
				filename));
		trivia = new ArrayList<String>();
		while (s.hasNextLine()) {
			trivia.add(s.nextLine());
		}
	}
}
