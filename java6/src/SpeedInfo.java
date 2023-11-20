import javax.swing.*;
import javax.swing.border.Border;

public class SpeedInfo extends JLabel {
  private Integer speed = 0;
  private Integer maxSpeed = speed;
  private Border margin = BorderFactory.createEmptyBorder(10, 21, 10, 21);

  SpeedInfo() {
    this.setText("Your speed (spm): " + Integer.toString(this.speed) + " | " + "Max speed: " + Integer.toString(maxSpeed));
    this.setBorder(margin);
  }

  public Integer GetSpeed() {
    return this.speed;
  }

  public void SetSpeed(Integer value) {
    this.speed = value;
    this.SetMaxSpeed(Math.max(this.speed, this.maxSpeed));
    this.setText("Your speed (spm): " + Integer.toString(this.speed) + " | " + "Max speed: " + Integer.toString(maxSpeed));
  }

  private void SetMaxSpeed(Integer value) {
    this.maxSpeed = value;
  }
}
