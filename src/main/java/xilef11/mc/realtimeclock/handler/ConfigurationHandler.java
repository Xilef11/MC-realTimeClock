/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.handler;

import java.nio.file.Path;
import java.util.regex.Pattern;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.config.ModConfig;
import xilef11.mc.realtimeclock.RealTimeClock;
import xilef11.mc.realtimeclock.client.gui.Clock;
import xilef11.mc.realtimeclock.references.Refs;

/**
 * @author Xilef11
 *
 */
public class ConfigurationHandler {
	
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec CLIENT_CONFIG;

	//Time as 24H
	public static ForgeConfigSpec.BooleanValue use24hours;
	//show AM/PM
	public static ForgeConfigSpec.BooleanValue showAMPM;
	//position de l'horloge
	public static ForgeConfigSpec.DoubleValue clockPosX,clockPosY;
	//scale
	public static ForgeConfigSpec.DoubleValue clockScale_cfg;
	public static double clockScale;
	//color
	public static ForgeConfigSpec.ConfigValue<String> color_cfg;
	public static ForgeConfigSpec.BooleanValue drawShadow;
	//show in which menus
	//public static boolean showPause;
	//is the clock displayed
	public static ForgeConfigSpec.BooleanValue display;
	public static int color;
	public static final String CATEGORY_VALUES="stored values";
	public static final String CATEGORY_GENERAL="general";

	
	
	static {
		CLIENT_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
		CLIENT_BUILDER.pop();
		CLIENT_BUILDER.comment("Saved Values").push(CATEGORY_VALUES);
		CLIENT_BUILDER.comment("This section is used to store values between reloads");
		CLIENT_BUILDER.pop();
		
		setupConfig();
		
		CLIENT_BUILDER.pop();
		
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	private static void setupConfig() {
		CLIENT_BUILDER.push(CATEGORY_GENERAL);
		//24 hours?
		use24hours=CLIENT_BUILDER.comment("Set to false to use a 12-hour clock").define("use24hours",true);
				//config.getBoolean("use24hours", Configuration.CATEGORY_GENERAL, true, );
		//am/pm
		showAMPM=CLIENT_BUILDER.comment("Set to true to show AM/PM").define("showAMPM", false);
				//config.getBoolean("showAMPM", Configuration.CATEGORY_GENERAL, false, "Set to true to show AM/PM");
		//position
		clockPosX=CLIENT_BUILDER.comment("Horizontal (X) position of the Clock HUD (as % of screen size)\nA too large value will be off screen")
				.defineInRange("posX", 0.5F, 0, 100);
				//config.getFloat("posX", Configuration.CATEGORY_GENERAL, 0.5F, 0, 100, "Horizontal (X) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
		clockPosY=CLIENT_BUILDER.comment("Vertical (Y) position of the Clock HUD (as % of screen size)\nA too large value will be off screen")
				.defineInRange("posY", 90F, 0, 100);
				//config.getFloat("posY", Configuration.CATEGORY_GENERAL, 90F, 0, 100, "Vertical (Y) position of the Clock HUD (as % of screen size)\nA too large value will be off screen");
		//size
		clockScale_cfg=CLIENT_BUILDER.comment("The Size of the clock (in % of the standard MC String size)")
				.defineInRange("clockScale", 120, 0, Float.MAX_VALUE);
				//config.getFloat("clockScale", Configuration.CATEGORY_GENERAL, 120, 0, Float.MAX_VALUE, "The Size of the clock (in % of the standard MC String size)");
		//TODO pattern?
		color_cfg = CLIENT_BUILDER.comment("Colour of the clock display, in Hexadecimal (from 0 to F for each of RRGGBB)")
				.define("color","AAAAAA");
		
		drawShadow=CLIENT_BUILDER.comment("Set to false to disable drawing the Shadow of the clock (recommended for dark colors)")
				.define("drawShadow", true);
				//config.getBoolean("drawShadow", Configuration.CATEGORY_GENERAL, true, "Set to false to disable drawing the Shadow of the clock (recommended for dark colors)");
		//is the clock displayed?
		//config.addCustomCategoryComment(CATEGORY_VALUES, "This section is used to store values between reloads");
		CLIENT_BUILDER.pop();
		CLIENT_BUILDER.push(CATEGORY_VALUES);
		display=CLIENT_BUILDER.comment("is the clock displayed?").define("displayClock", true);
				//config.getBoolean("displayClock", CATEGORY_VALUES, true, "is the clock displayed?");
	}
	
	 public static void loadConfig(ForgeConfigSpec spec, Path path) {

	        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
	                .sync()
	                .autosave()
	                .writingMode(WritingMode.REPLACE)
	                .build();

	        configData.load();
	        spec.setConfig(configData);
	    }
	
	private static void recalculate() {
		clockScale = clockScale_cfg.get()/100;
		//colour
		String col=color_cfg.get();
		try{
			color=Integer.parseInt(col, 16);
		}catch(NumberFormatException e){
			RealTimeClock.log.warn("Wrong Color String",e);
		}
		Clock.resetPosition();
	}
	
	@SubscribeEvent
	public void onConfigurationChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equals(Refs.MOD_ID)){
			recalculate();
		}
	}
	
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    	recalculate();
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {
    	recalculate();
    }
	
	private static Pattern hexCol= Pattern.compile("[0-9[A-F]]{6}?");
	
	
	
	public static void setValueDisplay(boolean b){
		//ModLogger.logInfo("Setting config value displayClock to "+b);
		//config.getCategory(CATEGORY_VALUES).get("displayClock").set(b);
		//config.save();
	}
}
