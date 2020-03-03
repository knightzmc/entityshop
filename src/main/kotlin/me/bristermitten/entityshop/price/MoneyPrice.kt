package me.bristermitten.entityshop.price

import org.bukkit.entity.Player

data class MoneyPrice(
    override var permission: String? = null,
    var amount: Double,

    @Transient
    val moneyManager: MoneyManager
) : Price {

    override fun canAfford(player: Player): Boolean {
        return moneyManager.has(player, amount)
    }

    override fun remove(player: Player): Boolean {
        return moneyManager.withdraw(player, amount)
    }
}
