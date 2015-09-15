/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xilef11.mc.realtimeclock.utilities.ModLogger;

/**
 * @author Xilef11
 *
 */
public class ServerProxy extends CommonProxy implements IProxy {

	/* (non-Javadoc)
	 * @see xilef11.mc.realtimeclock.proxy.IProxy#registerKeyBindings()
	 */
	@Override
	public void registerKeyBindings() {
		// Nothing to do here!		
	}

	/* (non-Javadoc)
	 * @see xilef11.mc.realtimeclock.proxy.IProxy#initConfig(cpw.mods.fml.common.event.FMLPreInitializationEvent)
	 */
	@Override
	public void initConfig(FMLPreInitializationEvent event) {
		ModLogger.logWarn("This mod does not do anything on the server");

	}


}
