import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
  public static final int GAME_WIDTH = 1200; // Ширина игрового поля
  public static final int GAME_HEIGHT = 700; // Высота игрового поля
  public static final int BALL_SIZE = 30; // Размер шарика
  public static final int BUTTON_HEIGHT = 50; // Высота кнопки
  public static final int PADDLE_WIDTH = 20; // Ширина ракетки
  public static final int PADDLE_HEIGHT = 100; // Высота ракетки

  public static final int MARGIN_BOTTOM = 100;

  public static final int INITIAL_BALL_X = (GAME_WIDTH - BALL_SIZE) / 2;
  public static final int INITIAL_BALL_Y = (GAME_HEIGHT - BALL_SIZE) / 2 - MARGIN_BOTTOM;

  public static int leftPlayerScore = 0;
  public static int rightPlayerScore = 0;

  public static void repaintTopPanel(TopPanel topPanel) {
    topPanel.repaint();
  }

  // final - нельзя изменить переменную. Попытка изменить значение приведёт к ошибкe

  public static void main(String[] args) {
    // Запускаем приложение в потоке обработки событий Swing
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
      // Создаем главное окно
      JFrame frame = new JFrame("PingPong");
      frame.setSize(GAME_WIDTH, GAME_HEIGHT); // Устанавливаем размер окна
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Устанавливаем операцию закрытия окна
      frame.setResizable(false);

      LeftPaddle leftPaddle = new LeftPaddle(0, GAME_HEIGHT / 2 - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
      leftPaddle.setBackground(new Color(34, 34, 34));
      
      RightPaddle rightPaddle = new RightPaddle(0, GAME_HEIGHT / 2 - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
      rightPaddle.setBackground(new Color(34, 34, 34));

      Ball ball = new Ball(BALL_SIZE, BALL_SIZE, leftPaddle, rightPaddle);
      ball.setBackground(new Color(17, 17, 17));
      
      Score score = new Score();
      RestartButton restartButton = new RestartButton();
      TopPanel topPanel = new TopPanel(restartButton, score);
      topPanel.setBackground(new Color(34, 34, 34));

      frame.add(topPanel, BorderLayout.NORTH);
      frame.add(ball, BorderLayout.CENTER);
      frame.add(leftPaddle, BorderLayout.WEST);
      frame.add(rightPaddle, BorderLayout.EAST);
      
      frame.setVisible(true);
      
      restartButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          ball.resetBall();
          leftPaddle.resetPaddle();
          rightPaddle.resetPaddle();
          leftPlayerScore = 0;
          rightPlayerScore = 0;
          frame.requestFocus();
        }
      });

      frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent event) {
          ball.closeThread();
          leftPaddle.closeThread();
          rightPaddle.closeThread();
          topPanel.closeThread();
          System.exit(0);
        }
      });

      frame.addKeyListener(new KeyListener(leftPaddle, rightPaddle));

      frame.requestFocus();
    }});
  }
}