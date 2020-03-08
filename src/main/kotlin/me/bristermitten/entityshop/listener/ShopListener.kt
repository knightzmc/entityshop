package me.bristermitten.entityshop.listener

import me.bristermitten.entityshop.shop.Shops
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class ShopListener(val shops: Shops) : Listener {

    @EventHandler
    fun PlayerInteractAtEntityEvent.handle() {
        val shop = shops.byEntity(rightClicked) ?: return
        player.sendMessage("Clicked $shop")
    }
}
