package me.bristermitten.entityshop.serializer

import com.sxtanna.korm.base.KormCodec
import com.sxtanna.korm.data.KormType
import com.sxtanna.korm.reader.KormReader
import com.sxtanna.korm.writer.KormWriter
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


object ItemStackSerializer : KormCodec<ItemStack> {
    private const val TYPE = "type"
    private const val AMOUNT = "amount"
    private const val DATA = "data"


    override fun pull(reader: KormReader.ReaderContext, types: MutableList<KormType>): ItemStack? {

        val typeType = types.byName(TYPE)?.asBase()?.dataAsString() ?: return null
        val material = Material.matchMaterial(typeType) ?: return null

        val amount = types.byName(AMOUNT)?.asBase()?.dataAsIntOr(1) ?: 1

        val data = (types.byName(DATA)?.asBase()?.dataAsIntOr(0) ?: 0).toShort()

        return ItemStack(material, amount, data)
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
