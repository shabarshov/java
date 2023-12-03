import javax.swing.*;
import java.awt.*;

public class RestartButton extends JButton {
  RestartButton() {
    super("Restart");

    this.setPreferredSize(new Dimension(Main.GAME_WIDTH, Main.BUTTON_HEIGHT));
  }
}
