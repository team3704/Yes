package frc.robot.commands;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MusicCmd extends CommandBase {
    public static List<TalonFX> talons = new ArrayList<>();
    private static Orchestra orchestra = new Orchestra();
    private final String fileName;

    public MusicCmd(String name) {
        this.fileName = name;
    }
    boolean playing = false;
    @Override public void execute() {
        if(playing) orchestra.pause();
        else {
            orchestra.loadMusic(Filesystem.getDeployDirectory().getAbsolutePath() + "/notBoring.chrp");
            orchestra.play();
        }
        playing = !playing;
    }

    @Override public boolean isFinished() {return true;}
    
    public void initialize() {
        orchestra.clearInstruments();
        for(TalonFX talon : talons) {
            orchestra.addInstrument(talon);
        }
    }

    public static void add(TalonFX talon) {talons.add(talon);}
}
