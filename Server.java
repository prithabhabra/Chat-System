
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    public static void main(String args[]) throws IOException, InterruptedException{
        ServerSocket server_socket=new ServerSocket(8092);
        //Scanner sc=new Scanner(System.in);
        int cnt=0;
        while(true){
            Socket s=server_socket.accept();
            System.out.println("Client "+(++cnt)+" connected");
            ServerThread t=new ServerThread(s);
            t.start();
        }
    }
    static class ServerThread extends Thread{
        DataInputStream in;
        DataOutputStream out;
        String msg="";
        Socket s;
        static int counter=0;
        ServerThread(Socket s) throws IOException{
            in=new DataInputStream(s.getInputStream());
            out=new DataOutputStream(s.getOutputStream());
            this.s=s;
            counter++;
        }
        @Override
        public void run() {
           try {
                do{
                msg=in.readUTF();
                System.out.println("message: "+msg);
                if(!msg.equals("bye"))
                    out.writeUTF("received!");
                }while(!msg.equals("bye"));
                counter--;
                s.close();
                if(counter==0)
                    System.exit(0);
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
}

