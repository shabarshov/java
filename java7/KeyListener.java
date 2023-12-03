import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
  private LeftPaddle leftPaddle;
  private RightPaddle rightPaddle;

  KeyListener(LeftPaddle leftPaddle, RightPaddle rightPaddle) {
    this.leftPaddle = leftPaddle;
    this.rightPaddle = rightPaddle;
  }

  public void keyPressed(KeyEvent event) {
    leftPaddle.keyPressed(event);
    rightPaddle.keyPressed(event);
  }

  public void keyReleased(KeyEvent event) {
    leftPaddle.keyReleased(event);
    rightPaddle.keyReleased(event);
  }
}
