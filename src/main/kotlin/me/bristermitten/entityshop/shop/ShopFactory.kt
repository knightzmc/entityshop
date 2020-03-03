package me.bristermitten.entityshop.shop

import me.bristermitten.entityshop.EntityShop
import me.bristermitten.entityshop.lang.color
import me.bristermitten.entityshop.lang.prettifyName
import me.bristermitten.entityshop.price.MoneyPrice
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShopFactory(private val entityShop: EntityShop) {

    fun createMoneyBasedShop(owner: Player, buyPrice: Double, sellPrice: Double, item: ItemStack): Shop {
        val entity = owner.world.spawnEntity(owner.location, EntityType.ARMOR_STAND) as ArmorStand
        entity.customName = "&6&l${item.type.prettifyName()}".color()
        entity.isCustomNameVisible = true
        entity.isInvulnerable = true
        entity.isVisible = false
        entity.equipment.helmet = item
        entity.isSmall = true

        return Shop(
            buyPrice = MoneyPrice(null, buyPrice, entityShop.moneyManager),
            sellPrice = MoneyPrice(null, sellPrice, entityShop.moneyManager),
            location = owner.location,
            item = item,
            entity = ShopEntity(entity),
            owner = owner.uniqueId
        )
    }
}
