import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class server {
    public static void main(String[] args ) throws IOException {
        int PORT = 3307;
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Please Run Client");
        cache caching = new cache();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread() {
                public void run() {
                    try (
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                    ) {

                        String i, o;
                        mLogic connect = new mLogic();
                        o = connect.Input(null, caching);
                        out.println(o);

                        while ((i = in.readLine()) != null) {
                            o = connect.Input(i.toLowerCase(), caching);
                            out.println(o);
                        }
                    } catch (IOException e) { }
                }
            };
            t.start();
        }
    }
}

