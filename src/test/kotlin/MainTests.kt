import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.entity.SimpleEntityMock
import me.bristermitten.entityshop.EntityShop
import me.bristermitten.entityshop.price.MoneyPrice
import me.bristermitten.entityshop.shop.Shop
import me.bristermitten.entityshop.shop.ShopEntity
import me.bristermitten.entityshop.shop.Shops
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.Test

class MainTests {
    @Test
    fun testLoad() {
        val mock = MockBukkit.mock()

        val world = mock.addSimpleWorld("world")
        val entity = SimpleEntityMock(mock)
        mock.registerEntity(entity);

//        val shop = Shop(
//            MoneyPrice(null, 30.0, MockMoneyManager),
//            MoneyPrice(null, 15.0, MockMoneyManager),
//            entity.location,
//            ItemStack(Material.STONE),
//            ShopEntity(entity)
//        )
//        val plugin = MockBukkit.load(EntityShop::class.java)
//
//        plugin.shops.save(shop)
//
//        println(shop)
//        println(Shops.INSTANCE)
    }
}
