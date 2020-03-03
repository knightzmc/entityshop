package me.bristermitten.entityshop.shop

import me.bristermitten.entityshop.price.Price
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


data class Shop(
    val buyPrice: Price,
    val sellPrice: Price,
    var location: Location,
    val item: ItemStack,

    val entity: ShopEntity,
    val owner: UUID
) {

    fun canBuy(player: Player): Boolean {
        return buyPrice.permission?.let { player.hasPermission(it) } ?: true
    }

    fun canSell(player: Player): Boolean {
        return sellPrice.permission?.let { player.hasPermission(it) } ?: true
    }

    fun remove() {
        entity.remove()
    }

    fun spawn(location: Location) {
        entity.spawn(location)
        //TODO hologram and stuff
    }
}
