import java.io.*;
import java.net.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Client extends Thread {
    private String name ="someone";
    private DatagramSocket clientSocket;

    public void run() {
        try {
            while (true) {

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
            }
        }
            catch  (IOException e) {
               System.out.println(e.toString());
        }
    }
    public static void main(String[] args)throws Exception {
        Client user = new Client();
        user.clientSocket= new DatagramSocket();
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence = inFromUser.readLine();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        sendData = sentence.getBytes();
        user.start();
        while(true) {
             inFromUser = new BufferedReader(new InputStreamReader(System.in));
            sentence = inFromUser.readLine();
            sendData = new byte[1024];
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9999);
            user.clientSocket.send(sendPacket);
            user.clientSocket.close();
        }

    }
}
