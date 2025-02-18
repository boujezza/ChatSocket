package UDPCHAT;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatServerUDP {

        public static void main(String[] args) {
        final int PORT = 12345;
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Serveur UDP démarré sur le port " + PORT + "...");
            byte[] buffer = new byte[1024];

            while (true) {
                // Préparation pour recevoir un datagramme
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);  // attend un paquet (appel bloquant)

                // Extraction du message envoyé par le client
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Client : " + message);

                // Ici, on peut par exemple renvoyer une réponse au client
                String response = "Message reçu: " + message;
                byte[] responseData = response.getBytes();

                // Création du paquet de réponse en utilisant l'adresse et le port du client
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
