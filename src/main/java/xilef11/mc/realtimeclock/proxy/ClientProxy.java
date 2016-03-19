/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xilef11.mc.realtimeclock.client.handler.KeyInputHandler;
import xilef11.mc.realtimeclock.client.settings.KeyBindings;
import xilef11.mc.realtimeclock.handler.ConfigurationHandler;

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
		MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
		ClientRegistry.registerKeyBinding(KeyBindings.toggle_clock);		
	}

	/* (non-Javadoc)
	 * @see xilef11.mc.realtimeclock.proxy.IProxy#initConfig(cpw.mods.fml.common.event.FMLPreInitializationEvent)
	 */
	@Override
	public void initConfig(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
	}


}
