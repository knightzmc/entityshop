import me.bristermitten.entityshop.price.MoneyManager
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

object MockMoneyManager : MoneyManager, Economy {
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

    override fun getBanks(): MutableList<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBalance(playerName: String?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBalance(player: OfflinePlayer?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBalance(playerName: String?, world: String?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun has(playerName: String?, amount: Double): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteBank(name: String?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBank(name: String?, player: String?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasAccount(playerName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasAccount(player: OfflinePlayer?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPlayerAccount(playerName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currencyNameSingular(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun currencyNamePlural(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEnabled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fractionalDigits(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bankBalance(name: String?): EconomyResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun format(amount: Double): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasBankSupport(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
