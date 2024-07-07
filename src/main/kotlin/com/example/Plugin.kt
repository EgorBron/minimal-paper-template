package com.example

import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Hello, Paper world!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("Shutting down $name")
        super.onDisable()
    }
}