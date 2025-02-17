import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur démarré sur le port 12345...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté!");

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                if ((clientMessage = input.readLine()) != null) {
                    System.out.println("Client : " + clientMessage);
                }

                System.out.print("Serveur : ");
                serverMessage = userInput.readLine();
                output.println(serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

