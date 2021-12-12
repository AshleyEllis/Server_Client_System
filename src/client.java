import java.io.*;
import java.net.*;
import java.util.*;
public class client {
    public static void main(String[] args) throws IOException {


        final String HOST = "127.0.0.1";
        final int PORT = 3307;


        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());
                Scanner s = new Scanner(System.in);
        ) {
            String fromServer;
            String fromUser;
            System.out.println("********************************************************************************");
            System.out.println("*                                                                              *");
            System.out.println("* ____________________________________________________________________________ *");
            System.out.println("  ************* Store " + " Update " + " Remove " +  " List" + " Read *****************************");
            System.out.println("*------------------------------------------------------------------------------*");
            System.out.println("*          Store: Type store <file>.txt Then list files. You                   *");
            System.out.println("*                 will see your new file!                                      *");
            System.out.println("*          Update: Update file contents by typing update <file>.txt            *");
            System.out.println("*                  type what you will. Then, to check it read file             *");
            System.out.println("*          Remove: Type remove <file>.txt Then, type list, to see the removed  *");
            System.out.println("*                   file has vanished                                          *");
            System.out.println("*          List: Type list You will see all files                              *");
            System.out.println("*          Read: Type read <file>.txt to read contents of file. Included is a  *");
            System.out.println("*                word and line count.                                          *");
            System.out.println("*                                                                              *");
            System.out.println("********************************************************************************");

            while ((fromServer = in.nextLine()) != null) {
                 System.out.println("Server: " + fromServer);
                fromUser = s.nextLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        }
    }
}
