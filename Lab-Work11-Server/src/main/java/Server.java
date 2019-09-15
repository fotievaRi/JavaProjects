import java.io.*;
import java.net.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;


    public class Server extends Thread{
        InetAddress ipAddress;
        int port;
        private DatagramSocket serverSocket;
        Server(DatagramSocket socket, InetAddress ipAddress, int port){
            this.ipAddress=ipAddress;
            this.port=port;
            this.serverSocket=socket;
        }
        public void run(){
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    serverSocket.receive(receivePacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String sentence = new String( receivePacket.getData());
                System.out.println("client: " + sentence);
            }
        }

        public static void main(String[] args) throws Exception{
            DatagramSocket socket= new DatagramSocket(9999);
            byte[] sendData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length);
            socket.receive(packet);
            InetAddress ipAddress = packet.getAddress();
            int port = packet.getPort();
            Server  server= new Server(socket,ipAddress,port);
            server.start();
            Scanner scanner = new Scanner(System.in);
            String sentence;
            server.start();
            while(true) {
                sentence = scanner.nextLine();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sentence .length(), ipAddress, port);
                socket.send(sendPacket);
            }
        }
    }
