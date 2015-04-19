/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-19
 */
package xilef11.mc.realtimeclock.client.handler;

import net.minecraft.client.Minecraft;
import xilef11.mc.realtimeclock.client.gui.Clock;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

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
    	//we wnat the END phase
        if (event.phase.equals(Phase.START))return;
        if(Clock.doesRender(mcClient)){
        	Clock.draw(mcClient);
        }

    }

}
