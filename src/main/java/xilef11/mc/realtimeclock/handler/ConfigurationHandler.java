/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.handler;

import java.io.File;
import java.io.IOException;

import xilef11.mc.realtimeclock.references.Refs;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * @author Xilef11
 *
 */
public class ConfigurationHandler {

		public static Configuration config;
		
		public static boolean testValue=false;
		//position de l'horloge
		public static float clockPosX,clockPosY;
	
		public static void init(File configFile){
			
			if(config==null){
				config = new Configuration(configFile);
				loadConfiguration();
			}
		}
		
		@SubscribeEvent
		public static void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
			if(event.modID.equals(Refs.MOD_ID)){
				//resync configs
				loadConfiguration();
			}
		}
		
		private static void loadConfiguration(){
			
				//read properties
				testValue = config.getBoolean("testValue",Configuration.CATEGORY_GENERAL,true,"example config value");
				//position
				clockPosX=config.getFloat("posX", Configuration.CATEGORY_GENERAL, 0, 0, Float.MAX_VALUE, "Horizontal (X) position of the Clock HUD");
				clockPosY=config.getFloat("posY", Configuration.CATEGORY_GENERAL, 0, 0, Float.MAX_VALUE, "Horizontal (X) position of the Clock HUD");
				if(config.hasChanged()) config.save();
			
		}
}
