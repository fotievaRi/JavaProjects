import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String message;
        byte[] sendData = new byte[1024];
        DatagramSocket serverSocket= new DatagramSocket(9999);
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length);
        serverSocket.receive(packet);
        InetAddress ipAddress = packet.getAddress();
        int port = packet.getPort();
        ReceiveThread  server= new ReceiveThread(serverSocket,port,ipAddress);
        server.start();
        while(true) {
            message = scanner.nextLine();
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, message .length(), ipAddress, port);
            serverSocket.send(sendPacket);
            if(message.contains("@quit")){
                break;
            }
        }
    }
}