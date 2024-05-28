package com.github.l1an.yuitemaction.kether

import com.github.l1an.yuitemaction.utils.itemEvent
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import taboolib.module.kether.KetherParser
import taboolib.module.kether.combinationParser

/**
 * event cancel
 */
@KetherParser(["event"], namespace = "yuitemaction")
private fun actionEvent() = combinationParser {
    it.group(
        text()
    ).apply(it) { type ->
        now {
            when (type) {
                "cancel" -> {
                    val e = itemEvent<Event>()
                    if (e is Cancellable) {
                        e.isCancelled = true
                    } else error("event is not cancellable")
                }

                else -> error("unknown event action $type")
            }
        }
    }
}