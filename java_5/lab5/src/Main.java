// Шабаршов Алексей, ПИН-36, Вариант 14

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import java.awt.*;

public class Main {
  public static Integer SIZE_X = 400;
  public static Integer SIZE_Y = 400;

  public static final Integer[] arrayAddSizes = {10, 100, 1000, 10000, 100000};

  public static ArrayList<Double> arrayAddTotalTime = new ArrayList<>();
  public static ArrayList<Double> arrayAddMediumTime = new ArrayList<>();
  public static ArrayList<Double> arrayRemoveTotalTime = new ArrayList<>();
  public static ArrayList<Double> arrayRemoveMediumTime = new ArrayList<>();

  public static ArrayList<Double> listAddTotalTime = new ArrayList<>();
  public static ArrayList<Double> listAddMediumTime = new ArrayList<>();
  public static ArrayList<Double> listRemoveTotalTime = new ArrayList<>();
  public static ArrayList<Double> listRemoveMediumTime = new ArrayList<>();

  public static void ReadFromFile(String fileName) throws IOException {
		Scanner fileScanner = new Scanner(new File(fileName));
    Boolean isArray = true;
    Boolean isAdd = true;

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
      String[] lineValues = line.split(" ");

      if(lineValues.length == 2) {
        if(lineValues[0].equals("ArrayList")) {
          isArray = true;
        } else {
          isArray = false;
        }

        if(lineValues[1].equals("Add")) {
          isAdd = true;
        } else {
          isAdd = false;
        }

        continue;
      }

      if(lineValues.length == 3) {
        // also convert from ( ns ) to ( ms )
        double value = Double.parseDouble(lineValues[2]) / 1000000.0;

        if(lineValues[0].equals("Total") && lineValues[1].equals("time:")) {
          if(isArray) {
            if(isAdd) {
              arrayAddTotalTime.add(value);
            } else {
              arrayRemoveTotalTime.add(value);
            }
          } else {
            if(isAdd) {
              listAddTotalTime.add(value);
            } else {
              listRemoveTotalTime.add(value);
            }
          }

          continue;
        }

        if(lineValues[0].equals("Medium") && lineValues[1].equals("time:")) {
          if(isArray) {
            if(isAdd) {
              arrayAddMediumTime.add(value);
            } else {
              arrayRemoveMediumTime.add(value);
            }
          } else {
            if(isAdd) {
              listAddMediumTime.add(value);
            } else {
              listRemoveMediumTime.add(value);
            }
          }

          continue;
        }
      }
    }

		fileScanner.close();
  }

  public static void PrintArray(ArrayList<Double> arr) {
    for (int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i));
    }
    System.out.println(" ");
  }

  public static DrawGraph CreateGraph(ArrayList<Double> arr, String title) {
    DrawGraph graphPanel = new DrawGraph(arr, title);
    graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
    graphPanel.setLayout(null);
    graphPanel.setPreferredSize(new Dimension(SIZE_X, SIZE_Y));

    return graphPanel;
  }
  public static void main(String[] args) {
    try {
      ReadFromFile("./results.txt");
    } catch(IOException e) {
      System.out.println("File read error!");
    }

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Lab 5");
        frame.setLayout(new GridLayout(2, 4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE_X * 4, SIZE_Y * 2);

        frame.add(CreateGraph(arrayAddTotalTime, "ArrayList add total"));
        frame.add(CreateGraph(arrayAddMediumTime, "ArrayList add average"));
        frame.add(CreateGraph(arrayRemoveTotalTime, "ArrayList remove total"));
        frame.add(CreateGraph(arrayRemoveMediumTime, "ArrayList remove average"));

        frame.add(CreateGraph(listAddTotalTime, "LinkedList add total"));
        frame.add(CreateGraph(listAddMediumTime, "LinkedList add average"));
        frame.add(CreateGraph(listRemoveTotalTime, "LinkedList remove total"));
        frame.add(CreateGraph(listRemoveMediumTime, "LinkedList remove average"));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}
