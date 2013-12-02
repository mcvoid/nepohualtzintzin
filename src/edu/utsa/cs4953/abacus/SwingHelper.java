/**
 * SwingHelper.java
 * Handles the loading repetitive code involved in Swing control management.
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

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

/**
 * Contains helper functions for creating and managing Swing stuff.
 * 
 */
public class SwingHelper {
	/**
	 * Adds an array of Components to a Container at once.
	 * 
	 * @param a
	 *            The Components to add.
	 * @param c
	 *            The Container to add to.
	 */
	public static void addAll(Component[] a, Container c) {
		for (Component b : a)
			c.add(b);
	}

	/**
	 * Puts a single Component into a new labeled Panel.
	 * 
	 * @param a
	 *            The Component to add.
	 * @param label
	 *            The label of the panel.
	 * @return A Panel with the given label and the component inside.
	 */
	public static JPanel addToLabeledPanel(Component a, String label) {
		Component[] array = { a };
		return addToLabeledPanel(array, label);
	}

	/**
	 * Puts an array of components into a new panel with a label.
	 * 
	 * @param a
	 *            The Components to add.
	 * @param label
	 *            The label of the panel.
	 * @return A Panel with the given label and the components inside.
	 */
	public static JPanel addToLabeledPanel(Component[] a, String label) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, a.length));
		addAll(a, panel);
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), label));
		return panel;
	}

	/**
	 * Makes a new CheckBox with given properties.
	 * 
	 * @param name
	 *            The label of the control.
	 * @param command
	 *            The name the action listener is looking for.
	 * @param isSelected
	 *            The condition on which this control is selected.
	 * @param listener
	 *            The action listener.
	 * @return A new CheckBox with proper default properties.
	 */
	public static JCheckBox createCheckBox(String name, String command,
			boolean isSelected, ActionListener listener) {
		JCheckBox button;
		button = new JCheckBox(name);
		button.setActionCommand(command);
		button.setSelected(isSelected);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * Makes a new top-level menu.
	 * 
	 * @param items
	 *            An array of its child menu items.
	 * @param name
	 *            The name of the menu.
	 * @param description
	 *            The accessible description.
	 * @param mnemonic
	 *            The key (when pressed with Alt) that activates the menu.
	 * @return A properly-initialized new menu.
	 */
	public static JMenu createMenu(JMenuItem[] items, String name,
			String description, int mnemonic) {
		JMenu menu = new JMenu(name);
		menu.getAccessibleContext().setAccessibleDescription(description);
		menu.setMnemonic(mnemonic);
		addAll(items, menu);
		return menu;
	}

	/**
	 * Makes a new menu item.
	 * 
	 * @param name
	 *            The name of the menu item.
	 * @param description
	 *            The accessible description.
	 * @param mnemonic
	 *            The key (when pressed with Alt) that activates the menu item.
	 * @param accelerator
	 *            The shortcut key sequence that activates the menu item.
	 * @param listener
	 *            The action listener.
	 * @return A properly-initialized new menu item.
	 */
	public static JMenuItem createMenuItem(String name, String description,
			int mnemonic, int accelerator, ActionListener listener) {
		JMenuItem item = new JMenuItem(name, mnemonic);
		item.getAccessibleContext().setAccessibleDescription(description);
		item.setAccelerator(KeyStroke.getKeyStroke(accelerator, Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask()));
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates a radio button with all the properties set up front.
	 * 
	 * @param name
	 *            The caption of the button.
	 * @param command
	 *            The name of the command the listener should look for.
	 * @param isSelected
	 *            The condition that should be true if this control is selected.
	 * @param listener
	 *            The object listening for commands.
	 * @return A configured radio button.
	 */
	public static JRadioButton createRadioButton(String name, String command,
			boolean isSelected, ActionListener listener) {
		JRadioButton button;
		button = new JRadioButton(name);
		button.setActionCommand(command);
		button.setSelected(isSelected);
		button.addActionListener(listener);
		return button;
	}

	/**
	 * Puts an array of radio buttons into a group so they are mutually
	 * exclusive to each other.
	 * 
	 * @param buttonArray
	 *            The buttons to group.
	 */
	public static void groupRadioButtons(JRadioButton[] buttonArray) {
		ButtonGroup g = new ButtonGroup();
		for (JRadioButton r : buttonArray) {
			g.add(r);
		}
	}
}
