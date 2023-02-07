package frc.robot.commands;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class MusicCmd extends CommandBase {
    public static List<TalonFX> talons = new ArrayList<>();
    private static Orchestra orchestra = new Orchestra();
    private final String fileName;

    public MusicCmd(String name) {
        this.fileName = name;
    }

    static boolean playing = false;
    @Override public void execute() {
        if(playing = !playing) {
            Robot.manualDrive = false;
            orchestra.loadMusic(Filesystem.getDeployDirectory().getAbsolutePath() + "/" + fileName);
            orchestra.play();
            System.out.println("playing I think " + orchestra.isPlaying());
        }
        else {
            orchestra.stop();
            Robot.manualDrive = true;
        }
    }

    @Override public boolean isFinished() {return true;}
    
    /*public static void init() {
        orchestra.clearInstruments();
        for(TalonFX talon : talons) {
            orchestra.addInstrument(talon);
        }
    }*/

    public static TalonFX add(TalonFX talon) {orchestra.addInstrument(talon);/*talons.add(talon);*/ return talon;}
}