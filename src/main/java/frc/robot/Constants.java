// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static final int
    id_stick = 0,
    idDT_bl = 1,
    idDT_br = 3,
    idDT_fl = 10,
    idDT_fr = 2,
    idSh_shooter = 12, //7
    idCl_climber = 8;
  
  public static final double
    pidMulti_TESTING = 50;
    //pidMulti_ARM = 50; otherwise 1

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
