/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.handler;

import java.io.File;
import java.util.regex.Pattern;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import xilef11.mc.realtimeclock.RealTimeClock;
import xilef11.mc.realtimeclock.client.gui.Clock;
import xilef11.mc.realtimeclock.references.Refs;

/**
 * @author Xilef11
 *
 */
public class ConfigurationHandler {

	public static Configuration config;

	//Time as 24H
	public static boolean use24hours=true;
	//show AM/PM
	public static boolean showAMPM;
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
		if(event.getModID().equals(Refs.MOD_ID)){
			//resync configs
			loadConfiguration();
			Clock.resetPosition();//force recalculate the position
		}
	}
	private static Pattern hexCol= Pattern.compile("[0-9[A-F]]{6}?");
	private static void loadConfiguration(){
		//read properties
		//24 hours?
		use24hours=config.getBoolean("use24hours", Configuration.CATEGORY_GENERAL, true, "Set to false to use a 12-hour clock");
		//am/pm
		showAMPM=config.getBoolean("showAMPM", Configuration.CATEGORY_GENERAL, false, "Set to true to show AM/PM");
		//position
		clockPosX=config.getFloat("posX", Configuration.CATEGORY_GENERAL, 0.5F, 0, 100, "Horizontal (X) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
		clockPosY=config.getFloat("posY", Configuration.CATEGORY_GENERAL, 90F, 0, 100, "Vertical (Y) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
		//size
		clockScale=config.getFloat("clockScale", Configuration.CATEGORY_GENERAL, 120, 0, Float.MAX_VALUE, "The Size of the clock (in % of the standard MC String size)");
		clockScale/=100;
		//colour
		String col=config.getString("color", Configuration.CATEGORY_GENERAL, "AAAAAA", "Colour of the clock display, in Hexadecimal (from 0 to F for each of RRGGBB)", hexCol);
		try{
			color=Integer.parseInt(col, 16);
		}catch(NumberFormatException e){
			RealTimeClock.log().warn("Wrong Color String",e);
		}
		drawShadow=config.getBoolean("drawShadow", Configuration.CATEGORY_GENERAL, true, "Set to false to disable drawing the Shadow of the clock (recommended for dark colors)");
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
