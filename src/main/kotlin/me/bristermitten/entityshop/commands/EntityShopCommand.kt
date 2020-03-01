package me.bristermitten.entityshop.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import me.bristermitten.entityshop.lang.color
import me.bristermitten.entityshop.price.MoneyPrice
import me.bristermitten.entityshop.shop.Shop
import me.bristermitten.entityshop.shop.ShopEntity
import me.bristermitten.entityshop.shop.ShopFactory
import me.bristermitten.entityshop.shop.Shops
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
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

        val entity = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand

        entity.customName = "&6&lShop".color()
        entity.isCustomNameVisible = true
        entity.isInvulnerable = true
        entity.isVisible = false

        val shop = shopFactory.createMoneyBasedShop(player, buyPrice, sellPrice, player.inventory.itemInMainHand)
        shops.save(shop)

        player.sendMessage("&aEntityShop created!".color())
    }
}

