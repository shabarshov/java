import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        // Установка параметров для подключения к почтовому серверу
        final String server = "smtp.mail.ru";
        final int port = 465; // Используем порт для SSL
        final String username = "alexey_shabarshov@mail.ru";
        final String password = "aj9b84jmzBZEecLq9f1d";
        final String recipient = "meepoonnewacc2@mail.ru";

        try (SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(server, port);
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))) {

            // Прочитать и вывести приветственный баннер сервера
            readResponse(reader);

            // Отправить команду приветствия серверу (EHLO)
            sendCommand(writer, "EHLO localhost");
            // Прочитать и вывести ответ сервера
            readResponse(reader);

            // Аутентификация пользователя
            sendCommand(writer, "AUTH LOGIN");
            // Прочитать и вывести ответ сервера
            readResponse(reader);
            sendCommand(writer, base64Encode(username));
            readResponse(reader);
            sendCommand(writer, base64Encode(password));
            // Прочитать и вывести ответ сервера
            readResponse(reader);

            // Указать отправителя и получателя
            sendCommand(writer, "MAIL FROM:<" + username + ">");
            // Прочитать и вывести ответ сервера
            readResponse(reader);
            sendCommand(writer, "RCPT TO:<" + recipient + ">");
            // Прочитать и вывести ответ сервера
            readResponse(reader);

            // Начать отправку тела письма
            sendCommand(writer, "DATA");
            // Прочитать и вывести ответ сервера
            readResponse(reader);

            // Отправка темы письма
            System.out.println("Sending Subject");
            sendCommand(writer, "Subject: Тема письма");
            String responseSubject = readResponse(reader);
            System.out.println("Response after sending subject: " + responseSubject);

            // Отправка пустой строки
            System.out.println("Sending empty line");
            sendCommand(writer, "");
            String responseEmptyLine = readResponse(reader);
            System.out.println("Response after sending empty line: " + responseEmptyLine);

            // Отправка тела письма
            System.out.println("Sending body");
            sendCommand(writer, "Привет, это текст письмааааааааа.");
            String responseBody = readResponse(reader);
            System.out.println("Response after sending body: " + responseBody);

            // Завершение тела письма
            System.out.println("Sending period");
            sendCommand(writer, ".");
            // Прочитать и вывести ответ сервера
            String responseAfterDot = readResponse(reader);
            System.out.println("Response after sending period: " + responseAfterDot);

            // Завершение сеанса
            sendCommand(writer, "QUIT");
            // Прочитать и вывести ответ сервера
            readResponse(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отправка команды серверу
    private static void sendCommand(PrintWriter writer, String command) {
        System.out.println("SENT: " + command);
        writer.println(command);
    }

    // Чтение ответа сервера
    private static String readResponse(BufferedReader reader) throws IOException {
        String response = reader.readLine();
        System.out.println("RECEIVED: " + response);
        return response;
    }

    // Кодирование строки в формат Base64
    private static String base64Encode(String input) {
        try {
            return Base64.getEncoder().encodeToString(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
