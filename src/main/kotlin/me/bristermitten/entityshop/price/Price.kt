package me.bristermitten.entityshop.price

import org.bukkit.entity.Player

interface Price {
    var permission: String?
    fun canAfford(player: Player): Boolean

    fun remove(player: Player): Boolean

}
