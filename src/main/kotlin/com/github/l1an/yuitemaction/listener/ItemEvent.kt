package com.github.l1an.yuitemaction.listener

import com.github.l1an.yuitemaction.api.YuItemActionAPI.getPlan
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.serverct.parrot.parrotx.feature.Debug.debug
import org.serverct.parrot.parrotx.function.isNull
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

@Suppress("unused")
object ItemEvent {

    @SubscribeEvent
    fun onClick(e: PlayerInteractEvent) {
        if (e.item.isNull) return
        val plan = getPlan(e.item!!) ?: return

        when(e.action) {
            Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK -> {
                plan.invokeScript(listOf("on-left-click", "on-left", "onLeftClick", "onLeft"), e.player, e)
                e.player.debug("left click")
            }
            Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK -> {
                plan.invokeScript(listOf("on-right-click", "on-right", "onRightClick", "onRight"), e.player, e)
                e.player.debug("right click")
            }
            else -> {}
        }
    }

    @SubscribeEvent(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBreak(e: BlockBreakEvent) {
        if (e.player.inventory.itemInMainHand.isNull) return
        val plan = getPlan(e.player.inventory.itemInMainHand) ?: return
        plan.invokeScript(listOf("on-break", "onBreak"), e.player, e)
        e.player.debug("break block")
    }

    @SubscribeEvent(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onSwap(e: PlayerSwapHandItemsEvent) {
        if (!e.offHandItem.isNull) {
            val plan = getPlan(e.offHandItem!!) ?: return
            plan.invokeScript(listOf("on-swap-to-offhand", "onSwapToOffhand"), e.player, e)
            plan.invokeScript(listOf("on-swap", "onSwap","onSwapHand"), e.player, e)
            e.player.debug("offhand isn't null, swap to offhand")
        }
        if (!e.mainHandItem.isNull) {
            val plan = getPlan(e.mainHandItem!!) ?: return
            plan.invokeScript(listOf("on-swap-to-mainhand", "onSwapToMainhand"), e.player, e)
            plan.invokeScript(listOf("on-swap", "onSwap","onSwapHand"), e.player, e)
            e.player.debug("mainhand isn't null, swap to mainhand")
        }
    }

    @SubscribeEvent(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onDrop(e: PlayerDropItemEvent) {
        if (!e.itemDrop.itemStack.isNull) {
            val plan = getPlan(e.itemDrop.itemStack) ?: return
            plan.invokeScript(listOf("on-drop", "onDrop"), e.player, e)
            e.player.debug("drop item")
        }
    }

    @SubscribeEvent(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPickup(e: EntityPickupItemEvent) {
        val player = e.entity as? Player ?: return
        if (!e.item.itemStack.isNull) {
            val plan = getPlan(e.item.itemStack) ?: return
            plan.invokeScript(listOf("on-pick", "on-pickup", "onPick", "onPickup"), player, e)
            player.debug("pickup item")
        }
    }

}