import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.entity.SimpleEntityMock
import me.bristermitten.entityshop.EntityShop
import me.bristermitten.entityshop.price.MoneyPrice
import me.bristermitten.entityshop.shop.Shop
import me.bristermitten.entityshop.shop.ShopEntity
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.Test
import java.util.*

class MainTests {
    @Test
    fun testLoad() {
        val mock = MockBukkit.mock()

        val world = mock.addSimpleWorld("world")
        val entity = SimpleEntityMock(mock)
        mock.registerEntity(entity);

        val shop = Shop(
            MoneyPrice(null, 30.0, MockMoneyManager),
            MoneyPrice(null, 15.0, MockMoneyManager),
            entity.location,
            ItemStack(Material.STONE),
            ShopEntity(entity),
            UUID.randomUUID()
        )
        val plugin = MockBukkit.load(EntityShop::class.java)
//        plugin.moneyManager = MockMoneyManager
//        val message = plugin.korm.push(shop)
//        println(message)
//        println(plugin.korm.pull(message, Shop::class))
//
//        plugin.shops.save(shop)
//
//        println(shop)
//        println(Shops.INSTANCE)

        val item = ItemStack(Material.STONE, 3)
        val itemKorm = plugin.korm.push(item)
        println(itemKorm)
    }
}
