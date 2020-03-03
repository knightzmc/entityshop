package me.bristermitten.entityshop.shop


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
}
