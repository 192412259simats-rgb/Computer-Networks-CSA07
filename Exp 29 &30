import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(5000);
                System.out.println("Server started...");
                Socket s = ss.accept();
                System.out.println("Client connected");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Client: " + msg);
                    if (msg.equalsIgnoreCase("bye"))
                        break;
                    out.println("Server received: " + msg);
                }
                s.close();
                ss.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Socket s = new Socket("localhost", 5000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(
                        s.getOutputStream(), true);
                out.println("Hello server");
                System.out.println("Server: " + in.readLine());
                out.println("How are you?");
                System.out.println("Server: " + in.readLine());
                out.println("bye");
                s.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        server.start();
        client.start();
    }
}
