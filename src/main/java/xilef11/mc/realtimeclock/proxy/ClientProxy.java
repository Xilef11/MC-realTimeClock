/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.proxy;

import xilef11.mc.realtimeclock.client.settings.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * @author Xilef11
 *
 */
public class ClientProxy extends CommonProxy implements IProxy {

	/* (non-Javadoc)
	 * @see xilef11.mc.realtimeclock.proxy.IProxy#registerKeyBindings()
	 */
	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(KeyBindings.toggle_clock);		
	}

}
