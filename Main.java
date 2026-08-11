import java.net.*;
import java.io.*;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Thread server = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(5000);
                System.out.println("Server started...");
                System.out.println("Waiting for client...");

                Socket s = ss.accept();
                System.out.println("Client connected.");

                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                out.println("Server Date & Time: " + new Date());

                out.close();
                s.close();
                ss.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread client = new Thread(() -> {
            try {
                Thread.sleep(1000);

                Socket s = new Socket("localhost", 5000);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                System.out.println("Client Received: " + in.readLine());

                in.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        server.start();
        client.start();

        try {
            server.join();
            client.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
