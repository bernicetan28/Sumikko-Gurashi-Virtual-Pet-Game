package ch.makery.address.view

import ch.makery.address.MainGame
import ch.makery.address.model.Pet
import javafx.stage.Screen
import scalafx.scene.control.{Label, ProgressBar}
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml

@sfxml
class Bathroom(private val bathroomImg: ImageView,
               private val cleanlinessProgress: ProgressBar,
               private val cleanlinessNum: Label) {
  // Get the Pet instance
  private val myPet: Pet = Pet.getInstance

  // Load bathroom image and set its dimensions
  private val bathroomImage: Image = new Image("/images/Bathroom.png")
  private val screenBounds = Screen.getPrimary.getVisualBounds
  private val screenWidth = screenBounds.getWidth
  private val screenHeight = screenBounds.getHeight

  bathroomImg.image = bathroomImage
  bathroomImg.fitWidth = screenWidth
  bathroomImg.fitHeight = screenHeight
  bathroomImg.toBack()

  // Initialize cleanliness display and progress
  private def initializeCleanliness(): Unit = {
    val initialCleanliness = myPet.cleanlinessStatus
    cleanlinessNum.text = s"$initialCleanliness %"
    cleanlinessProgress.progress = initialCleanliness.toDouble / 100.0
  }

  initializeCleanliness()

  // Clean the pet and update display
  def cleanPet(): Unit = {
    myPet.cleanPet()
    val updatedCleanliness = myPet.cleanlinessStatus
    cleanlinessNum.text = s"$updatedCleanliness %"
    cleanlinessProgress.progress = updatedCleanliness.toDouble / 100.0
  }

  // Show pet screen
  def startGame(): Unit = {
    MainGame.showPetScreen()
  }
}