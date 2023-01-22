package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonFX
        m_bl = new TalonFX(idDT_bl),
        m_br = new TalonFX(idDT_br),
        m_fr = new TalonFX(idDT_fr),
        m_fl = new TalonFX(idDT_fl);

    public void drive(double x, double y, double z) {
        m_bl.set(ControlMode.PercentOutput, (y + x) * z);
        m_fl.set(ControlMode.PercentOutput, (y + x) * z);
        m_br.set(ControlMode.PercentOutput, -(y - x) * z);
        m_fr.set(ControlMode.PercentOutput, -(y - x) * z);
    }
}
