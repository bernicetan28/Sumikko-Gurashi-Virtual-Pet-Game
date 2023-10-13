package ch.makery.address.view
import ch.makery.address.MainGame
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController{
  def saveGame(): Unit = {
    MainGame.saveGame()
  }

  def resetGame(): Unit = {
    MainGame.resetGame()
  }
}