/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.gui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import xilef11.mc.realtimeclock.utilities.ModLogger;
import net.minecraft.client.Minecraft;

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
		// TODO when to render
		if(!enabled)return false;
		if(mc.currentScreen instanceof net.minecraft.client.gui.GuiMainMenu)return false;
		return true;
	}

	/**Draw the Time
	 * 
	 */
	public static void draw(Minecraft mc) {
		// get the time
		//ModLogger.logInfo("language String: "+mc.gameSettings.language);
		//Calendar time = Calendar.getInstance(Locale.forLanguageTag(mc.gameSettings.language));
		//ModLogger.logInfo("TimeZone: "+TimeZone.getDefault());
		Calendar time = Calendar.getInstance(TimeZone.getDefault(), Locale.forLanguageTag(mc.gameSettings.language));
		//ModLogger.logInfo(time.HOUR_OF_DAY+" : "+time.MINUTE);
		mc.fontRenderer.drawString(time.get(time.HOUR_OF_DAY)+" : "+time.get(time.MINUTE), 0, 0, 0);
	}
	
}
