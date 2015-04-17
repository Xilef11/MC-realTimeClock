/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  File created by Xilef11 on 2015-04-15
 */
package xilef11.mc.realtimeclock;

import xilef11.mc.realtimeclock.handler.ConfigurationHandler;
import xilef11.mc.realtimeclock.proxy.IProxy;
import xilef11.mc.realtimeclock.references.Refs;
import xilef11.mc.realtimeclock.utilities.ModLogger;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Xilef11
 *
 */
@Mod(modid = Refs.MOD_ID, name=Refs.MOD_NAME, version=Refs.MOD_VERSION, guiFactory=Refs.GUI_FACTORY_CLASS)
public class RealTimeClock {
	
	@Mod.Instance(Refs.MOD_ID)
	public static RealTimeClock instance;
	
	@SidedProxy(clientSide=Refs.CLIENT_PROXY_CLASS, serverSide=Refs.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		//ModLogger.logInfo("Pre Initialization Starting");
		//network handling
		//mod config
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		//is this even needed? FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		//items & blocks
		ModLogger.logInfo("Pre Initialization complete");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//ModLogger.logInfo("Initialization Starting");
		//guis
		//crafting
		//tileEntities
		ModLogger.logInfo("Initialization complete");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//ModLogger.logInfo("Post Initialization starting");
		//stuff that needs to be done after init
		ModLogger.logInfo("Post Initialization complete");
	}
}
