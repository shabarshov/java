import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/*** 
В классе BallAnimation, где реализован интерфейс Runnable, запускается отдельный поток для выполнения анимации. В методе run() этого потока происходит обновление координат шарика и его перерисовка на панели.

Это позволяет обеспечить беспрерывное перемещение шарика в отдельном потоке, не блокируя главный поток выполнения программы.
***/

public class Ball extends JPanel implements Runnable {
  private int x;
  private int y;
  private int width;
  private int height;
  private LeftPaddle leftPaddle;
  private RightPaddle rightPaddle;
  private double xSpeed;
  private double ySpeed;
  private volatile boolean isThreadRunning = true;

  public Ball(int width, int height, LeftPaddle leftPaddle, RightPaddle rightPaddle) {
    this.x = Main.INITIAL_BALL_X;
    this.y = Main.INITIAL_BALL_Y;
    this.width = width;
    this.height = height;
    this.leftPaddle = leftPaddle;
    this.rightPaddle = rightPaddle;

    this.setBorder(BorderFactory.createMatteBorder(0, 0, 100, 0, new Color(34, 34, 34)));

    // Генерируем случайное направление движения в пределах от -4 до -2 и от 2 до 4
    Random random = new Random();
    this.xSpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);
    this.ySpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);

    // Запускаем поток для анимации
    new Thread(this).start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Рисуем круглый мяч
    Ellipse2D ball = new Ellipse2D.Double(x, y, width, height);
    g2d.setColor(Color.WHITE);
    g2d.fill(ball);
  }

  @Override
  public void run() {
    while (isThreadRunning) {
      // Обновляем координаты для анимации
      x += xSpeed;
      y += ySpeed;

      // Проверяем, достиг ли шарик верхней или нижней границы
      if (y >= this.getHeight() - this.height - Main.MARGIN_BOTTOM) {
        ySpeed = -ySpeed - 0.25;
      }

      if (y <= 0) {
        ySpeed = -ySpeed + 0.25;
      }

      if(this.x <= 0 &&
         this.y + this.height / 2 >= leftPaddle.getCurrentY() &&
         this.y + this.height / 2 <= leftPaddle.getCurrentY() + leftPaddle.getCurrentHeight()) {
        xSpeed = -xSpeed + 1;
      }
      else if(this.x <= 0) {
        Random random = new Random();
        
        this.x = Main.INITIAL_BALL_X;
        this.y = Main.INITIAL_BALL_Y;
        this.xSpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);
        this.ySpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);

        Main.rightPlayerScore += 1;
      }

      if(this.x + this.width + this.width / 2 >= Main.GAME_WIDTH - rightPaddle.getCurrentWidth() &&
         this.y + this.height / 2 >= rightPaddle.getCurrentY() &&
         this.y + this.height / 2 <= rightPaddle.getCurrentY() + rightPaddle.getCurrentHeight()) {
        xSpeed = -xSpeed - 1;
      }
      else if (this.x + this.width / 2 >= Main.GAME_WIDTH){
        Random random = new Random();
        
        this.x = Main.INITIAL_BALL_X;
        this.y = Main.INITIAL_BALL_Y;
        this.xSpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);
        this.ySpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);

        Main.leftPlayerScore += 1;
      }

      // Перерисовываем панель
      repaint();

      // Приостанавливаем поток на короткое время (чтобы увидеть анимацию)
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // Метод для сброса координат и скоростей шарика
  public void resetBall() {
    this.x = Main.INITIAL_BALL_X;
    this.y = Main.INITIAL_BALL_Y;

    // Генерируем случайное направление движения в пределах от -4 до -2 и от 2 до 4
    Random random = new Random();
    xSpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);
    ySpeed = (random.nextInt(3) + 2) * (random.nextBoolean() ? 1 : -1);
  }

  public void closeThread() {
    this.isThreadRunning = false;
  }
}
