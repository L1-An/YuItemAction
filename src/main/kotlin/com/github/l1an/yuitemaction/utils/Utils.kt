package com.github.l1an.yuitemaction.utils

import com.github.l1an.yuitemaction.holder.ActionPlan

/**
 * 从 match type 为 default 的 ActionPlan 中获取对应条件的 ActionPlan
 */
fun List<ActionPlan>.filterDefaultPlan(predicate: (ActionPlan) -> Boolean): ActionPlan? {
    return firstOrNull { it.matchType == "default" && predicate(it) }
}

/**
 * 从 match type 为 nbt 的 ActionPlan 中获取对应条件的 ActionPlan
 */
fun List<ActionPlan>.filterNbtPlan(predicate: (ActionPlan) -> Boolean): ActionPlan? {
    return firstOrNull { it.matchType == "nbt" && predicate(it) }
}
