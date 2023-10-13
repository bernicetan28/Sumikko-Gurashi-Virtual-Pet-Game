package ch.makery.address.model

class Pet(var hungerStatus: Int, var happinessStatus: Int, var cleanlinessStatus: Int) {
  def feedFood(food: Food): Boolean = {

    if (Coins.getCoins >= food.cost) {

      hungerStatus = Math.max(0, Math.min(100, hungerStatus + food.hungerIncrease))
      happinessStatus = Math.max(0, Math.min(100, happinessStatus + food.happinessIncrease))
      cleanlinessStatus = Math.max(0, Math.min(100, cleanlinessStatus + food.cleanlinessDecrease))
      true
    } else {
      false
    }
  }

  def cleanPet(): Unit = {
    cleanlinessStatus = Math.min(100, cleanlinessStatus + 5)

  }
}

object Pet {
  var hungerStatus: Int = 50
  var happinessStatus: Int = 50
  var cleanlinessStatus: Int = 50

  private var instance: Pet = new Pet(hungerStatus, happinessStatus, cleanlinessStatus)

  def getInstance: Pet = instance
}
