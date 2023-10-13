package ch.makery.address
import ch.makery.address.model.{Coins, Pet}
import ch.makery.address.view.PetScreen
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}

import java.io.IOException
object MainGame extends JFXApp{

  val rootResource = getClass.getResource("view/RootLayout.fxml")

  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)

  // Load root layout from fxml file.
  loader.load();

  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  // initialize stage
  stage = new PrimaryStage() {
    title = "Sumikko Gurashi Virtual Pet"
    minWidth = 700
    minHeight = 500

    scene = new Scene() {
      stylesheets += getClass.getResource("view/PetScreen.css").toString
      root = roots

    }
    icons += new Image(getClass.getResourceAsStream("/images/Penguin.png"))
  }

  stage.setOnCloseRequest(event => endGame())

  //Save game function
  def saveGame(): Unit = {
    val filename: String = "saveFile.json"
    val filePath = os.pwd / filename

    try {
      val petInstance = Pet.getInstance
      val data = ujson.Obj(
        "hunger" -> petInstance.hungerStatus,
        "cleanliness" -> petInstance.cleanlinessStatus,
        "happiness" -> petInstance.happinessStatus,
        "coins" -> Coins.userCoins
      )

      os.write.over(filePath, data)

      println("File saved successfully")
    } catch {
      case e: IOException =>
        println("Error saving file")
        e.printStackTrace()
    }
  }

  def endGame(): Unit = {
    saveGame()
    System.exit(0)
  }

  def resetGame(): Unit = {
    val petInstance = Pet.getInstance

    // Reset pet's status
    petInstance.hungerStatus = 50
    petInstance.cleanlinessStatus = 50
    petInstance.happinessStatus = 50

    // Reset coins
    Coins.userCoins = 500

    // Show the pet after resetting
    showPetScreen()
  }

  def showPetScreen(): Unit = {
    val resource = getClass.getResource("view/PetScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showStartScreen(): Unit = {
    val resource = getClass.getResource("view/StartScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]()
    this.roots.setCenter(roots)

  }

  def showMinigame(): Unit = {
    val resource = getClass.getResource("view/MinigameScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]()
    this.roots.setCenter(roots)

  }

  def showBathroomScreen(): Unit = {
    val resource = getClass.getResource("view/BathroomScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]()
    this.roots.setCenter(roots)
  }

  showStartScreen()
}

