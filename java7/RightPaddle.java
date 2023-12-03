import java.awt.event.*;

public class RightPaddle extends Paddle {
  public RightPaddle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    this.setFocusable(true);

    new Thread(this).start();
  }

  public void keyTyped(KeyEvent e) {}

  public void keyPressed(KeyEvent e) {
    // Обработка нажатий клавиш W и S для левой ракетки
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      ySpeed = -10;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      ySpeed = 10;
    }
  }

  public void keyReleased(KeyEvent e) {
    // Обработка отпускания клавиш для остановки ракеток
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
      ySpeed = 0;
    }
  }

  @Override
  public void run() {
    while (isThreadRunning) {
      y += ySpeed;

      if (y <= 0 || y >= getHeight() - height - Main.MARGIN_BOTTOM) {
        y -= ySpeed;
      }

      // Перерисовываем панель
      repaint();

      // Приостанавливаем поток на короткое время (чтобы увидеть анимацию)
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
