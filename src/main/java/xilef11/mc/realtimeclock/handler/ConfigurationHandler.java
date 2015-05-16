/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.handler;

import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Level;

import xilef11.mc.realtimeclock.references.Refs;
import xilef11.mc.realtimeclock.utilities.ModLogger;
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
		
		//Time as 24H
		public static boolean use24hours=true;
		//position de l'horloge
		public static float clockPosX,clockPosY;
		//scale
		public static float clockScale;
		//color
		public static int color;
		public static boolean drawShadow;
		//show in which menus
		//public static boolean showPause;
		//is the clock displayed
		public static boolean display;
		public static final String CATEGORY_VALUES="stored values";
		
		public static void init(File configFile){
			
			if(config==null){
				config = new Configuration(configFile);
				loadConfiguration();
			}
		}
		
		@SubscribeEvent
		public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
			if(event.modID.equals(Refs.MOD_ID)){
				//resync configs
				loadConfiguration();
			}
		}
		
		private static void loadConfiguration(){
			
				//read properties
				//24 hours?
				use24hours=config.getBoolean("use24hours", Configuration.CATEGORY_GENERAL, true, "Set to false to use a 12-hour clock");
				//position
				clockPosX=config.getFloat("posX", Configuration.CATEGORY_GENERAL, 0.5F, 0, 100, "Horizontal (X) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
				clockPosY=config.getFloat("posY", Configuration.CATEGORY_GENERAL, 95F, 0, 100, "Vertical (Y) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
				//size
				clockScale=config.getFloat("clockScale", Configuration.CATEGORY_GENERAL, 120, 0, Float.MAX_VALUE, "The Size of the clock (in % of the standard MC String size)");
				//colour
				Pattern hexCol= Pattern.compile("[0-9[A-F]]{6}?");
				String col=config.getString("color", Configuration.CATEGORY_GENERAL, "AAAAAA", "Colour of the clock display, in Hexadecimal (from 0 to F for each of RRGGBB)", hexCol);
				try{
					color=Integer.parseInt(col, 16);
				}catch(NumberFormatException e){
					ModLogger.logException(Level.WARN, e, "Wrong Color String");
				}
				drawShadow=config.getBoolean("drawShadow", Configuration.CATEGORY_GENERAL, true, "Set to false to disable drawing the Shadow of the clock (recommended for dark colors)");
				//show on pause menu
				//showPause=config.getBoolean("showDebug", Configuration.CATEGORY_GENERAL, true, "if true, the clock will always be shown in the pause menu");
				//is the clock displayed?
				config.addCustomCategoryComment(CATEGORY_VALUES, "This section is used to store values between reloads");
				display=config.getBoolean("displayClock", CATEGORY_VALUES, true, "is the clock displayed?");
				if(config.hasChanged()){
					//ModLogger.logInfo("Config has changed");
					config.save();
				}
			
		}
		public static void setValueDisplay(boolean b){
			//ModLogger.logInfo("Setting config value displayClock to "+b);
			config.getCategory(CATEGORY_VALUES).get("displayClock").set(b);
			config.save();
		}
}
