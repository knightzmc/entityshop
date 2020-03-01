package me.bristermitten.entityshop.shop

import kotlinx.serialization.Serializable

@Serializable
class Shops(private val shops: MutableSet<Shop> = HashSet()) : Set<Shop> by shops {

    fun save(shop: Shop) {
        shops.add(shop)
    }

    fun remove(shop: Shop) {
        shops.remove(shop)
    }
}
