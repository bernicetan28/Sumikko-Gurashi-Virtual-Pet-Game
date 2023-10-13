package ch.makery.address.model

object Coins {
  var userCoins: Int = 500

  def getCoins: Int = userCoins

  def addCoins(amount: Int): Unit = {
    userCoins += amount
  }

  def deductCoins(amount: Int): Boolean = {
    if (userCoins >= amount) {
      userCoins -= amount
      true
    } else {
      false
    }
  }
}
