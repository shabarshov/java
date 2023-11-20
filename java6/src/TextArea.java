import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class TextArea extends JTextArea implements KeyListener {
  private Border margin = BorderFactory.createEmptyBorder(20, 20, 20, 20);
  private Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
  private Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

  private Border compoundBorder = new CompoundBorder(margin, new CompoundBorder(border, padding));

  TextArea() {
    this.setFont(new Font("Serif", Font.BOLD, 26));
    this.setLineWrap(true);
    this.setWrapStyleWord(true);
    this.setSize(400, 300);
    this.setBorder(this.compoundBorder);
    this.addKeyListener(this);
  }

  public void keyPressed(KeyEvent e) {
    Main.countOfSymbols += 1;
    Main.isStartedTyping = true;
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}
}
