package me.bristermitten.entityshop.price

import net.milkbowl.vault.economy.Economy
import org.bukkit.entity.Player

class VaultMoneyManager(private val economy: Economy) : MoneyManager {

    override fun has(player: Player, amount: Double) = economy.has(player, amount)

    override fun withdraw(player: Player, amount: Double) = economy.withdrawPlayer(player, amount).transactionSuccess()
}
