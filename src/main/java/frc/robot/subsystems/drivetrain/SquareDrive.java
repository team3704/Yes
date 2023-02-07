package frc.robot.subsystems.drivetrain;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Coms;

public class SquareDrive extends SubsystemBase {
    private boolean inverted = false;
    private TalonFX
        m_bl = new TalonFX(idDT_bl),
        m_br = new TalonFX(idDT_br),
        m_fr = new TalonFX(idDT_fr),
        m_fl = new TalonFX(idDT_fl);

    public void arcadeDrive(double x, double y, double z) {
        if(Coms.test) {
            if(z < 0.1) {System.out.println("nop" + (int) (Math.random() * 3)); return;}
            x = Coms.extData[0] == 1 ? -1 : Coms.extData[2] == 1 ? 1 : 0;
            y = Coms.extData[1];
            z = 0.15;
            m_bl.set(ControlMode.PercentOutput, (y + x) * z);
            m_fl.set(ControlMode.PercentOutput, (y + x) * z);
            m_br.set(ControlMode.PercentOutput, -(y - x) * z);
            m_fr.set(ControlMode.PercentOutput, -(y - x) * z);
        }
        if(inverted) {
            m_fr.set(ControlMode.PercentOutput, (y + x) * z);
            m_br.set(ControlMode.PercentOutput, (y + x) * z);
            m_fl.set(ControlMode.PercentOutput, -(y - x) * z);
            m_bl.set(ControlMode.PercentOutput, -(y - x) * z);
        } else {
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
        System.out.println("inverting");
        inverted = !inverted;
    }
}
