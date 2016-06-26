/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import xilef11.mc.realtimeclock.client.gui.Clock;

/**
 * @author Xilef11
 *
 */
public class RenderTickHandler {
	private Minecraft      mcClient;

	public RenderTickHandler()
	{
		mcClient = FMLClientHandler.instance().getClient();
	}

	@SubscribeEvent
	public void onTick(RenderTickEvent event)
	{
		//we want the END phase
		if (!event.phase.equals(Phase.END))return;
		if(Clock.doesRender(mcClient)){
			Clock.draw(mcClient);
		}

	}

}
