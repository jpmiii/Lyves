package com.jpmiii.Lyves;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Lyves extends JavaPlugin {
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void onEnable() {
		this.saveDefaultConfig();
		
		this.getCustomConfig();
		
	}
	public void onDisable() {
		this.saveCustomConfig();
		getLogger().info("onDisable has been invoked!");
	}
	public FileConfiguration getCustomConfig() {
	    if (customConfig == null) {
	        reloadCustomConfig();
	    }
	    return customConfig;
	}
	
	public void saveCustomConfig() {
	    if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getCustomConfig().save(customConfigFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	
	public void reloadCustomConfig() {
	    if (customConfigFile == null) {
	    customConfigFile = new File(getDataFolder(), "save.yml");
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("save.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig);
	    }
	}
	public void saveDefaultConfig() {
	    if (customConfigFile == null) {
	        customConfigFile = new File(getDataFolder(), "save.yml");
	    }
	    if (!customConfigFile.exists()) {            
	         this.saveResource("save.yml", false);
	     }
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("lyves")) {
			// doSomething

			if (!(sender instanceof Player)) {
				this.reloadConfig();
				
				getLogger().info("config reloaded");
				return true;
			}

			Player player = (Player) sender;
			if (args.length > 0) {
				return true;
			}
			return true;
		}return true;
	}

}
