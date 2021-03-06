/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-17
 */
package xilef11.mc.realtimeclock.client.settings;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import xilef11.mc.realtimeclock.references.Names;

/**
 * @author Xilef11
 *
 */
public class KeyBindings {

	public static KeyBinding toggle_clock=new KeyBinding(Names.Keys.TOGGLE_CLOCK, Keyboard.KEY_C, Names.Keys.CATEGORY);
}
