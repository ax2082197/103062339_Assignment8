package com.example;

import java.awt.Dimension;
import java.awt.TextArea;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;

public class Server extends JFrame implements Runnable{
    private Thread thread;
    private ServerSocket servSock;
    private TextArea text;

    public Server(){
        super("Assignment 8");
        this.setSize(new Dimension(400,300));
        text = new TextArea();
        text.setSize(new Dimension(400,300));
        this.add(text);
        this.setVisible(true);
        try{
            InetAddress IP = InetAddress.getLocalHost();
            text.append("IP of my system is = "+IP.getHostAddress()+"\n");
            text.append("Waitting to connect......\n");
            servSock = new ServerSocket(2000);
            thread = new Thread(this);
            thread.start();
        }catch (java.io.IOException e){
            System.out.println("IOException :" + e.toString()+"\n");
        }finally{
        }
    }

    public void run(){
        while(true){
            try{
                Socket clntSock = servSock.accept();
                InputStream in = clntSock.getInputStream();
                text.append("Connected!!"+"\n");

                byte[] b = new byte[1024];
                int length;
                length = in.read(b);
                String s = new String(b);
                text.append("[Server said]: " + s + "\n");
            }catch(java.io.IOException e){
            }
        }
    }

    public static void main(String[] args){
        Server showR = new Server();
        showR.run();
    }
}
