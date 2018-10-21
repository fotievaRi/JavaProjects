import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server extends Thread{
    private DatagramSocket serverSocket;
    public void run(){
        try {
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                String sentence = new String( receivePacket.getData());
                System.out.println("client: " + sentence);
            }
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) throws Exception{
        Server user = new Server();
        user.serverSocket = new DatagramSocket(9999);
        while(true) {


            byte[] sendData = new byte[1024];
            int port = receivePacket.getPort();

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence2 = inFromUser.readLine();
            sendData=sentence2.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,
                    port);
            serverSocket.send(sendPacket);
        }
    }
}