/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-17
 */
package xilef11.mc.realtimeclock.client.handler;

import xilef11.mc.realtimeclock.client.settings.KeyBindings;
import xilef11.mc.realtimeclock.references.Key;
import xilef11.mc.realtimeclock.utilities.ModLogger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/**
 * @author Xilef11
 *
 */
public class KeyInputHandler {
	@SubscribeEvent
	public void handleKeyInput(InputEvent.KeyInputEvent event){
		if(getPressedKeyBinding()==Key.TOGGLE_CLOCK){
			//TODO DO THE THINGS
		}
	}
	
	private static Key getPressedKeyBinding(){
		if(KeyBindings.toggle_clock.isPressed()){
			return Key.TOGGLE_CLOCK;
		}
		return Key.UNKNOWN;
	}
}
