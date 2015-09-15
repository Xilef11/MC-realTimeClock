/**
 *  the Real-Time Clock mod adds the system time to the Minecraft HUD.
 *  Copyright (C) 2015  Xilef11
 *  Licensed under the GNU General Public License version 3
 *
 *  File created by Xilef11 on 2015-04-17
 */
package xilef11.mc.realtimeclock.utilities;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import xilef11.mc.realtimeclock.references.Refs;

/**Helper class for outputting to the Minecraft console log
 * @author Xilef11
 *
 */
public class ModLogger {
	public static void log(Level level, Object obj){
		FMLLog.log(Refs.MOD_ID, level, String.valueOf(obj));
	}
	public static void logAll(Object obj){
		log(Level.ALL, obj);
	}

	public static void logFatal(Object obj){
		log(Level.FATAL, obj);
	}

	public static void logError(Object obj){
		log(Level.ERROR, obj);
	}

	public static void logDebug(Object obj){
		log(Level.DEBUG, obj);
	}

	public static void logInfo(Object obj){
		log(Level.INFO, obj);
	}

	public static void logTrace(Object obj){
		log(Level.TRACE, obj);
	}

	public static void logWarn(Object obj){
		log(Level.WARN,obj);
	}

	public static void logException(Level level, Throwable ex, Object message){
		FMLLog.log(Refs.MOD_ID, level, ex, String.valueOf(message));
	}

	public static void logBigWarn(Object message){
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		logWarn("****************************************");
		logWarn(message);
		for (int i = 2; i < 8 && i < trace.length; i++)
		{
			logWarn("*  at "+ trace[i].toString()+( i == 7 ? "..." : ""));
		}
		log(Level.WARN, "****************************************");
	}
}
