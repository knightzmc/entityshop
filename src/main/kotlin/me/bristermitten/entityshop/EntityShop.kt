package me.bristermitten.entityshop

import co.aikar.commands.PaperCommandManager
import com.sxtanna.korm.Korm
import com.sxtanna.korm.data.option.Options
import com.sxtanna.korm.data.option.Options.SERIALIZE_NULLS
import com.sxtanna.korm.writer.KormWriter
import me.bristermitten.entityshop.commands.CommandConditions
import me.bristermitten.entityshop.commands.EntityShopCommand
import me.bristermitten.entityshop.price.MoneyManager
import me.bristermitten.entityshop.price.Price
import me.bristermitten.entityshop.price.VaultMoneyManager
import me.bristermitten.entityshop.serializer.PriceCodec
import me.bristermitten.entityshop.shop.Shop
import me.bristermitten.entityshop.shop.ShopEntity
import me.bristermitten.entityshop.shop.ShopFactory
import me.bristermitten.entityshop.shop.Shops
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File
import java.util.logging.Level


class EntityShop : JavaPlugin {


    private lateinit var shops: Shops

    lateinit var moneyManager: MoneyManager

    private lateinit var shopFactory: ShopFactory

    lateinit var korm: Korm
    private lateinit var shopsFile: File

    constructor() : super()
    constructor(loader: JavaPluginLoader?, description: PluginDescriptionFile?, dataFolder: File?, file: File?) : super(
        loader,
        description,
        dataFolder,
        file
    )

    override fun onEnable() {
        plugin = this

        val economy = setupEconomy()
        if (economy == null) {
//            logger.severe("Vault not found! EntityShop shutting down...")
//            server.pluginManager.disablePlugin(this)
//            return
        } else
            moneyManager = VaultMoneyManager(economy)
        shopFactory = ShopFactory(this)

        loadShops()
        loadCommands()
    }

    private fun loadShops() {
        korm = Korm(writer = KormWriter(2, Options.min(SERIALIZE_NULLS)))
        korm.codecBy<World, String>(Bukkit::getWorld) { it?.name }
        korm.codecBy(Bukkit::getEntity) { it?.uniqueId }
//        korm.codecBy(ItemStack::class, ItemStackSerializer)
        korm.codecBy(ItemStack::deserialize) { it?.serialize() }
        korm.codecBy(::ShopEntity) { it?.entity }
        korm.codecBy(Price::class, PriceCodec)

        shopsFile = dataFolder.resolve("shops.korm")

        var shopSet: MutableSet<Shop> = HashSet()

        if (shopsFile.exists()) {
            try {
                val text = shopsFile.readText()
                shopSet = korm.pull(text).toList<Shop>().toMutableSet()
            } catch (e: IllegalStateException) {
                logger.log(Level.WARNING, "Could not deserialize shops", e)
            }
        } else {
            shopsFile.parentFile.mkdirs()
            shopsFile.createNewFile()
        }

        shops = Shops(shopSet)
        val text = korm.push(shops)
        shopsFile.writeText(text)
    }

//    private fun createSerializationModule(): SerialModule {
//        return SerializersModule {
//            polymorphic(Price::class) {
//                MoneyPrice::class with MoneyPrice.serializer()
//            }
//        }
//    }

    private fun setupEconomy(): Economy? {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return null
        }
        val rsp =
            server.servicesManager.getRegistration(Economy::class.java) ?: return null
        return rsp.provider
    }


    private fun loadCommands() {
        val manager = PaperCommandManager(this)

        @Suppress("DEPRECATION")
        manager.enableUnstableAPI("help")

        CommandConditions.registerAllConditions(manager)

        manager.registerCommand(EntityShopCommand(shops, shopFactory))
    }

    override fun onDisable() {
        if (!shopsFile.exists()) {
            shopsFile.parentFile.mkdirs()
            shopsFile.createNewFile()
        }

        val text = korm.push(shops)
        shopsFile.writeText(text)
    }

    companion object {
        lateinit var plugin: EntityShop
    }
}
