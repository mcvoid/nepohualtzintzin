/**
 * Themes.java
 * Handles graphics assets for themes.
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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * Loads and organizes the myriad graphics assets of bead states and
 * backgrounds.
 * 
 */
public class Theme {
	/**
	 * Creates a new theme from a directory on disk.
	 * 
	 * @param dir
	 *            The directory to load it from.
	 * @return If loaded successfully, returns the new theme. Returns null
	 *         otherwise.
	 */
	public static Theme loadFromDirectory(String dir) {
		Theme t = new Theme();
		t.images = new Hashtable<String, BufferedImage[]>();
		t.images.put("primary.ones", new BufferedImage[5]);
		t.images.put("primary.fives", new BufferedImage[4]);
		t.images.put("secondary.ones", new BufferedImage[5]);
		t.images.put("secondary.fives", new BufferedImage[4]);
		t.images.put("tertiary.ones", new BufferedImage[5]);
		t.images.put("tertiary.fives", new BufferedImage[4]);
		t.images.put("background.normal", new BufferedImage[1]);
		t.images.put("background.split", new BufferedImage[1]);

		Properties defaultDirectories = new Properties();
		defaultDirectories.put("primary.ones", "primary/ones");
		defaultDirectories.put("primary.fives", "primary/fives");
		defaultDirectories.put("secondary.ones", "secondary/ones");
		defaultDirectories.put("secondary.fives", "secondary/fives");
		defaultDirectories.put("tertiary.ones", "tertiary/ones");
		defaultDirectories.put("tertiary.fives", "tertiary/fives");
		defaultDirectories.put("backgrounds", "backgrounds");

		Properties directories = new Properties(defaultDirectories);
		try {
			String path = Settings.getThemeDirectory() + "/" + dir
					+ "/theme.manifest";
			FileInputStream in = new FileInputStream(path);
			directories.load(in);
		} catch (FileNotFoundException e) {
			directories = defaultDirectories;
		} catch (IOException e) {
			directories = defaultDirectories;
		}

		try {
			// load beads
			for (Object key : defaultDirectories.keySet()) {
				String currentKey = (String) key;
				if (currentKey.equals("backgrounds"))
					continue;

				String path = Settings.getThemeDirectory() + "/" + dir + "/";
				path += (directories.containsKey(currentKey) ? directories
						.getProperty(currentKey) : defaultDirectories
						.getProperty(currentKey));
				for (int i = 0; i < t.images.get(currentKey).length; i++) {
					String currentPath = path + "/Case" + i + ".png";
					t.images.get(currentKey)[i] = ImageIO.read(new File(
							currentPath));
				}

			}
			// Load backgrounds
			String path = Settings.getThemeDirectory() + "/" + dir + "/";
			path += (directories.containsKey("backgrounds") ? directories
					.getProperty("backgrounds") : defaultDirectories
					.getProperty("backgrounds"));
			t.images.get("background.normal")[0] = ImageIO.read(new File(path
					+ "/normal.png"));
			t.images.get("background.split")[0] = ImageIO.read(new File(path
					+ "/split.png"));
			//load hitboxes
			if (directories.containsKey("hitboxmodel")) {
				if (directories.get("hitboxmodel").equals("overlap"))
					t.hitBoxModel = new OverlapModel();
				else if (directories.get("hitboxmodel").equals("adjacent"))
					t.hitBoxModel = new AdjacentModel();
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			return null;
		}

		return t;
	}

	/**
	 * Creates a new theme stored inside the Jar file.
	 * 
	 * @param dir
	 *            The directory containing the theme.
	 * @return If loaded successfully, returns the new theme. Returns null
	 *         otherwise.
	 */
	public static Theme loadFromJar(String dir) {
		Theme t = new Theme();
		t.images = new Hashtable<String, BufferedImage[]>();
		t.images.put("primary.ones", new BufferedImage[5]);
		t.images.put("primary.fives", new BufferedImage[4]);
		t.images.put("secondary.ones", new BufferedImage[5]);
		t.images.put("secondary.fives", new BufferedImage[4]);
		t.images.put("tertiary.ones", new BufferedImage[5]);
		t.images.put("tertiary.fives", new BufferedImage[4]);
		t.images.put("background.normal", new BufferedImage[1]);
		t.images.put("background.split", new BufferedImage[1]);

		Properties defaultDirectories = new Properties();
		defaultDirectories.put("primary.ones", "primary/ones");
		defaultDirectories.put("primary.fives", "primary/fives");
		defaultDirectories.put("secondary.ones", "secondary/ones");
		defaultDirectories.put("secondary.fives", "secondary/fives");
		defaultDirectories.put("tertiary.ones", "tertiary/ones");
		defaultDirectories.put("tertiary.fives", "tertiary/fives");
		defaultDirectories.put("backgrounds", "backgrounds");

		Properties directories = new Properties(defaultDirectories);
		try {
			directories.load(Theme.class.getClass().getResourceAsStream(
					"/" + dir + "/theme.manifest"));
		} catch (FileNotFoundException e) {
			directories = defaultDirectories;
		} catch (IOException e) {
			directories = defaultDirectories;
		}

		try {
			// load beads
			for (Object key : defaultDirectories.keySet()) {
				String currentKey = (String) key;
				if (currentKey.equals("backgrounds"))
					continue;

				String path = "/" + dir + "/";
				path += (directories.containsKey(currentKey) ? directories
						.getProperty(currentKey) : defaultDirectories
						.getProperty(currentKey));
				for (int i = 0; i < t.images.get(currentKey).length; i++) {
					String currentPath = path + "/Case" + i + ".png";
					t.images.get(currentKey)[i] = ImageIO.read(Theme.class
							.getClass().getResource(currentPath));
				}

			}
			// Load backgrounds
			String path = "/" + dir + "/";
			path += (directories.containsKey("backgrounds") ? directories
					.getProperty("backgrounds") : defaultDirectories
					.getProperty("backgrounds"));
			t.images.get("background.normal")[0] = ImageIO.read(Theme.class
					.getClass().getResource(path + "/normal.png"));
			t.images.get("background.split")[0] = ImageIO.read(Theme.class
					.getClass().getResource(path + "/split.png"));
			//load hitboxes
			if (directories.containsKey("hitboxmodel")) {
				if (directories.get("hitboxmodel").equals("overlap"))
					t.hitBoxModel = new OverlapModel();
				else if (directories.get("hitboxmodel").equals("adjacent"))
					t.hitBoxModel = new AdjacentModel();
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			return null;
		}

		return t;
	}

	/**
	 * The arrangement of hitboxes.
	 */
	private HitBoxModel hitBoxModel;

	/**
	 * The collections of all images required in the theme.
	 */
	private Hashtable<String, BufferedImage[]> images;

	/**
	 * Constructor
	 */
	private Theme() {
		hitBoxModel = new AdjacentModel();
	}

	/**
	 * Gets the appropriate background image to be displayed.
	 * 
	 * @return The appropriate background image.
	 */
	public BufferedImage getBackground() {
		if (Settings.getMode() == Mode.NORMAL)
			return images.get("background.normal")[0];
		else
			return images.get("background.split")[0];
	}

	/**
	 * Gets the appropriate set of images for the fives' block of a particular
	 * row.
	 * 
	 * @param i
	 *            The row in question.
	 * @return The images the fives' block will use.
	 */
	public BufferedImage[] getFivesImagesForRow(int i) {
		if (i < 0 || i >= 13)
			throw new IllegalArgumentException("Only rows 0-12 are valid.");
		switch (i) {
		case 0:
		case 1:
		case 2:
		case 6:
		case 7:
		case 8:
			return images.get("primary.fives");
		case 3:
		case 4:
		case 5:
		case 9:
		case 10:
		case 11:
			return images.get("secondary.fives");
		default:
			return images.get("tertiary.fives");
		}
	}

	/**
	 * The arrangement of hitboxes.
	 * @return the hitBoxModel
	 */
	public HitBoxModel getHitBoxModel() {
		return hitBoxModel;
	}

	/**
	 * Gets the appropriate set of images for the ones' block of a particular
	 * row.
	 * 
	 * @param i
	 *            The row in question.
	 * @return The images the ones' block will use.
	 */
	public BufferedImage[] getOnesImagesForRow(int i) {
		if (i < 0 || i >= 13)
			throw new IllegalArgumentException("Only rows 0-12 are valid.");
		switch (i) {
		case 0:
		case 1:
		case 2:
		case 6:
		case 7:
		case 8:
			return images.get("primary.ones");
		case 3:
		case 4:
		case 5:
		case 9:
		case 10:
		case 11:
			return images.get("secondary.ones");
		default:
			return images.get("tertiary.ones");
		}
	}
}
