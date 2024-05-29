package com.github.l1an.yuitemaction

import com.github.l1an.yuitemaction.utils.releaseResource
import org.serverct.parrot.parrotx.function.sendInfo
import org.serverct.parrot.parrotx.update.GithubUpdateChecker
import org.serverct.parrot.parrotx.update.UpdateManager
import taboolib.common.platform.Platform
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.pluginVersion
import taboolib.module.metrics.Metrics

@Suppress("unused")
object YuItemAction : Plugin() {

    val messagePrefix = "&f[ &3YuItemAction &f]"

    override fun onEnable() {
        Metrics(22060, pluginVersion, Platform.BUKKIT)

        sendInfo{
            +"$messagePrefix &aYuItemAction has been loaded! - $pluginVersion"
            +"$messagePrefix &bAuthor by L1An(Discord: &el1_an.&b)"
        }

        releaseResource()
    }

    override fun onActive() {
        if (UpdateManager.checkUpdate) {
            GithubUpdateChecker("L1-An", "YuItemAction").check()
        }
    }
}