/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.gui;

import static xilef11.mc.realtimeclock.handler.ConfigurationHandler.clockScale;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import xilef11.mc.realtimeclock.handler.ConfigurationHandler;
import xilef11.mc.realtimeclock.utilities.RenderingPosHelper;

/**
 * @author Xilef11
 *
 */
public class Clock {
	private static boolean enabled=true;

	public static void toggleEnabled(){enabled=!enabled;}

	public static boolean isEnabled(){return enabled;}

	/** Do we want to show the clock
	 * @return true to show the clock
	 */
	public static boolean doesRender(Minecraft mc) {
		//when to render
		//render only if currently playing
		if (mc.theWorld ==null ) return false;
		//if(ConfigurationHandler.showPause && mc.currentScreen instanceof net.minecraft.client.gui.GuiIngameMenu)return true;
		if(!enabled)return false;
		return true;
	}
	//debugging
	//private static int numTicks=0;
	/**Draw the Time
	 * 
	 */
	public static void draw(Minecraft mc) {
		//get the correct position and scale TODO this should only be done when the config changes or the window is resized
		int xPos=RenderingPosHelper.getXPosByScreenSize(mc, ConfigurationHandler.clockPosX);
		int yPos=RenderingPosHelper.getYPosByScreenSize(mc, ConfigurationHandler.clockPosY);
		//draw the time
		GL11.glPushMatrix();
		GL11.glScalef(clockScale, clockScale, 1);
		//fix for the weirdness in the achievements GUI
		boolean lightingState=GL11.glIsEnabled(GL11.GL_LIGHTING);//get the current state
		if(lightingState)GL11.glDisable(GL11.GL_LIGHTING);//we need this off
		//actually draw the time
		mc.fontRendererObj.drawString(getTimeString(mc), Math.round(xPos/clockScale), Math.round(yPos/clockScale), ConfigurationHandler.color,ConfigurationHandler.drawShadow);
		if(lightingState)GL11.glEnable(GL11.GL_LIGHTING);//if it was on, turn it back on.
		GL11.glPopMatrix();
	}
	/** returns a String with the current time according to config options
	 * @Param mc the instance of Minecraft, required to get the localization
	 * @return the current time as a String
	 */
	private static String getTimeString(Minecraft mc){
		// get the time TODO we should move away from Calendar eventually
		Calendar time = Calendar.getInstance(TimeZone.getDefault(), Locale.forLanguageTag(mc.gameSettings.language));
		//get the hour depending on the time format to use
		int hour;
		if(ConfigurationHandler.use24hours){
			hour=time.get(Calendar.HOUR_OF_DAY);
		}else{
			hour=time.get(Calendar.HOUR);
		}
		int minute = time.get(Calendar.MINUTE);
		//make sure the minutes have 2 digits
		String minuteS= minute<10? "0"+minute : String.valueOf(minute);
		return hour+" : "+minuteS;
	}
	/** should the string shadow be rendered? depends on the config and current GUI
	 * 
	 * @param mc
	 * @return
	 */
	private static boolean drawShadow(Minecraft mc){
		//don't draw the shadow if the config says we shouldn't
		if(!ConfigurationHandler.drawShadow) return false;
		//don't draw the shadow in menus to avoid bugs (i.e achievements gui...)
		//if(mc.currentScreen != null) return false;
		return true;
	}
}
