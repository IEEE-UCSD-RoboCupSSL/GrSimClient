package Client;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import Protobuf.*;

public class Connection {

    String simAddress, visionAddress;
    int commandListenPort, visionPort, id;
    byte[] buffer2;
    int envio = 1;
    float timeStamp, wheel1, wheel2, wheel3, wheel4, kickspeedx, kickspeedz, velX, velY, velZ;
    boolean spinner, useWheelSpeed, isYellow;

    Connection() {
        simAddress = "127.0.0.1";
        commandListenPort = 20011;
        id = 0;
        timeStamp = 0;
        wheel1 = 0;
        wheel2 = 0;
        wheel3 = 0;
        wheel4 = 0;
        kickspeedx = 0;
        kickspeedz = 0;
        velX = 0;
        velY = 0;
        velZ = 0;
        spinner = false;
        useWheelSpeed = false;
        isYellow = false;
    }

    Connection(String simAddress, int commandListenPort, boolean isYellow, int id) {
        this.simAddress = simAddress;
        this.commandListenPort = commandListenPort;
        this.isYellow = isYellow;
        this.id = id;

        timeStamp = 0;
        wheel1 = 0;
        wheel2 = 0;
        wheel3 = 0;
        wheel4 = 0;
        kickspeedx = 0;
        kickspeedz = 0;
        velX = 0;
        velY = 0;
        velZ = 0;
        spinner = false;
        useWheelSpeed = false;
    }

    public void send() {
        GrSimCommands.grSim_Robot_Command command = GrSimCommands.grSim_Robot_Command.newBuilder().setId(this.id)
                .setWheel2(this.wheel2).setWheel1(this.wheel1).setWheel3(this.wheel3).setWheel4(this.wheel4)
                .setKickspeedx(this.kickspeedx).setKickspeedz(this.kickspeedz).setVeltangent(this.velX)
                .setVelnormal(this.velY).setVelangular(this.velZ).setSpinner(this.spinner)
                .setWheelsspeed(this.useWheelSpeed).build();
        GrSimCommands.grSim_Commands command2 = GrSimCommands.grSim_Commands.newBuilder().setTimestamp(this.timeStamp)
                .setIsteamyellow(this.isYellow).addRobotCommands(command).build();
        GrSimPacket.grSim_Packet packet = GrSimPacket.grSim_Packet.newBuilder().setCommands(command2).build();

        String buffer = packet.toString();
        buffer2 = packet.toByteArray();
        try {
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(buffer2, buffer2.length, InetAddress.getByName(simAddress), commandListenPort);
            ds.send(dp);
            System.out.println(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setSimAddress(String simAddress) {
        this.simAddress = simAddress;
    }

    public void setCommandListenPort(int commandListenPort) {
        this.commandListenPort = commandListenPort;
    }

    public void setIsYellow(boolean isYellow) {
        this.isYellow = isYellow;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(float timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setWheel1(float wheel1) {
        this.wheel1 = wheel1;
    }

    public void setWheel2(float wheel2) {
        this.wheel2 = wheel2;
    }

    public void setWheel3(float wheel3) {
        this.wheel3 = wheel3;
    }

    public void setWheel4(float wheel4) {
        this.wheel4 = wheel4;
    }

    public void setKickspeedX(float kickspeedx) {
        this.kickspeedx = kickspeedx;
    }

    public void setKickspeedZ(float kickspeedz) {
        this.kickspeedz = kickspeedz;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setVelZ(float velZ) {
        this.velZ = velZ;
    }

    public void setSpinner(boolean spinner) {
        this.spinner = spinner;
    }

    public void setUseWheelSpeed(boolean useWheelSpeed) {
        this.useWheelSpeed = useWheelSpeed;
    }
}
