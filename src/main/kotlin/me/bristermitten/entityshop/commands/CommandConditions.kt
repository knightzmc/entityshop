package me.bristermitten.entityshop.commands

import co.aikar.commands.ConditionFailedException
import co.aikar.commands.PaperCommandManager
import me.bristermitten.entityshop.lang.LangKey
import org.bukkit.Material
import org.bukkit.entity.Player

object CommandConditions {
    fun registerAllConditions(manager: PaperCommandManager) {
        manager.commandConditions.addCondition(Player::class.java, "item-in-hand") { _, _, player ->
            val itemInMainHand = player.inventory.itemInMainHand
            if (itemInMainHand == null || itemInMainHand.type == Material.AIR) {
                throw ConditionFailedException(LangKey.Error.NO_ITEM_IN_HAND)
            }
        }
    }
    const val ITEM_IN_HAND = "item-in-hand"
}
