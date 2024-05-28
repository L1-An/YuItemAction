package com.github.l1an.yuitemaction.holder

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.serverct.parrot.parrotx.feature.Debug.debug
import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.util.getMap
import taboolib.module.kether.KetherShell.eval
import taboolib.module.kether.ScriptOptions

class ActionPlan(val root: ConfigurationSection) {

    val id = root.name
    val matchType = root.getString("match", "default")!!
    val matchName = root.getString("name")
    val matchLore = root.getStringList("lore")
    val matchTag = root.getString("tag")
    val actionMap = root.getMap<String, String>("action").toMutableMap()

    fun invokeScript(key: List<String>, player: Player?, event: Event, namespace: String = "yuitemaction-internal") {
        val script = actionMap.entries.firstOrNull { it.key in key }?.value ?: return
        val options = ScriptOptions.new {
            if (player != null) {
                sender(player)
            }
            namespace(listOf("yuitemaction", namespace))
            set("@ItemEvent", event)
            sandbox()
            detailError()
        }
        eval(script, options)
        player?.debug("invoke event action: &e${key[0]}")
    }

}