package ch.makery.address.model

abstract class Food(val name: String, val cost: Int, val hungerIncrease: Int, val happinessIncrease: Int, val cleanlinessDecrease: Int) {
  def feedPet(pet: Pet): Unit
}
