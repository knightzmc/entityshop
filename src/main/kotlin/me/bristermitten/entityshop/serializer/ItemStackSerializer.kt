package me.bristermitten.entityshop.serializer

import kotlinx.serialization.Serializer
import org.bukkit.inventory.ItemStack

@Serializer(forClass = ItemStack::class)
object ItemStackSerializer {
}
