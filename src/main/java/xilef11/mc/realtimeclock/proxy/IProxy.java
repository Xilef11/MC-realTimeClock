/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Xilef11
 *
 */
public interface IProxy {

	public abstract void registerKeyBindings();
	public abstract void initConfig(FMLPreInitializationEvent event);
}
