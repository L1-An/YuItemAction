package com.github.l1an.yuitemaction.manager

import com.github.l1an.yuitemaction.YuItemAction.messagePrefix
import com.github.l1an.yuitemaction.holder.ActionPlan
import org.serverct.parrot.parrotx.function.sendInfo
import org.serverct.parrot.parrotx.mechanism.Reloadable
import org.serverct.parrot.parrotx.util.files
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.configuration.Configuration

object PlanManager {

    val plans = mutableListOf<ActionPlan>()

    @Awake(LifeCycle.ENABLE)
    @Reloadable
    fun loadPlans() {
        plans.clear()
        files("plan") {
            val config = Configuration.loadFromFile(it)
            config.getKeys(false).forEach { key ->
                val section = config.getConfigurationSection(key)!!
                plans += ActionPlan(section)
            }
        }
        sendInfo("$messagePrefix &aHas been loaded &e${plans.size}&a plans.")
    }

}