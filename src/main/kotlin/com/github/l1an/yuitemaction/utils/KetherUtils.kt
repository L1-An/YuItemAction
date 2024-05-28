package com.github.l1an.yuitemaction.utils

import org.bukkit.event.Event
import taboolib.module.kether.ScriptFrame

fun <T: Event> ScriptFrame.itemEvent(): T {
    return variables().get<T>("@ItemEvent").orElse(null) ?: error("No event selected.")
}