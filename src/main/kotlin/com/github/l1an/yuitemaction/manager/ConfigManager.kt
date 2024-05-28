package com.github.l1an.yuitemaction.manager

import org.serverct.parrot.parrotx.mechanism.Reloadable
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

object ConfigManager {

    @Config("config.yml", autoReload = true)
    @Reloadable
    lateinit var config: Configuration
        private set

    @ConfigNode("settings.tag", "config.yml")
    val TAG = "@plan-name"

}