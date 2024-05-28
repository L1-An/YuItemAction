package com.github.l1an.yuitemaction

import com.github.l1an.yuitemaction.utils.releaseResource
import org.serverct.parrot.parrotx.feature.sendInfo
import org.serverct.parrot.parrotx.update.GithubUpdateChecker
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.pluginVersion

@Suppress("unused")
object YuItemAction : Plugin() {

    val messagePrefix = "&f[ &3YuItemAction &f]"

    override fun onEnable() {
        sendInfo{
            +"$messagePrefix &aYuItemAction has been loaded! - $pluginVersion"
            +"$messagePrefix &bAuthor by L1An(Discord: &el1_an.&b)"
        }

        releaseResource()

        GithubUpdateChecker("l1-an", "YuItemAction").getVersion { version ->
            if (pluginVersion == version) {
                sendInfo("$messagePrefix &cThere is not a new update available.")
            } else {
                sendInfo {
                    +"$messagePrefix &aThere is a new update($version) available at:"
                    +"&ahttps://github.com/L1-An/YuItemAction/releases/latest"
                }
            }
        }
    }
}