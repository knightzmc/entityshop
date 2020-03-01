package me.bristermitten.entityshop

import co.aikar.commands.PaperCommandManager
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule
import me.bristermitten.entityshop.commands.CommandConditions
import me.bristermitten.entityshop.commands.EntityShopCommand
import me.bristermitten.entityshop.price.MoneyPrice
import me.bristermitten.entityshop.price.Price
import me.bristermitten.entityshop.price.VaultMoneyManager
import me.bristermitten.entityshop.shop.ShopFactory
import me.bristermitten.entityshop.shop.Shops
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File


class EntityShop : JavaPlugin {


    lateinit var economy: Economy
        private set

    lateinit var shops: Shops
        private set

    lateinit var vaultMoneyManager: VaultMoneyManager
        private set

    lateinit var shopFactory: ShopFactory
        private set

    private lateinit var json: Json
    private lateinit var shopsFile: File

    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File?) : super(
        loader,
        description,
        dataFolder,
        file
    )

    constructor() : super()

    override fun onEnable() {
        entityShop = this

        if (!setupEconomy()) {
            logger.severe("Vault not found! EntityShop shutting down...")
            server.pluginManager.disablePlugin(this)
            return
        }

        vaultMoneyManager = VaultMoneyManager(economy)
        shopFactory = ShopFactory(this)


        loadShops()
        loadCommands()
    }

    private fun loadShops() {
        json = loadSerialization()

        shopsFile = dataFolder.resolve("shops.json")

        if (shopsFile.exists()) {
            shops = json.parse(Shops.serializer(), shopsFile.readText())
        } else {
            shopsFile.parentFile.mkdirs()
            shopsFile.createNewFile()
            shops = Shops()
            val text = json.stringify(Shops.serializer(), shops)
            shopsFile.writeText(text)
        }
    }

    private fun loadSerialization(): Json {
        return Json(JsonConfiguration.Stable.copy(prettyPrint = true), context = createSerializationModule())
    }

    private fun createSerializationModule(): SerialModule {
        return SerializersModule {
            polymorphic(Price::class) {
                MoneyPrice::class with MoneyPrice.serializer()
            }
        }
    }

    private fun setupEconomy(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }
        val rsp =
            server.servicesManager.getRegistration(Economy::class.java) ?: return false
        economy = rsp.provider
        return true
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

        shopsFile.writeText(json.stringify(Shops.serializer(), shops))
    }

    companion object {
        lateinit var entityShop: EntityShop
    }
}
