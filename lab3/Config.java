import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
      System.out.println("Problems with opening the config");
      this.logs = false;
      this.username = "undefined";
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
        Main.WriteToFile("\t" + logValue, fileName);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void StartLog(String fileName) {
    if(this.logs) {
      try {
        Main.WriteToFile("The program was launched by " + this.username + "\n" , fileName);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }

  public void WriteLog(String fileName) {
    if(this.logs) {
      try {
        Main.WriteToFile("\tWriting to a file by " + this.username + "\n" , fileName);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }
  public void ReadLog(String fileName) {
    if(this.logs) {
      try {
        Main.WriteToFile("\tReading from a file by " + this.username + "\n" , fileName);
      } catch (IOException e) {
        Printer.LogWriteError();
      }
    }
  }
}
