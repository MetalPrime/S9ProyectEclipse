import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

public class UDPConnection extends Thread {
	private DatagramSocket socket;
    private OnMessageListener observer;

    public void setObserver(OnMessageListener observer){
        this.observer = observer;
    }

    @Override
    public void run() {
        try {
            //1.Escuchar
            socket = new DatagramSocket(6000);
            //2.Esperar mensajes Datagramas

            while (true){
                byte[] buffer = new byte[30];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                System.out.println("Status Esperando datagrama");
                socket.receive(packet);
                
             
  
                String mensaje = new String(packet.getData()).trim();
                
                String socket = packet.getSocketAddress().toString();
                
                String[] ipElement = socket.split(":");
                String[] ip = ipElement[0].split("/");
                

                Gson gson = new Gson();
                addOrder newOrder = gson.fromJson(mensaje, addOrder.class);
                observer.newOrder(newOrder.getType(),ip[1]);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(String msg,String ip){
        new Thread(
                ()->{
                    try {
                        InetAddress ipSocket = InetAddress.getByName(ip);
       				 System.out.println(ipSocket);
                        DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.getBytes().length,ipSocket,5000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}
