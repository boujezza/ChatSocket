package UDPCHAT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatClientUDP {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";  // adresse du serveur (localhost)
        final int SERVER_PORT = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            while (true) {
                // Saisie du message à envoyer
                System.out.print("Client : ");
                String message = userInput.readLine();
                if (message == null || message.isEmpty()) {
                    continue;
                }

                // Envoi du message au serveur
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, SERVER_PORT);
                socket.send(packet);

                // Préparation pour recevoir la réponse du serveur
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);  // reçoit la réponse (appel bloquant)
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Serveur : " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}