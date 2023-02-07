package frc.robot;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static java.lang.System.out;

public class Coms {
    private static ServerSocket s = null;
    private static Socket client = null;
    private static DataInputStream input;
    private static BufferedOutputStream output;
    public static byte[] extData = new byte[3];
    public static boolean test = false;
    public static void connect() {
        if(s != null) return;
        new Thread(
            () -> {
                try {
                    s = new ServerSocket(51803);
                    out.println("awaiting connection...");
                    client = s.accept();
                    out.println("Connected to client");
                    input = new DataInputStream(client.getInputStream());
                    output = new BufferedOutputStream(client.getOutputStream());
                }
                catch (IOException e) {e.printStackTrace();}
                awaitData();
            }
        ).start();
    }

    public static void disconnect() {
        try {
            if(s != null) {
                s.close(); s = null; out.println("Disconnected");
                input.close(); input = null;
                output.close(); output = null;
            }
            if(client != null) client.close(); client = null;
        } catch (IOException e) {e.printStackTrace();}
    }

    public static void awaitData() {
        while(true) {
            try {
                input.readFully(extData);//.read(buffer, 0, 3);
                out.println("successfully read " + Arrays.toString(extData));
            } catch (IOException e) {
                System.out.println("check");
                //e.printStackTrace();
                disconnect();
                connect();
                return;
            } 
        }
    }
}
