package frc.robot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class Coms {
    private static ServerSocket s = null;
    private static Socket client = null;
    private static BufferedInputStream input;
    private static BufferedOutputStream output;
    static byte recieved = 0;
    private static byte[] buffer = new byte[3];

    public static void connect() {
        try {
            s = new ServerSocket(50183);
            client = s.accept();
            out.println("Connected");
            input = new BufferedInputStream(client.getInputStream());
            output = new BufferedOutputStream(client.getOutputStream());
        }
        catch (IOException e) {e.printStackTrace();}
        out.println("entering");
        while(!s.isClosed()) {
            recieved = request()[0];
            out.println(recieved + " from loop");
            //RobotContainer.shooter.setOutput(MathUtil.clamp(recieved, -80, 80));
        }
        out.println("exiting");
    }

    public static void disconnect() {
        try {
            if(s != null) {s.close(); out.println("Disconnected");}
            if(client != null) client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recieved = 0;
    }

    public static byte[] request() {
        try {
            output.write(1);
            output.flush();
            out.println("flushed");
            input.read(buffer);
            out.println("successfully read value " + buffer[0]);
        } catch (IOException e) {
            System.out.println("check");
            e.printStackTrace();
            disconnect();
        }
        return buffer;
    }
}
