package me.bristermitten.entityshop.shop

import kotlinx.serialization.*
import kotlinx.serialization.internal.HexConverter
import kotlinx.serialization.internal.StringDescriptor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import java.util.*

@Serializable
class ShopEntity(private var entity: Entity) {

    fun spawn(location: Location) {
        if (entity.isDead || !entity.isValid) {
            entity = entity.world.spawnEntity(location, entity.type)
        } else teleport(location)
    }

    fun teleport(location: Location) {
        entity.teleport(location)
    }

    fun remove() {
        entity.remove()
    }

    @Serializer(forClass = ShopEntity::class)
    companion object : KSerializer<ShopEntity> {
        override val descriptor: SerialDescriptor =
            StringDescriptor.withName("ShopEntity")

        override fun serialize(encoder: Encoder, obj: ShopEntity) {
            encoder.encodeString(obj.entity.uniqueId.toString())
        }

        override fun deserialize(decoder: Decoder): ShopEntity {
            return ShopEntity(Bukkit.getEntity(UUID.fromString(decoder.decodeString())))
        }
    }


}
