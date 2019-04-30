package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * It adds clients to server....server will wait 15 seconds for connecting and sending data to clients
*/
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author wakil
 */
class ClientAdder extends Thread{
    Socket socket;
    ServerSocket serverSocket ;
    ArrayList<Socket>sockets;
    private volatile boolean exit=false;
    public ClientAdder(ServerSocket serverSocket, ArrayList<Socket> sockets) {
        this.serverSocket = serverSocket;
        this.sockets = sockets;
    }
    @Override
    public void run(){
        
        while(!exit){
            try {
                socket = serverSocket.accept();
                socket.setTcpNoDelay(true);
                sockets.add(socket);
                System.out.println("ClientConnected");
                
            } catch (IOException ex) {
                System.out.println("Stopped Accepting new Clients");
            }
            
        }
        
        
    }
    public void stopThread(){       //for stopping this thread
        exit = true;
    }
    
}