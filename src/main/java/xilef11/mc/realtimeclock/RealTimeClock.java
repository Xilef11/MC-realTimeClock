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

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.relauncher.Side;
import xilef11.mc.realtimeclock.client.gui.Clock;
import xilef11.mc.realtimeclock.client.handler.RenderTickHandler;
import xilef11.mc.realtimeclock.handler.ConfigurationHandler;
import xilef11.mc.realtimeclock.proxy.IProxy;
import xilef11.mc.realtimeclock.references.Refs;
import xilef11.mc.realtimeclock.utilities.ModLogger;

/**
 * @author Xilef11
 *
 */
@Mod(modid = Refs.MOD_ID, name=Refs.MOD_NAME, version=Refs.MOD_VERSION, guiFactory=Refs.GUI_FACTORY_CLASS, canBeDeactivated=true,acceptableRemoteVersions="*", clientSideOnly=true)
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
		proxy.initConfig(event);
		//items & blocks
		ModLogger.logInfo("Pre Initialization complete");
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//ModLogger.logInfo("Initialization Starting");
		//keyBindings
		proxy.registerKeyBindings();
		//guis
		if(event.getSide()==Side.CLIENT){
			MinecraftForge.EVENT_BUS.register(new RenderTickHandler());
		}
		//crafting
		//tileEntities
		ModLogger.logInfo("Initialization complete");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//ModLogger.logInfo("Post Initialization starting");
		//stuff that needs to be done after init
		//set the "enabled" value of the clock
		if(Clock.isEnabled()!=ConfigurationHandler.display){
			Clock.toggleEnabled();
		}
		ModLogger.logInfo("Post Initialization complete");
	}
	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event){
		if(event.getSide()==Side.CLIENT){
			//when the server stops (hopefully this is the right event), save the state of the clock
			ModLogger.logInfo("Saving clock state for the next reload");
			ConfigurationHandler.setValueDisplay(Clock.isEnabled());
		}
	}
}
