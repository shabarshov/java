// Шабаршов Алексей, ПИН-36, Вариант 14 (6)

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
  public static Integer countOfSymbols = 0;
  public static Integer startTime = 0;
  public static Boolean isStartedTyping = false;

  public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
        JFrame frame = new JFrame("Lab 6 - speedtest");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TextArea textArea = new TextArea();
        SpeedInfo label = new SpeedInfo();
        
        frame.add(textArea, BorderLayout.CENTER);
        frame.add(label, BorderLayout.NORTH);

        Timer timer = new Timer(1000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if(isStartedTyping) {
              startTime += 1;
              label.SetSpeed(((int)Math.floor(60 * countOfSymbols / startTime)));
              label.repaint();
            }
            System.out.println(startTime);
          }
        });
        
        timer.start();

        frame.setVisible(true);
      }
    });
  }
}
