package com.jfinal;

import java.util.List;

import com.jfinal.config.Plugins;
import com.jfinal.log.LogManager;
import com.jfinal.plugin.IPlugin;

/**
 * 
 */

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月30日下午5:26:43
 */
public class JfinalPlugin {
	//AutowiredAnnotationBeanPostProcessor
	
	public static void startPlugins(Plugins plugins) {
		List<IPlugin> pluginList = plugins.getPluginList();
		if (pluginList == null) {
			return ;
		}
		for (IPlugin plugin : pluginList) {
			try {
				if (plugin instanceof com.jfinal.plugin.activerecord.ActiveRecordPlugin) {
					com.jfinal.plugin.activerecord.ActiveRecordPlugin arp = (com.jfinal.plugin.activerecord.ActiveRecordPlugin)plugin;
					if (arp.getDevMode() == null) {
						
					}
				}
				if (plugin.start() == false) {
					String message = "Plugin start error: " + plugin.getClass().getName();
					throw new RuntimeException(message);
				}
			}
			catch (Exception e) {
				String message = "Plugin start error: " + plugin.getClass().getName() + ". \n" + e.getMessage();
				throw new RuntimeException(message, e);
			}
		}
	}
	
	public static void initLogFactory() {
		LogManager.me().init();
	}
}
