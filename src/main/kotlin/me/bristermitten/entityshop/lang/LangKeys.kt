package me.bristermitten.entityshop.lang

open class LangKeys(internal val prefix: String)

fun LangKeys.key(key: String) = LangKey(this, key)

