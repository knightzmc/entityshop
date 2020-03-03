package me.bristermitten.entityshop.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import me.bristermitten.entityshop.lang.color
import me.bristermitten.entityshop.shop.ShopFactory
import me.bristermitten.entityshop.shop.Shops
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.entity.Player

@CommandAlias("entityshop|eshop|shop")
class EntityShopCommand(
    private val shops: Shops,
    private val shopFactory: ShopFactory
) : BaseCommand() {

    @Default
    @HelpCommand
    fun defaultOrHelp() {
        @Suppress("DEPRECATION")
        showCommandHelp()
    }

    @Subcommand("list")
    @CommandPermission("entityshop.list")
    fun listShops() {
        currentCommandIssuer.sendMessage("Shops")
        shops.forEach {

            currentCommandIssuer.sendMessage(it.toString())
        }
    }

    @Subcommand("create")
    @CommandPermission("entityshop.create")
    fun create(
        @Conditions(CommandConditions.ITEM_IN_HAND) player: Player,
        buyPrice: Double,
        sellPrice: Double
    ) {

        val shop = shopFactory.createMoneyBasedShop(player, buyPrice, sellPrice, player.inventory.itemInMainHand)
        shops.save(shop)

        player.sendMessage("&aEntityShop created!".color())
    }

    @Subcommand("clear")
    @CommandPermission("entityshop.clear")
    fun clear() {
        shops.forEach {
            it.remove()
        }
        shops.clear()
        currentCommandIssuer.sendMessage("&aShops Cleared!".color())
    }
}

