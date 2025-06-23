
import java.io.*;
import java.net.Socket;

public class SmptEmail {

    private String smtpHost;
    private int smtpPort;

    public SmptEmail(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public void sendEmail(String from, String to, String subject, String body) throws IOException {
        Socket socket = new Socket(smtpHost, smtpPort);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        readResponse(reader); // Read greeting from server
        sendCommand(writer, "HELO " + smtpHost);
        readResponse(reader);

        sendCommand(writer, "MAIL FROM:<" + from + ">");
        readResponse(reader);

        sendCommand(writer, "RCPT TO:<" + to + ">");
        readResponse(reader);

        sendCommand(writer, "DATA");
        readResponse(reader);

        sendCommand(writer, "From: " + from);
        sendCommand(writer, "To: " + to);
        sendCommand(writer, "Subject: " + subject);
        sendCommand(writer, "");
        sendCommand(writer, body);
        sendCommand(writer, ".");
        readResponse(reader);

        sendCommand(writer, "QUIT");
        readResponse(reader);

        socket.close();
    }

    private void sendCommand(BufferedWriter writer, String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
    }

    private void readResponse(BufferedReader reader) throws IOException {
        String response = reader.readLine();
        if (!response.startsWith("250") && !response.startsWith("220") && !response.startsWith("354")) {
            throw new IOException("SMTP Error: " + response);
        }
    }
}
