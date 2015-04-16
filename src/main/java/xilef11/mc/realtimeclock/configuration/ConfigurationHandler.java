/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-16
 */
package xilef11.mc.realtimeclock.configuration;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

/**
 * @author Xilef11
 *
 */
public class ConfigurationHandler {

		public static void init(File configFile){
			
			Configuration config = new Configuration(configFile);
			
			try{
				//load the config file
				config.load();
				//read properties
				boolean configValue = config.get(Configuration.CATEGORY_GENERAL,"configValue",true,"example config value").getBoolean();
			}catch(Exception e){
				//log exception
			}finally{
				//save the config file
				config.save();
			}
		}
}
