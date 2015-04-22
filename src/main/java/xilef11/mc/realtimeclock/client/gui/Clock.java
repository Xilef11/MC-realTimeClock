/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.gui;

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
		// get the time
		Calendar time = Calendar.getInstance(TimeZone.getDefault(), Locale.forLanguageTag(mc.gameSettings.language));
		//get the hour depending on the time format to use
		int hour;
		if(ConfigurationHandler.use24hours){
			hour=time.get(time.HOUR_OF_DAY);
		}else{
			hour=time.get(time.HOUR);
		}
		int minute = time.get(time.MINUTE);
		//make sure the minutes have 2 digits
		String minuteS= minute<10? "0"+minute : String.valueOf(minute);
		int xPos=RenderingPosHelper.getXPosByScreenSize(mc, ConfigurationHandler.clockPosX);
		int yPos=RenderingPosHelper.getYPosByScreenSize(mc, ConfigurationHandler.clockPosY);
		//numTicks++;
		//if(numTicks>40){ModLogger.logInfo("Rendering clock at "+xPos+","+yPos);numTicks=0;}
		//ModLogger.logInfo(time.HOUR_OF_DAY+" : "+time.MINUTE);
		float scale=ConfigurationHandler.clockScale/100;
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, 1);
		mc.fontRenderer.drawString(hour+" : "+minuteS, Math.round(xPos/scale), Math.round(yPos/scale), ConfigurationHandler.color,ConfigurationHandler.drawShadow);
		GL11.glPopMatrix();
	}
	
}
