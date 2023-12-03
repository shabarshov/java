import javax.swing.*;

public class TopPanel extends JPanel implements Runnable {
  private volatile boolean isThreadRunning = true;
  private RestartButton restartButton;
  private Score score;

  TopPanel(RestartButton restartButton, Score score) {
    this.score = score;
    this.restartButton = restartButton;

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    this.setSize(Main.GAME_WIDTH, Main.BUTTON_HEIGHT + 20);

    this.add(restartButton);
    this.add(score);

    this.setBorder(BorderFactory.createEmptyBorder((this.getHeight() - restartButton.getHeight() - score.getHeight())/2, Main.GAME_WIDTH / 2 - score.getValueLength() * 10, 0, 0));
    new Thread(this).start();
  }

  @Override
  public void run() {
    while(isThreadRunning) {
      score.setText(Integer.toString(Main.leftPlayerScore) + " / " + Integer.toString(Main.rightPlayerScore));
      score.setBorder(BorderFactory.createEmptyBorder(0, (this.restartButton.getWidth() - this.score.getValueLength() * 10) / 2, 0, 0));
      score.repaint();
    }
  }

  public void closeThread() {
    this.isThreadRunning = false;
  }
}
