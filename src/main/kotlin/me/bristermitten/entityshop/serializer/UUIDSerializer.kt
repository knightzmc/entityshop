package me.bristermitten.entityshop.serializer

//import kotlinx.serialization.*
//import kotlinx.serialization.internal.StringDescriptor
//import org.bukkit.inventory.ItemStack
//import java.util.*
//
//@Serializer(forClass = ItemStack::class)
//object UUIDSerializer : KSerializer<UUID> {
//    override val descriptor: SerialDescriptor =
//        StringDescriptor.withName("UUID")
//
//    override fun deserialize(decoder: Decoder): UUID {
//        return UUID.fromString(decoder.decodeString())
//    }
//
//    override fun serialize(encoder: Encoder, obj: UUID) {
//        encoder.encodeString(obj.toString())
//    }
//}
