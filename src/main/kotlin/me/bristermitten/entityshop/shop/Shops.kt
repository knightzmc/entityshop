package me.bristermitten.entityshop.shop

import org.bukkit.entity.Entity


class Shops(private val shops: MutableSet<Shop>) : Set<Shop> by shops {

    fun save(shop: Shop) {
        shops.add(shop)
    }

    fun remove(shop: Shop) {
        shops.remove(shop)
    }

    fun clear() {
        shops.clear()
    }

    fun byEntity(entity: ShopEntity): Shop? {
        return shops.firstOrNull {
            it.entity == entity
        }
    }

    fun byEntity(entity: Entity): Shop? {
        return shops.firstOrNull {
            it.entity.entity == entity
        }
    }

}
