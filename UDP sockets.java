import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {

        DatagramSocket server = new DatagramSocket(5000);

        DatagramSocket client = new DatagramSocket();

        String domain = "google.com";
        byte[] sendData = domain.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length,
                InetAddress.getByName("localhost"), 5000);
        client.send(sendPacket);

        byte[] receiveData = new byte[100];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        server.receive(receivePacket);

        String name = new String(receivePacket.getData(), 0, receivePacket.getLength());

        String ip;
        if (name.equals("google.com"))
            ip = "142.250.183.14";
        else
            ip = "Not Found";
        byte[] reply = ip.getBytes();
        DatagramPacket replyPacket = new DatagramPacket(
                reply, reply.length,
                receivePacket.getAddress(), receivePacket.getPort());
        server.send(replyPacket);
        byte[] result = new byte[100];
        DatagramPacket resultPacket = new DatagramPacket(result, result.length);
        client.receive(resultPacket);

        System.out.println("Domain : " + domain);
        System.out.println("IP Address : " +
                new String(resultPacket.getData(), 0, resultPacket.getLength()));

        server.close();
        client.close();
    }
}
