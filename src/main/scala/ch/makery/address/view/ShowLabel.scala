package ch.makery.address.view
import scalafx.application.Platform
import scalafx.scene.control.Label
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

abstract class ShowLabel(private var text: Label) {
  def showText(str: String): Unit = {
    Platform.runLater(() => {
      text.setText(str)

      // Schedule a task to update the text
      val updateTask = Future {
        Thread.sleep(5000)
        Platform.runLater(() => text.setText(""))
      }
    })
  }

}