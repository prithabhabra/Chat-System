
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String args[]) throws IOException{
        Socket s=new Socket("127.0.0.1",8092);
        System.out.println("Connected to server");
        String msg;
        DataOutputStream dc=new DataOutputStream(s.getOutputStream());
        DataInputStream di=new DataInputStream(s.getInputStream());
        Scanner sc=new Scanner(System.in);
        do{
             System.out.println("Client message:");
             msg=sc.next();
             dc.writeUTF(msg);
             if(!msg.equals("bye"))
                System.out.println("Server: "+di.readUTF());
        }while(!msg.equals("bye"));
        s.close();
        
    }
    
}
