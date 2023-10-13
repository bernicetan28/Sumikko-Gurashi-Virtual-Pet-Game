package ch.makery.address.view

import ch.makery.address.MainGame
import ch.makery.address.model.{Cheese, Coins, Cookie, Egg, Food, Pet, Pizza}
import scalafx.event.ActionEvent
import javafx.scene.Node
import javafx.stage.Screen
import scalafx.animation.{KeyFrame, Timeline}
import scalafx.scene.control.{Label, ProgressBar}
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml
import scalafx.util.Duration

@sfxml
class PetScreen(private val backgroundImg: ImageView,
                private val hungerProgress: ProgressBar,
                private val happinessProgress: ProgressBar,
                private val cleanlinessProgress: ProgressBar,
                private val coinsNum: Label,
                private val hungerNum: Label,
                private val happinessNum: Label,
                private val cleanlinessNum: Label,
                message: Label)
  extends ShowLabel(message) {

  // Constants
  private val DecreaseValue = 5
  private val StatusUpdateInterval = Duration(11000)

  // Get the Pet instance
  private val pet: Pet = Pet.getInstance
  updateLabel()

  // Set background image
  private val backgroundImage = new Image("/images/BackgroundPic2.jpg")
  backgroundImg.image = backgroundImage
  backgroundImg.preserveRatio = true
  backgroundImg.fitWidth = Screen.getPrimary.getVisualBounds.getWidth
  backgroundImg.fitHeight = Screen.getPrimary.getVisualBounds.getHeight
  backgroundImg.toBack()

  // Update the status labels
  private def updateLabel(): Unit = {
    coinsNum.text = Coins.getCoins.toString + " "
    hungerNum.text = pet.hungerStatus + " %"
    happinessNum.text = pet.happinessStatus + " %"
    cleanlinessNum.text = pet.cleanlinessStatus + " %"

    hungerProgress.progress = pet.hungerStatus.toDouble / 100.0
    cleanlinessProgress.progress = pet.cleanlinessStatus.toDouble / 100.0
    happinessProgress.progress = pet.happinessStatus.toDouble / 100.0
  }


  // Buy food
  def detectFood(action: ActionEvent): Unit = {
    val source: Node = action.getSource.asInstanceOf[Node]
    val foodie = source.getId
    foodie match {
      case "cookieFood" => updateFoodStatus(Cookie)
      case "cheeseFood" => updateFoodStatus(Cheese)
      case "pizzaFood" => updateFoodStatus(Pizza)
      case "eggFood" => updateFoodStatus(Egg)
    }
  }

  // Update feeding status
  private def updateFoodStatus(food: Food): Unit = {
    val foodCost = food.cost
    if (Coins.deductCoins(foodCost)) {
      pet.feedFood(food)
      showText("Your pet has been fed! :)")
    } else {
      showText("You do not have enough Coins :(")
    }
    updateLabel()
  }

  // Timeline to update pet status every 11 seconds
  private val statusUpdateTimeline = new Timeline {
    cycleCount = Timeline.Indefinite
    keyFrames = Seq(
      KeyFrame(StatusUpdateInterval, onFinished = { _ =>
        pet.hungerStatus = Math.max(0, Math.min(100, (pet.hungerStatus - DecreaseValue)))
        pet.happinessStatus = Math.max(0, Math.min(100, (pet.happinessStatus - DecreaseValue)))
        pet.cleanlinessStatus = Math.max(0, Math.min(100, (pet.cleanlinessStatus - DecreaseValue)))
        updateLabel()
      })
    )
  }
  statusUpdateTimeline.play()

  def startStartScreen(): Unit = {
    MainGame.showStartScreen()
  }

  def startMinigame(): Unit = {
    MainGame.showMinigame()
  }

  def startBathroomScreen(): Unit = {
    MainGame.showBathroomScreen()
  }
}
