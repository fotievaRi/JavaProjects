import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class Client  {

    public static void main(String[] args)throws Exception {
        Scanner scanner = new Scanner(System.in);
            String message;
            byte[] sendData = new byte[1024];
            int port = 9999;
            InetAddress ipAddress = InetAddress.getByName("localhost");
            DatagramSocket clientSocket= new DatagramSocket();
            ReceiveThread client  =new ReceiveThread (clientSocket ,port, ipAddress);
            client.start();
            sendData = "Client connected".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            clientSocket.send(sendPacket);
            while(true) {
            message = scanner.nextLine();
            sendData = message.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            clientSocket.send(sendPacket);
            if(message.contains("@quit")){
                break;
            }
        }
    }
}
