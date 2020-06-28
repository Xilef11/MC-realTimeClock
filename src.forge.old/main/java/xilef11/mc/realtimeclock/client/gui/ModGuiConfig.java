/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-17
 */
package xilef11.mc.realtimeclock.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import xilef11.mc.realtimeclock.references.Refs;

/**
 * @author Xilef11
 *
 */
public class ModGuiConfig extends Screen {

	public ModGuiConfig(Screen screen) {
		//TODO 1.14
		super(new TextComponent() {
			
			@Override
			public ITextComponent shallowCopy() {
				try {
					return (ITextComponent)this.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			@Override
			public String getUnformattedComponentText() {
				return Refs.MOD_NAME;
			}
		});
		/*super(screen, new ConfigElement(
				ConfigurationHandler.config
				.getCategory(Configuration.CATEGORY_GENERAL))
		.getChildElements(), Refs.MOD_ID, false, false,
		GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
		*/
	}

}
