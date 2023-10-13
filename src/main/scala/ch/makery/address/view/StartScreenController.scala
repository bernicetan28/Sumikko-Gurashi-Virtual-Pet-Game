package ch.makery.address.view
import ch.makery.address.model.{Coins, Pet}
import scalafxml.core.macros.sfxml
import ch.makery.address.MainGame
import scalafx.scene.control.Label

import java.io.FileNotFoundException

@sfxml
class StartScreenController(startMsg: Label) extends ShowLabel(startMsg) {
  def startGame(): Unit = {
    // Check for saved file
    val filename: String = "saveFile.json"
    try {
      val petInstance = Pet.getInstance
      val tempRead = os.read(os.pwd / filename)
      val analyze = ujson.read(tempRead)

      petInstance.happinessStatus = analyze("happiness").num.toInt
      petInstance.hungerStatus = analyze("hunger").num.toInt
      petInstance.cleanlinessStatus = analyze("cleanliness").num.toInt
      Coins.userCoins = analyze("coins").num.toInt
      MainGame.showPetScreen()
    } catch {
      case _: FileNotFoundException => showText("Save File Not Found")
    }
  }

  def exitGame(): Unit = {
    MainGame.endGame()
  }
}
