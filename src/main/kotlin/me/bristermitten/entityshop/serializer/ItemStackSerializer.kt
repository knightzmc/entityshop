package me.bristermitten.entityshop.serializer

import com.sxtanna.korm.base.KormCodec
import com.sxtanna.korm.data.KormType
import com.sxtanna.korm.reader.KormReader
import com.sxtanna.korm.writer.KormWriter
import org.bukkit.inventory.ItemStack


object ItemStackSerializer : KormCodec<ItemStack> {
    const val TYPE = "type"
    const val AMOUNT = "amount"
    const val DATA = "data"


    override fun pull(reader: KormReader.ReaderContext, types: MutableList<KormType>): ItemStack? {
        //TODO do it
        return null
    }

    override fun push(writer: KormWriter.WriterContext, data: ItemStack?) {
        if (data == null) return
        writer.writeHash(
            mapOf(
                TYPE to data.type,
                AMOUNT to data.amount,
                DATA to data.durability
            )
        )
    }

}
