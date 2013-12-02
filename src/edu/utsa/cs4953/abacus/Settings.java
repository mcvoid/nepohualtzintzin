/**
 * Settings.java
 * Contains data needed by all objects.
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;

/**
 * A place holder for data that's better off being global.
 * 
 */
public class Settings {
	/**
	 * The only instance of a Settings object
	 */
	private static Settings instance;

	/**
	 * Loads a new theme with the current name and adds it to the list.
	 * 
	 * @param name
	 */
	public static void addTheme(String name) {
		Theme t = Theme.loadFromDirectory(name);
		if (t != null)
			getInstance().theme.put(name, t);
	}

	/**
	 * A list of all theme names.
	 * 
	 * @return An array of strings representing a list of themes.
	 */
	public static String[] getAllThemes() {
		return getInstance().theme.keySet().toArray(new String[0]);
	}

	/**
	 * Gets a reference to the board.
	 * 
	 * @return the board
	 */
	public static Board getBoard() {
		return (Board) getInstance().settings.get("Board");
	}

	/**
	 * The current sounds effect that will be played when beads click.
	 * 
	 * @return The playable SoundEffect.
	 */
	public static SoundEffect getCurrentSoundEffect() {
		return (SoundEffect) getInstance().settings.get("CurrentSoundEffect");
	}

	/**
	 * The theme that's currently being used.
	 * 
	 * @return An object representing the current theme.
	 */
	public static Theme getCurrentTheme() {
		if (!getInstance().settings.containsKey("CurrentTheme"))
			return null;
		String currentTheme = getCurrentThemeName();
		if (getInstance().theme.containsKey(currentTheme))
			return getInstance().theme.get(currentTheme);
		return null;
	}

	/**
	 * The name of the current theme.
	 * 
	 * @return A string representing the theme name.
	 */
	public static String getCurrentThemeName() {
		return (String) getInstance().settings.get("CurrentTheme");
	}

	/**
	 * Gets the current theme's position in the list of themes.
	 * 
	 * @return An integer representing the ordinal position of the current
	 *         string.
	 */
	public static int getCurrentThemeOrdinal() {
		int i = 0;
		for (String s : getAllThemes()) {
			if (s.equals(getCurrentThemeName()))
				return i;
			i++;
		}
		return -1;
	}

	/**
	 * @return the statusPanel
	 */
	public static DecimalPanel getDecimalPanel() {
		return (DecimalPanel) getInstance().settings.get("StatusPanel");
	}

	/**
	 * A reference to the drawing panel.
	 * 
	 * @return the drawingPanel
	 */
	public static DrawingPanel getDrawingPanel() {
		return (DrawingPanel) getInstance().settings.get("DrawingPanel");
	}

	/**
	 * Gets Settings's only reference and initializes if appropriate.
	 * 
	 * @return The
	 */
	private static Settings getInstance() {
		if (instance == null)
			instance = new Settings();
		return instance;
	}

	/**
	 * A reference to the mayan numeral panel.
	 * 
	 * @return the mayaPanel
	 */
	public static MayaNumeralPanel getMayaPanel() {
		return (MayaNumeralPanel) getInstance().settings.get("MayaPanel");
	}

	/**
	 * Whether the board is drawn split or not.
	 * 
	 * @return the mode.
	 */
	public static Mode getMode() {
		return (Mode) getInstance().settings.get("Mode");
	}

	/**
	 * Gets the application's parent window.
	 * 
	 * @return the program
	 */
	public static Program getProgram() {
		return (Program) getInstance().settings.get("Program");
	}

	/**
	 * Default directory where themes are stored.
	 * 
	 * @return the theme Directory.
	 */
	public static String getThemeDirectory() {
		return (String) getInstance().settings.get("ThemeDirectory");
	}

	/**
	 * Show the clickable hotspots.
	 * 
	 * @return the showHitboxes
	 */
	public static boolean isShowHitboxes() {
		return (Boolean) getInstance().settings.get("HitBoxes");
	}

	/**
	 * Whether or not trivia will be displayed.
	 * 
	 * @return the showTrivia
	 */
	public static boolean isShowTrivia() {
		return (Boolean) getInstance().settings.get("Trivia");
	}

	/**
	 * Loads user and system settings from config files.
	 */
	public static void loadSettings() {
		// Loads built-in theme.
		Theme t = Theme.loadFromJar("defaultTheme");
		if (t != null)
			getInstance().theme.put("Stone", t);
		else
			System.err.println("Cannot find default theme!");
		// loads all themes
		try {
			Scanner s = new Scanner(new File(getThemeDirectory()
					+ "/themes.info"));
			while (s.hasNext())
				Settings.addTheme(s.next().trim());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}

		// Load custom user preferences
		boolean userSettingsExist = true;
		Properties user = new Properties();
		String homeDirectory = System.getProperty("user.home");
		try {
			FileInputStream in = new FileInputStream(homeDirectory
					+ "/.nepo.settings");
			user.load(in);
		} catch (FileNotFoundException e) {
			userSettingsExist = false;
		} catch (IOException e) {
			userSettingsExist = false;
		}

		// load settings from properties
		if (userSettingsExist) {
			// maya numeral panel visible
			if (user.containsKey("maya"))
				getMayaPanel().setVisible(user.get("maya").equals("on"));
			else
				getMayaPanel().setVisible(true);
			// decimal numeral panel visible
			if (user.containsKey("decimal"))
				getDecimalPanel().setVisible(user.get("decimal").equals("on"));
			else
				getDecimalPanel().setVisible(true);
			// show trivia on start
			if (user.containsKey("trivia"))
				setShowTrivia(user.get("trivia").equals("on"));
			// set board mode
			if (user.containsKey("mode")) {
				if (user.get("mode").equals("normal"))
					setMode(Mode.NORMAL);
				else if (user.get("mode").equals("split"))
					setMode(Mode.SPLIT);
			}
			// set current theme
			if (user.containsKey("theme"))
				Settings.setCurrentTheme(user.getProperty("theme"));
			// display hitboxes
			if (user.containsKey("hitboxes"))
				setShowHitboxes(user.get("hitboxes").equals("on"));
			// play sound effects
			if (user.containsKey("soundeffect")) {
				setCurrentSoundEffect(SoundEffect.getSoundByName((String) user
						.get("soundeffect")));
			}
		}

		// initialize sound.
		SoundEffect.load();
		// push settings to board.
		getDrawingPanel()
				.setPreferredSize(
						new Dimension(getMode().getWidth(),
								DrawingPanel.DEFAULT_HEIGHT));
		Board.getInstance()
				.setSize(
						new Dimension(getMode().getWidth(),
								DrawingPanel.DEFAULT_HEIGHT));
	}

	/**
	 * Saves user options to their home directory.
	 */
	public static String saveUserSettings() {
		Properties user = new Properties();
		user.setProperty("mode", (getMode() == Mode.NORMAL) ? "normal"
				: "split");
		user.setProperty("decimal", (getDecimalPanel().isVisible()) ? "on"
				: "off");
		user.setProperty("maya", (getMayaPanel().isVisible()) ? "on" : "off");
		user.setProperty("theme", getCurrentThemeName());
		user.setProperty("trivia", (isShowTrivia()) ? "on" : "off");
		user.setProperty("soundeffect", getCurrentSoundEffect().getName());
		user.setProperty("hitboxes", (isShowHitboxes()) ? "on" : "off");

		String homeDirectory = System.getProperty("user.home");
		try {
			FileOutputStream out = new FileOutputStream(homeDirectory
					+ "/.nepo.settings");
			user.store(out, "Nepohualtzintzin User Settings");
		} catch (FileNotFoundException e) {
			return "User preferences cannot be saved.";
		} catch (IOException e) {
			return "User preferences cannot be saved.";
		}
		return "User preferences saved";
	}

	/**
	 * Creates and stores the Board instance.
	 */
	public static void setBoard() {
		getInstance().settings.put("Board", Board.getInstance());
	}

	/**
	 * Changes the current sounds effect that will be played when beads click.
	 * 
	 * @param s
	 *            The new SoundEffect.
	 */
	public static void setCurrentSoundEffect(SoundEffect s) {
		getInstance().settings.put("CurrentSoundEffect", s);
	}

	/**
	 * Changed the current theme to the one with the name specified.
	 * 
	 * @param s
	 *            The theme name as a string.
	 */
	public static void setCurrentTheme(String s) {
		if (getInstance().theme.containsKey(s))
			getInstance().settings.put("CurrentTheme", s);
		else
			System.err.println("Theme " + s + " does not exist!");
	}

	/**
	 * Turns the decimal numerals on or off.
	 * 
	 * @param b
	 *            Whether the panel should be visible.
	 */
	public static void setDecimalDigits(boolean b) {
		getDecimalPanel().setVisible(b);
	}

	/**
	 * Sets reference to the drawing panel.
	 * 
	 * @param drawingPanel
	 *            the drawingPanel to set
	 */
	public static DrawingPanel setDrawingPanel(DrawingPanel drawingPanel) {
		getInstance().settings.put("DrawingPanel", drawingPanel);
		return drawingPanel;
	}

	/**
	 * Turns the maya numerals on or off.
	 * 
	 * @param b
	 *            Whether the panel should be visible.
	 */
	public static void setMayanNumerals(boolean b) {
		getMayaPanel().setVisible(b);
	}

	/**
	 * Change the reference to the mayan numeral panel.
	 * 
	 * @param mayaPanel
	 *            the mayaPanel to set
	 */
	public static MayaNumeralPanel setMayaPanel(MayaNumeralPanel mayaPanel) {
		getInstance().settings.put("MayaPanel", mayaPanel);
		return mayaPanel;
	}

	/**
	 * Sets whether the board is drawn split or not.
	 * 
	 * @param mode
	 *            the mode to set.
	 */
	public static void setMode(Mode mode) {
		getInstance().settings.put("Mode", mode);
	}

	/**
	 * Sets the application's parent window.
	 * 
	 * @param program
	 *            the program to set
	 */
	public static void setProgram(Program program) {
		getInstance().settings.put("Program", program);
	}

	/**
	 * Enable or disable showing the clickable hotspots.
	 * 
	 * @param showHitboxes
	 *            the showHitboxes to set
	 */
	public static void setShowHitboxes(boolean showHitboxes) {
		getInstance().settings.put("HitBoxes", showHitboxes);
	}

	/**
	 * Sets whether or not trivia will be displayed.
	 * 
	 * @param showTrivia
	 *            the showTrivia to set
	 */
	public static void setShowTrivia(boolean showTrivia) {
		getInstance().settings.put("Trivia", showTrivia);
	}

	/**
	 * @param statusPanel
	 *            the statusPanel to set
	 */
	public static DecimalPanel setStatusPanel(DecimalPanel statusPanel) {
		getInstance().settings.put("StatusPanel", statusPanel);
		return statusPanel;
	}

	/**
	 * The total number of themes currently loaded.
	 * 
	 * @return An integer representing the total theme count.
	 */
	public static int themeCount() {
		return getInstance().theme.size();
	}

	/**
	 * Makes the Mayan Numeral Panel visible/invisible.
	 */
	public static void toggleMayanNumerals() {
		getMayaPanel().setVisible(!getMayaPanel().isVisible());
	}

	/**
	 * Makes the decimal Numeral Panel visible/invisible.
	 */
	public static void toggleStatusPanel() {
		getDecimalPanel().setVisible(!getDecimalPanel().isVisible());
	}

	/**
	 * A collection of all available themes loaded from disk.
	 */
	private Hashtable<String, Theme> theme;

	/**
	 * The repository of all program settings.
	 */
	private Hashtable<String, Object> settings;

	/**
	 * Private constructor.
	 */
	private Settings() {
		settings = new Hashtable<String, Object>();
		theme = new Hashtable<String, Theme>();
		settings.put("ThemeDirectory", "themes/");
		settings.put("CurrentSoundEffect", SoundEffect.CLICK);
		settings.put("Trivia", true);
		settings.put("CurrentTheme", "Stone");
		settings.put("HitBoxes", false);
		settings.put("Mode", Mode.NORMAL);
	}
}