package me.bristermitten.entityshop.price

import kotlinx.serialization.Serializable
import org.bukkit.entity.Player

typealias KTransient = kotlin.jvm.Transient

@Serializable
class MoneyPrice(
    override var permission: String? = null,
    private var amount: Double,

    @KTransient
    val moneyManager: MoneyManager
) : Price {

    override fun canAfford(player: Player): Boolean {
        return moneyManager.has(player, amount)
    }

    override fun remove(player: Player): Boolean {
        return moneyManager.withdraw(player, amount)
    }
}
