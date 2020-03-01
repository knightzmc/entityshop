package me.bristermitten.entityshop.shop

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable
import me.bristermitten.entityshop.price.Price
import me.bristermitten.entityshop.serializer.ItemStackSerializer
import me.bristermitten.entityshop.serializer.LocationSerializer
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


@Serializable
data class Shop(
    val buyPrice: Price,
    val sellPrice: Price,

    @Serializable(with = LocationSerializer::class)
    var location: Location,

    @Serializable(with = ItemStackSerializer::class)
    val item: ItemStack,

    val entity: ShopEntity,

    @ContextualSerialization
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
