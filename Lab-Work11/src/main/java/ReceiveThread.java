import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class ReceiveThread extends Thread {
    private static int id=0;
    private String name;
    private DatagramSocket socket;
    private int port;
    private InetAddress ipAdress;
    private Logger logger = Logger.getLogger(ReceiveThread.class.getName());

    ReceiveThread(DatagramSocket socket, int port, InetAddress ipAdress){
        this.socket=socket;
        this.ipAdress=ipAdress;
        this.port=port;
        id++;
        this.name="sender"+id;
    }
    private boolean checkMessage (String message){
        if(message.length()>4) if ("@name".equals(message.substring(0, 5))) {
            this.name = message.substring(6, message.length());
            return true;
        } else if ("@quit".equals(message.substring(0, 5))) {
            System.out.println(name + " disconnected");
            return false;
        }
        System.out.println(name+": "+message);
        return true;
    }
    @Override
    public void run(){
        while (true) {
            try {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                if (!checkMessage(new String(receivePacket.getData(), 0, receivePacket.getLength())))
                {
                    return;
                }
            } catch (IOException e) {
                logger.info(e.toString());
            }
        }
    }
}
