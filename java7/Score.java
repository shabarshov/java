import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Score extends JLabel {
  private String value;

  Score() {
    this.value = Integer.toString(Main.leftPlayerScore) + " / " + Integer.toString(Main.rightPlayerScore);
    this.setText(Integer.toString(Main.leftPlayerScore) + " / " + Integer.toString(Main.rightPlayerScore));
    this.setFont(new Font("Verdana",0,20));
    this.setSize(value.length() * 20, 20);
    this.setForeground(Color.WHITE);
    System.out.println(this.getWidth());
  }

  public int getValueLength() {
    return this.value.length();
  }
}
