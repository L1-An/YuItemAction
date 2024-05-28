package com.github.l1an.yuitemaction.command

import com.github.l1an.yuitemaction.command.subcommand.CommandReload
import org.serverct.parrot.parrotx.feature.Debug.CommandDebug
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.mainCommand
import taboolib.expansion.createHelper

@CommandHeader(
    name = "yuitemaction",
    aliases = ["yia", "ia", "itemaction", "itema"],
    permission = "yuitemaction.command.use",
permissionDefault = PermissionDefault.TRUE
)
object MainCommand {

    @CommandBody
    val main = mainCommand { createHelper() }

    @CommandBody(permission = "yuitemaction.command.reload", permissionDefault = PermissionDefault.OP)
    val reload = CommandReload

    @CommandBody(permission = "yuitemaction.command.debug", permissionDefault = PermissionDefault.OP)
    val debug = CommandDebug

}