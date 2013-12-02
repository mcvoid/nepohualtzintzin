/**
 * MenuHander.java
 * Handles the myriad menu options.
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Event handler for clicking a button.
 * 
 */
public class MenuHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean needsResizing = false;

		// Process menu commands
		if (e.getActionCommand().equals("About")) {
			displayAboutBox();
		} else if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Reset")) {
			Settings.getCurrentSoundEffect().play();
			Board.getInstance().Reset();
		} else if (e.getActionCommand().equals("Settings")) {
			new SettingsPane();
		} else if (e.getActionCommand().equals("Trivia")) {
			Trivia.displayRandomTrivia();
		} else if (e.getActionCommand().equals("Increment")) {
			try {
				Settings.getBoard().add(1);
				Settings.getCurrentSoundEffect().play();
			} catch (IllegalArgumentException ex) { /* do nothing */
			}
		} else if (e.getActionCommand().equals("Decrement")) {
			try {
				Settings.getBoard().subtract(1);
				Settings.getCurrentSoundEffect().play();
			} catch (IllegalArgumentException ex) { /* do nothing */
			}
		}

		// Process Settings menu stuff
		if (e.getActionCommand().equals("Toggle Decimal Numerals")) {
			Settings.toggleStatusPanel();
			needsResizing = true;
		} else if (e.getActionCommand().equals("Toggle Mayan Numerals")) {
			Settings.toggleMayanNumerals();
			needsResizing = true;
		} else if (e.getActionCommand().equals("Addition")) {
			Settings.setMode(Mode.NORMAL);
			needsResizing = true;
		} else if (e.getActionCommand().equals("Multiplication")) {
			Settings.setMode(Mode.SPLIT);
			needsResizing = true;
		} else if (e.getActionCommand().equals("Save")) {
			saveSettings();
		} else if (e.getActionCommand().equals("Sound Click")) {
			Settings.setCurrentSoundEffect(SoundEffect.CLICK);
		} else if (e.getActionCommand().equals("Sound Blip")) {
			Settings.setCurrentSoundEffect(SoundEffect.BLIP);
		} else if (e.getActionCommand().equals("Sound Off")) {
			Settings.setCurrentSoundEffect(SoundEffect.NONE);
		} else if (e.getActionCommand().equals("Enable Trivia on Startup"))
			Settings.setShowTrivia(!Settings.isShowTrivia());

		if (needsResizing)
			Settings.getBoard().setNeedsResizing(true);
		Program.content.repaint();
	}

	/**
	 * Displays the about box.
	 */
	private void displayAboutBox() {
		String text = "";
		Scanner s = new Scanner(getClass().getResourceAsStream("/About.html"));
		while (s.hasNextLine())
			text += s.nextLine();
		JOptionPane.showMessageDialog(null, new JLabel(text),
				"About Nepohualtzintzin", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/images/mayazero.png")));
	}

	/**
	 * Saves settings and displays confirmation dialog.
	 */
	private void saveSettings() {
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/images/mayazero_small.png"));
		String message = Settings.saveUserSettings();
		int type = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(null, message, "Settings", type, icon);
	}
}