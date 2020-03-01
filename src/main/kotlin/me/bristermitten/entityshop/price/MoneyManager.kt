package me.bristermitten.entityshop.price

import org.bukkit.entity.Player

interface MoneyManager {
    fun has(player: Player, amount: Double): Boolean

    fun withdraw(player: Player, amount: Double): Boolean
}
