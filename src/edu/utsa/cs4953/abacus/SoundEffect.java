/**
 * SoundEffect.java
 * Handles the sound playing in the program.
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

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The collection of all sound effects.
 */
public enum SoundEffect {
	// A clicking sound.
	CLICK("/sound/click.wav", "click"),
	// An 8-bit crazy sound.
	BLIP("/sound/blip.wav", "blip"),
	// No sound.
	NONE("", "off");

	/**
	 * Gets the sound effect with a given name.
	 * 
	 * @param name
	 *            The name to search for.
	 * @return The sound effect with that name.
	 */
	public static SoundEffect getSoundByName(String name) {
		for (SoundEffect s : SoundEffect.values()) {
			if (s.name.equals(name))
				return s;
		}
		return NONE;
	}

	/**
	 * Loads all the sounds from disk.
	 */
	static void load() {
		values();
	}

	/**
	 * The actual sound that plays.
	 */
	private Clip clip;

	/**
	 * The name of the clip.
	 */
	private String name;

	/**
	 * Constructor
	 * 
	 * @param soundFileName
	 *            A sound file. Use of the above constant is encouraged.
	 */
	private SoundEffect(String soundFileName, String name) {
		this.name = name;
		if (!soundFileName.equals("")) {
			try {
				// Load sound file
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(getClass().getResource(
								soundFileName));
				// Get a clip resource.
				clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioInputStream);
			} catch (UnsupportedAudioFileException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} catch (LineUnavailableException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * The string representation of the Sound Effect.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Plays the sound effect from beginning.
	 */
	public void play() {
		if (this == NONE)
			return;
		if (clip.isRunning())
			clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
}
