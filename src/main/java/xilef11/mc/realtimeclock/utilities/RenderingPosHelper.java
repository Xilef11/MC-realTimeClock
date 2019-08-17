/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-20
 */
package xilef11.mc.realtimeclock.utilities;

import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;

/** Various methods to help with rendering things in the correct position
 * @author Xilef11
 *
 */
public class RenderingPosHelper {
	private RenderingPosHelper(){}

	/**returns the current scaled game resolution
	 * 
	 * @param mc the instance of Minecraft
	 * @return a new ScaledResolution based on the given instance
	 */
//	private static ScaledResolution getScaledRes(Minecraft mc){
//		return new ScaledResolution(mc);
//	}
	/**get the vertical position based on screen size
	 * 
	 */
	public static int getYPosByScreenSize(Minecraft mc, double percentage){
		//int height = getScaledRes(mc).getScaledHeight();
		int height = Display.getHeight();
		float decimalPercentage=(float)percentage/100;
		float yPos=height*decimalPercentage;
		return Math.round(yPos);
	}
	/**get theHorizontal position based on screen size
	 * 
	 */
	public static int getXPosByScreenSize(Minecraft mc, double percentage){
		//int width = getScaledRes(mc).getScaledWidth();
		int width = Display.getWidth();
		float decimalPercentage=(float)percentage/100;
		float xPos=width*decimalPercentage;
		return Math.round(xPos);
	}
}
