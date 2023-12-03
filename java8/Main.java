import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        final String server = "smtp.mail.ru";
        final int port = 465; // Используем порт для SSL
        final String username = "alexey_shabarshov@mail.ru";
        final String password = "aj9b84jmzBZEecLq9f1d";
        final String recipient = "meepoonnewacc2@mail.ru";

        try (SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(server, port);
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {

            readResponse(reader);

            sendCommand(writer, "EHLO localhost");
            readResponse(reader);

            sendCommand(writer, "AUTH LOGIN");
            readResponse(reader);
            sendCommand(writer, base64Encode(username));
            readResponse(reader);
            sendCommand(writer, base64Encode(password));
            readResponse(reader);

            sendCommand(writer, "MAIL FROM:<" + username + ">");
            readResponse(reader);
            sendCommand(writer, "RCPT TO:<" + recipient + ">");
            readResponse(reader);

            sendCommand(writer, "DATA");
            readResponse(reader);

            System.out.println("Sending Subject");
            sendCommand(writer, "Subject: Тема письма");
            String responseSubject = readResponse(reader);
            System.out.println("Response after sending subject: " + responseSubject);

            System.out.println("Sending empty line");
            sendCommand(writer, "");
            String responseEmptyLine = readResponse(reader);
            System.out.println("Response after sending empty line: " + responseEmptyLine);

            System.out.println("Sending body");
            sendCommand(writer, "Привет, это текст письма.");
            String responseBody = readResponse(reader);
            System.out.println("Response after sending body: " + responseBody);

            System.out.println("Sending period");
            sendCommand(writer, ".");
            String responseAfterDot = readResponse(reader);
            System.out.println("Response after sending period: " + responseAfterDot);

            sendCommand(writer, "QUIT");
            readResponse(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendCommand(PrintWriter writer, String command) {
        System.out.println("SENT: " + command);
        writer.println(command);
    }

    private static String readResponse(BufferedReader reader) throws IOException {
        String response = reader.readLine();
        System.out.println("RECEIVED: " + response);
        return response;
    }

    private static String base64Encode(String input) {
      try {
          return Base64.getEncoder().encodeToString(input.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
          return null;
      }
  }
}
