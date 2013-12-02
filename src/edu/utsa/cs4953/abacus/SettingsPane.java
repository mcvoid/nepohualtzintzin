/**
 * SettingsPane.java
 * A frame of changeable program settings.
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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Lets the user change the program settings.
 * 
 */
public class SettingsPane extends JFrame {
	/**
	 * Included because JFrame implements the Serializable interface.
	 */
	private static final long serialVersionUID = 3368918122632941567L;

	/**
	 * Default Constructor.
	 */
	public SettingsPane() {

		setTitle("Settings");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		MenuHandler listener = new MenuHandler();

		// Layout panel
		JRadioButton[] layout = {
				SwingHelper.createRadioButton("Normal", "Addition",
						Settings.getMode() == Mode.NORMAL, listener),
				SwingHelper.createRadioButton("Split", "Multiplication",
						Settings.getMode() == Mode.SPLIT, listener) };
		SwingHelper.groupRadioButtons(layout);
		contentPane.add(SwingHelper.addToLabeledPanel(layout, "Layout"));

		// Numerals panel
		JCheckBox[] digits = {
				SwingHelper.createCheckBox("Mayan Numerals",
						"Toggle Mayan Numerals", Settings.getMayaPanel()
								.isVisible(), listener),
				SwingHelper.createCheckBox("Decimal Numerals",
						"Toggle Decimal Numerals", Settings.getDecimalPanel()
								.isVisible(), listener) };
		contentPane.add(SwingHelper
				.addToLabeledPanel(digits, "Numeral Display"));

		// Theme panel
		JComboBox themeSelector = new JComboBox(Settings.getAllThemes());
		themeSelector.setSelectedIndex(Settings.getCurrentThemeOrdinal());
		themeSelector.addActionListener(new ThemeHandler());
		contentPane.add(SwingHelper.addToLabeledPanel(themeSelector, "Theme"));

		// Sound effect panel.
		JRadioButton[] sounds = {
				SwingHelper.createRadioButton("Click", "Sound Click",
						Settings.getCurrentSoundEffect() == SoundEffect.CLICK,
						listener),
				SwingHelper.createRadioButton("Blip", "Sound Blip",
						Settings.getCurrentSoundEffect() == SoundEffect.BLIP,
						listener),
				SwingHelper.createRadioButton("Off", "Sound Off",
						Settings.getCurrentSoundEffect() == SoundEffect.NONE,
						listener) };
		SwingHelper.groupRadioButtons(sounds);
		contentPane.add(SwingHelper.addToLabeledPanel(sounds, "Sound Effect"));

		// Check box showing trivia.
		JCheckBox trivia = SwingHelper.createCheckBox(
				"Enable Trivia on Startup", "Enable Trivia on Startup",
				Settings.isShowTrivia(), listener);
		contentPane.add(SwingHelper.addToLabeledPanel(trivia, "Show Trivia"));

		// Save button
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(listener);
		contentPane.add(SwingHelper.addToLabeledPanel(saveButton,
				"Save User Preferences"));
		pack();
		this.setResizable(false);
		setVisible(true);
	}
}
