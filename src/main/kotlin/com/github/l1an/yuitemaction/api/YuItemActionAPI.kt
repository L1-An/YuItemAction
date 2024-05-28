package com.github.l1an.yuitemaction.api

import com.github.l1an.yuitemaction.holder.ActionPlan
import com.github.l1an.yuitemaction.manager.ConfigManager.TAG
import com.github.l1an.yuitemaction.manager.PlanManager
import com.github.l1an.yuitemaction.utils.filterDefaultPlan
import com.github.l1an.yuitemaction.utils.filterNbtPlan
import org.bukkit.inventory.ItemStack
import org.serverct.parrot.parrotx.function.name
import taboolib.module.chat.uncolored
import taboolib.module.nms.getItemTag

object YuItemActionAPI {

    /**
     * 根据物品获取对应的plan
     */
    fun getPlan(item: ItemStack): ActionPlan? {
        val tag = item.getItemTag()[TAG]?.asString()
        val name = item.name?.uncolored()
        val lore = item.itemMeta?.lore?.uncolored()

        val planFromTag = PlanManager.plans.filterNbtPlan{ it.matchTag == tag }

        val planFromDefault = PlanManager.plans.filterDefaultPlan { plan ->
            // 若匹配lore为空，则只匹配name
            if (plan.matchLore.isEmpty()) {
                name?.let { plan.matchName?.contains(it) } == true
            } else {
                // 若匹配lore不为空，则同时匹配name与lore
                if (lore != null) {
                    val isLoreMatch = plan.matchLore.any { str ->
                        lore.any { lore ->
                            lore.contains(str)
                        }
                    }
                    name?.let { plan.matchName?.contains(it) } == true && isLoreMatch
                } else {
                    false // 不满足条件，false，则获取到null
                }
            }
        }

        return planFromTag ?: planFromDefault
    }

}