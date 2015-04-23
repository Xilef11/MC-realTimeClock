/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.proxy;

import xilef11.mc.realtimeclock.client.handler.KeyInputHandler;
import xilef11.mc.realtimeclock.client.settings.KeyBindings;
import xilef11.mc.realtimeclock.handler.ConfigurationHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		ClientRegistry.registerKeyBinding(KeyBindings.toggle_clock);		
	}

	/* (non-Javadoc)
	 * @see xilef11.mc.realtimeclock.proxy.IProxy#initConfig(cpw.mods.fml.common.event.FMLPreInitializationEvent)
	 */
	@Override
	public void initConfig(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
	}
	

}
