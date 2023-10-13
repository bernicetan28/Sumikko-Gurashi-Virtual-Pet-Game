package ch.makery.address.view

import ch.makery.address.MainGame
import ch.makery.address.model.Coins
import javafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import javafx.stage.Screen
import scalafx.animation.{KeyFrame, Timeline}
import scalafx.scene.control.Label
import scalafxml.core.macros.sfxml
import scalafx.util.Duration

import scala.util.Random

@sfxml
class Minigame(private val grassBgImg: ImageView,
               private val rat1Img: ImageView,
               private val rat2Img: ImageView,
               private val rat3Img: ImageView,
               private val rat4Img: ImageView,
               private val rat5Img: ImageView,
               private val rat6Img: ImageView,
               private val rat7Img: ImageView,
               private val rat8Img: ImageView,
               private val rat9Img: ImageView,
               private val coinsNum: Label,
               private val initialCoins: Int) {

  // Load grass image and set its dimensions
  private val grassImage = new Image("/images/GrassBg.png")
  grassBgImg.image = grassImage

  private val screenBounds = Screen.getPrimary.getVisualBounds
  private val screenWidth = screenBounds.getWidth
  private val screenHeight = screenBounds.getHeight
  grassBgImg.fitWidth = screenWidth
  grassBgImg.fitHeight = screenHeight
  grassBgImg.toBack()

  // Get Coins
  coinsNum.text = Coins.getCoins.toString

  // Hide all rats initially
  private val rats = Seq(rat1Img, rat2Img, rat3Img, rat4Img, rat5Img, rat6Img, rat7Img, rat8Img, rat9Img)
  rats.foreach(_.visible = false)

  private val randomDuration = () => {
    val randomSeconds = Random.nextInt(6) + 2
    Duration(randomSeconds.toDouble * 1000)
  }

  // Create a custom class that extends Timeline to make a random rat visible
  private class AppearTimeline extends Timeline {
    keyFrames = Seq(
      KeyFrame(randomDuration(), onFinished = (e: ActionEvent) => {
        val randomIndex = Random.nextInt(rats.length) // Generate a random index between 0 and 9
        rats.foreach(_.visible = false)
        rats(randomIndex).visible = true
        playFrom(randomDuration())
      })
    )
    cycleCount = Timeline.Indefinite
  }

  // Start the timeline to make rats appear randomly
  private val appearTimeline = new AppearTimeline
  appearTimeline.play()

  def handleRatClick {
    rats.foreach(_.visible = false)
    Coins.addCoins(50)
    coinsNum.text = Coins.getCoins.toString
  }

  def startGame(): Unit = {
    MainGame.showPetScreen()
  }

}
