/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-17
 */
package xilef11.mc.realtimeclock.client.gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;

/**
 * @author Xilef11
 *
 */
public class GuiFactory implements IModGuiFactory {

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.IModGuiFactory#initialize(net.minecraft.client.Minecraft)
	 */
	@Override
	public void initialize(Minecraft minecraftInstance) {
		//might not be used
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.IModGuiFactory#mainConfigGuiClass()
	 */
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ModGuiConfig.class;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.IModGuiFactory#runtimeGuiCategories()
	 */
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		//Might not be used
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.client.IModGuiFactory#getHandlerFor(cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement)
	 */
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(
			RuntimeOptionCategoryElement element) {
		//might not be used
		// TODO Auto-generated method stub
		return null;
	}

}
