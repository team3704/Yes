package frc.robot.subsystems;

import static frc.robot.Constants.*;
import static java.lang.Math.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Coms;
import frc.robot.Robot;
import frc.robot.commands.MusicCmd;

public class DriveSub extends SubsystemBase {
    public boolean inverted = false;
    private TalonFX
        m_bl = MusicCmd.add(new TalonFX(idDT_bl)),
        m_br = MusicCmd.add(new TalonFX(idDT_br)),
        m_fr = MusicCmd.add(new TalonFX(idDT_fr)),
        m_fl = MusicCmd.add(new TalonFX(idDT_fl));

    Timer aX = new Timer(), aY = new Timer();

    public DriveSub() {
        aX.start();
        aY.start();
    }

    public double testDeadband(double value, double max, double deadband) {
        return value <= deadband ? 0 : value / max; /*scale from value / value;*/
    }
    public void arcadeDrive(double x, double y, double z) {
        double dist = Robot.rangeFinder.get();
        if(Coms.test) {
            if(z < 0.1) {System.out.println("nop" + (int) (Math.random() * 3)); z = 0; return;}
            else {z = 0.25;}
            x = Coms.extData[0] == 1 ? -1 : Coms.extData[2] == 1 ? 1 : 0;
            y = Coms.extData[1];
            if(x == 0) aX.reset();
            if(y == 0) aY.reset();
            y *= (min(aY.get(), 5) / 9) + 0.4;
            x *= (min(aX.get(), 5) / 9) + 0.4;
            m_bl.set(ControlMode.PercentOutput, (y + x) * z);
            m_fl.set(ControlMode.PercentOutput, (y + x) * z);
            m_br.set(ControlMode.PercentOutput, -(y - x) * z);
            m_fr.set(ControlMode.PercentOutput, -(y - x) * z);
            return;
        }
        if(inverted) {
            z =  y < 0 ? testDeadband(min(dist / 3.8, 100), 100, 30) * z : z;
            m_fr.set(ControlMode.PercentOutput, (y + x) * z);
            m_br.set(ControlMode.PercentOutput, (y + x) * z);
            m_fl.set(ControlMode.PercentOutput, -(y - x) * z);
            m_bl.set(ControlMode.PercentOutput, -(y - x) * z);
        } else {
            z =  y > 0 ? testDeadband(min(dist / 3.8, 100), 100, 30) * z : z;
            m_bl.set(ControlMode.PercentOutput, (y + x) * z);
            m_fl.set(ControlMode.PercentOutput, (y + x) * z);
            m_br.set(ControlMode.PercentOutput, -(y - x) * z);
            m_fr.set(ControlMode.PercentOutput, -(y - x) * z);
        }
    }
    public void tankDrive(double left, double right) {
        if(inverted) {
            m_fr.set(ControlMode.PercentOutput, -left);
            m_br.set(ControlMode.PercentOutput, -left);
            m_fl.set(ControlMode.PercentOutput, right);
            m_bl.set(ControlMode.PercentOutput, right);
        } else {
            m_bl.set(ControlMode.PercentOutput, -left);
            m_fl.set(ControlMode.PercentOutput, -left);
            m_br.set(ControlMode.PercentOutput, right);
            m_fr.set(ControlMode.PercentOutput, right);
        }
    }

    public void invert() {
        System.out.println("inverted: " + (inverted = !inverted));
    }

}
