package me.bristermitten.entityshop.lang

import co.aikar.locales.MessageKey
import co.aikar.locales.MessageKeyProvider

open class LangKey(private val parent: LangKeys, private val key: String) : MessageKeyProvider {

    override fun getMessageKey(): MessageKey {
        return MessageKey.of(parent.prefix + "." + key)
    }

    object Error : LangKeys("error") {
        val NO_ITEM_IN_HAND = key("no-item-in-hand")
    }
}
