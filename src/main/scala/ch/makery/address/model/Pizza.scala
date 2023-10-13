package ch.makery.address.model

object Pizza extends Food("Pizza", 150, 20, 20, -20) {
  override def feedPet(pet: Pet): Unit = {
  }
}