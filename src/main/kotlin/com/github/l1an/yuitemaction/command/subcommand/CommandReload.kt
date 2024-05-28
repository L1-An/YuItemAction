package com.github.l1an.yuitemaction.command.subcommand

import com.github.l1an.yuitemaction.manager.PlanManager.plans
import org.bukkit.command.CommandSender
import org.serverct.parrot.parrotx.lang.LanguageType
import org.serverct.parrot.parrotx.lang.sendLang
import org.serverct.parrot.parrotx.mechanism.Reloadables
import taboolib.common.platform.command.subCommandExec

val CommandReload = subCommandExec<CommandSender> {
    Reloadables.execute()
    sender.sendLang("command-reload", type = LanguageType.Done)
    sender.sendLang("plan-count", plans.size to "count")
}