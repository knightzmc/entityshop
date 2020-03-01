import me.bristermitten.entityshop.price.MoneyManager
import org.bukkit.entity.Player

object MockMoneyManager : MoneyManager {
    val money: MutableMap<Player, Double> = HashMap()

    override fun has(player: Player, amount: Double): Boolean {
        if (player !in money) {
            money[player] = 0.0
        }
        return money[player]!! >= amount
    }

    override fun withdraw(player: Player, amount: Double): Boolean {
        if (player !in money) {
            money[player] = 0.0
        }
        val money = money[player]!!
        if (money < amount) return false
        this.money[player] = money - amount
        return true
    }
}
