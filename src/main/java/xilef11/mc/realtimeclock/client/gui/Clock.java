/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.gui;

import static xilef11.mc.realtimeclock.handler.ConfigurationHandler.clockScale;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import xilef11.mc.realtimeclock.handler.ConfigurationHandler;
import xilef11.mc.realtimeclock.utilities.RenderingPosHelper;

/**
 * @author Xilef11
 *
 */
public class Clock {

	private static boolean enabled=true;

	public static void toggleEnabled(){
		enabled=!enabled;
		if(!enabled){
			resetPosition();//force recalculate on reactivation.
			//time=null;//reset calendar in case locale gets switched or something
		}
	}
	/** Calling this causes the clock's position (in pixels) 
	 * to be recalculated on the next frame it is rendered
	 */
	public static void resetPosition(){
		xPos=yPos=-1;
	}
	public static boolean isEnabled(){return enabled;}

	/** Do we want to show the clock
	 * @return true to show the clock
	 */
	public static boolean doesRender(Minecraft mc) {
		//when to render
		//render only if currently playing
		if (mc.world ==null ) return false;
		//if(ConfigurationHandler.showPause && mc.currentScreen instanceof net.minecraft.client.gui.GuiIngameMenu)return true;
		if(!enabled)return false;
		//not if gui hidden with F1
		if(mc.gameSettings.hideGUI) return false;
		return true;
	}
	//debugging
	//private static int numTicks=0;
	//position of the clock in pixels
	private static int xPos = -1,
					   yPos = -1;
	private static boolean wasFullScreen=false;
	/**Draw the Time
	 * 
	 */
	public static void draw(Minecraft mc) {
		//get the correct position and scale only if something changed
		//detect the switch between windowed -> fullscreen
		boolean fullscreen = Display.isFullscreen();
		if((wasFullScreen && !fullscreen) || (!wasFullScreen && fullscreen)){
			wasFullScreen=fullscreen;
			resetPosition();
		}
		if(xPos<0||yPos<0 || Display.wasResized()){
			xPos=RenderingPosHelper.getXPosByScreenSize(mc, ConfigurationHandler.clockPosX);
			yPos=RenderingPosHelper.getYPosByScreenSize(mc, ConfigurationHandler.clockPosY);
		}
		//draw the time
		GL11.glPushMatrix();
		GL11.glScalef(clockScale, clockScale, 1);
		//fix for the weirdness in the achievements GUI
		boolean lightingState=GL11.glIsEnabled(GL11.GL_LIGHTING);//get the current state
		if(lightingState)GL11.glDisable(GL11.GL_LIGHTING);//we need this off
		//actually draw the time
		mc.fontRenderer.drawString(getTimeString(mc), Math.round(xPos/clockScale), Math.round(yPos/clockScale), ConfigurationHandler.color,ConfigurationHandler.drawShadow);
		if(lightingState)GL11.glEnable(GL11.GL_LIGHTING);//if it was on, turn it back on.
		GL11.glPopMatrix();
	}
	/** returns a String with the current time according to config options
	 * @Param mc the instance of Minecraft, required to get the localization
	 * @return the current time as a String
	 */
	private static String getTimeString(Minecraft mc){
		LocalTime time = LocalTime.now();
		//get the hour depending on the time format to use
		int hour;
		if(ConfigurationHandler.use24hours){
			hour=time.get(ChronoField.HOUR_OF_DAY);
		}else{
			hour=time.get(ChronoField.CLOCK_HOUR_OF_AMPM);
		}
		int minute = time.get(ChronoField.MINUTE_OF_HOUR);
		//make sure the minutes have 2 digits
		String minuteS= minute<10? "0"+minute : String.valueOf(minute);
		return hour+" : "+minuteS + 
				(ConfigurationHandler.showAMPM ? (time.get(ChronoField.AMPM_OF_DAY)==0? " AM" : " PM") :"");
	}

}
