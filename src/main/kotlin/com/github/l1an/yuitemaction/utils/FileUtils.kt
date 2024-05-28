package com.github.l1an.yuitemaction.utils

import taboolib.common.io.newFile
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.releaseResourceFile

fun releaseResource() {
    val file = newFile(getDataFolder(), "plan/default.yml", create = false)
    if (!file.exists()) {
        releaseResourceFile("plan/default.yml")
    }
}