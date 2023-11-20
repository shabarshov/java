import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Config {
  private String username;
  private Boolean logs;

  public Config (String configFileName){
    try {
      Properties props = new Properties();
      props.load(new FileInputStream(new File(configFileName)));
  
      this.logs = Boolean.parseBoolean(props.getProperty("SET_LOGS"));
      this.username = props.getProperty("USER_NAME");
    } catch (IOException e) {
      this.logs = true;
      this.username = "undefined";
      this.Log("Config file error", "../logs.txt");
    }
  }

  public Config() {
    this.logs = false;
    this.username = "undefined";
  }

  public String GetUsername() {
    return this.username;
  }

  public Boolean GetLogs() {
    return this.logs;
  }

  public void ErrorLog(String logValue, String fileName) {
    if(this.logs) {
      try {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Main.WriteToFile("\t" + logValue + " " + currentTime + "\n", fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void Log(String logValue, String fileName) {
    if(this.logs) {
      try {
        Main.WriteToFile(logValue + " \n", fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void StartLog(String fileName) {
    if(this.logs) {
      try {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Main.WriteToFile("The program was launched by " + this.username + " " + currentTime + "\n" , fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void EndLog(String fileName) {
    if(this.logs) {
      try {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Main.WriteToFile("The program has been ended" + " " + currentTime + "\n\n" , fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void WriteLog(String fileName) {
    if(this.logs) {
      try {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Main.WriteToFile("\tWriting to a file by " + this.username + " " + currentTime + "\n" , fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void ReadLog(String fileName) {
    if(this.logs) {
      try {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Main.WriteToFile("\tReading from a file by " + this.username + " " + currentTime + "\n" , fileName, true);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }
}