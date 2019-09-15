import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Logger;

public class Main {
    public static void main(String [] args) {
        ReaderWriter rw= new ReaderWriter(args);
        System.out.println(rw.toString());
    }
}

package com.suai.fotieva.labwork11;

        import java.net.*;
        import java.io.IOException;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.util.Scanner;
        import  java.util.logging.Logger;
class Client  {
    public static void main(String[] args)throws Exception {
        Logger logger = Logger.getLogger(Client.class.getName());
        Scanner scanner = new Scanner(System.in);
        String message;
        try( DatagramSocket clientSocket = new DatagramSocket()) {
            byte[] sendData;
            int port = 9999;
            InetAddress ipAddress = InetAddress.getByName("localhost");
            sendData = "Client connected".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            clientSocket.send(sendPacket);
            ReceiveThread client = new ReceiveThread(clientSocket);
            client.start();
            do {
                message = scanner.nextLine();
                sendData = message.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                clientSocket.send(sendPacket);
            } while (!message.contains("@quit"));
            client.join();
        }catch (IOException e){
            logger.info(e.toString());
        }
    }
}


import java.io.IOException;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.InetAddress;
        import java.util.Scanner;
        import java.util.logging.Logger;
public class Server {
    public static void main(String[] args) throws Exception{
        Logger logger = Logger.getLogger(Server.class.getName());
        Scanner scanner = new Scanner(System.in);
        String message;
        try (DatagramSocket serverSocket = new DatagramSocket(9999)){
            byte[] receiveData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(packet);
            logger.info("Client is here ");
            InetAddress ipAddress = packet.getAddress();
            int port = packet.getPort();
            ReceiveThread server = new ReceiveThread(serverSocket);
            server.start();
            do {
                message = scanner.nextLine();
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, message.length(), ipAddress, port);
                serverSocket.send(sendPacket);
            } while (!message.contains("@quit"));
            server.join();
        }catch (IOException e){
            logger.info(e.toString());
        }
    }
}




//package com.suai.fotieva.labwork11;
//
//        import java.io.FileWriter;
//        import java.io.IOException;
//        import java.net.DatagramPacket;
//        import java.net.DatagramSocket;
//        import java.util.Vector;
//        import java.util.logging.Logger;
//
//public class ReceiveThread extends Thread {
//    static Vector<String> story= new Vector<String>();
//    static Object o= new Object();
//    private static int id=0;
//    private String name = "sender"+id;
//    private DatagramSocket socket;
//    private Logger logger = Logger.getLogger(ReceiveThread.class.getName());
//
//    ReceiveThread(DatagramSocket socket){
//        try (DatagramSocket socket1 = this.socket = socket) {
//            id++;
//        }
//    }
//    private boolean checkMessage (String message){
//        if(message.length()>4) {
//            if ("@name".equals(message.substring(0, 5))) {
//                this.name = message.substring(6);
//                return true;
//            } else if ("@quit".equals(message.substring(0, 5))) {
//                System.out.println(name + " disconnected");
//                return false;
//            }
//            else if("@dump".equals(message.substring(0, 5))){
//                writeStory(message.substring(6));
//            }
//        }
//        synchronized (o){
//            story.add(name+": "+message);
//        }
//        System.out.println(name+": "+message);
//        return true;
//    }
//
//    private void writeStory(String filePath){
//        try(FileWriter writer = new FileWriter(filePath, false))
//        {
//            for(int i=0; i<story.size();i++) {
//                writer.write(story.get(i));
//                writer.append('\n');
//            }
//            writer.flush();
//        }
//        catch(IOException e){
//
//            logger.info(e.toString());
//        }
//    }
//    @Override
//    public void run(){
//        while (true) {
//            try {
//                byte[] receiveData = new byte[1024];
//                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                socket.receive(receivePacket);
//                if (!checkMessage(new String(receivePacket.getData(), 0, receivePacket.getLength())))
//                {
//                    return;
//                }
//            } catch (IOException e) {
//                logger.info(e.toString());
//            }
//        }
//    }
//}

