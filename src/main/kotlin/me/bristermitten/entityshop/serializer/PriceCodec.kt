package me.bristermitten.entityshop.serializer

import com.sxtanna.korm.base.KormCodec
import com.sxtanna.korm.data.KormNull
import com.sxtanna.korm.data.KormType
import com.sxtanna.korm.reader.KormReader
import com.sxtanna.korm.writer.KormWriter
import me.bristermitten.entityshop.EntityShop
import me.bristermitten.entityshop.price.MoneyPrice
import me.bristermitten.entityshop.price.Price

object PriceCodec : KormCodec<Price> {

    override fun pull(reader: KormReader.ReaderContext, types: MutableList<KormType>): Price? {
        val type = types.byName("type") ?: return null

        return when (type.asBase()?.dataAsString() ?: return null) {
            "Money" -> {
                val amountType = types.byName("amount")
                val amount = amountType?.asBase()?.dataAsDoubleOr(0.0) ?: 0.0
                val perm = when (val permission = types.byName("permission")?.asBase()) {
                    null -> null
                    else -> if (permission.data is KormNull) null
                    else permission.dataAsString()
                }

                val price = MoneyPrice(
                    perm,
                    amount,
                    EntityShop.plugin.moneyManager
                )
                price
            }
            else -> null
        }
    }

    override fun push(writer: KormWriter.WriterContext, data: Price?) {

        if (data is MoneyPrice) {
            writer.writeHash(
                mapOf(
                    "type" to "Money",
                    "amount" to data.amount.toString(),
                    "permission" to (data.permission ?: KormNull)
                )
            )
        }
    }
}
