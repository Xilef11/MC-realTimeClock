/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-05-30
 */
package xilef11.mc.realtimeclock.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import xilef11.mc.realtimeclock.client.gui.Clock;
import xilef11.mc.realtimeclock.utilities.ModLogger;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Xilef11
 *
 */
public class RGOEventHandler {
	private Minecraft      mcClient;

    public RGOEventHandler()
    {
        mcClient = FMLClientHandler.instance().getClient();
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event)
    {
    	if(event.type!= ElementType.ALL)return;
        if(Clock.doesRender(mcClient)){
        	Clock.draw(mcClient);
        }
    }
}
