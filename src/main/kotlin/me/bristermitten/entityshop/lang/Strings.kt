package me.bristermitten.entityshop.lang

import org.bukkit.ChatColor

fun String.color(): String = ChatColor.translateAlternateColorCodes('&', this)

fun Enum<*>.prettifyName() = name.toLowerCase().split("_").joinToString(" ") {
    it.capitalize().replace('_', ' ')
}
