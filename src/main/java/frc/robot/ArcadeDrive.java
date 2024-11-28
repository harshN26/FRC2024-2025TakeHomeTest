// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public final class ArcadeDrive {
    //assume that robot has two leading motors
   private CANSparkMax leftDriveLead;
   private CANSparkMax rightDriveLead;

   //assume that robot has two following motors
   private CANSparkMax leftDriveFollow;
   private CANSparkMax rightDriveFollow;

   //create differentail drive that does all important stuff on lead motors
   private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftDriveLead::set, rightDriveLead::set);

   //create time for auto
  


  public ArcadeDrive(int rLeadPort,int rFollowPort, int lLeadPort, int lFollowPort){
    leftDriveLead = new CANSparkMax(lLeadPort, MotorType.kBrushless);
    rightDriveLead = new CANSparkMax(rLeadPort, MotorType.kBrushless);
    leftDriveFollow = new CANSparkMax(lFollowPort, MotorType.kBrushless);
    rightDriveFollow = new CANSparkMax(rFollowPort, MotorType.kBrushless);
  }

  public static void initRobotDrive(){
    //reset all motors to factory defaults, set follower motors, and reverse right side
    leftDriveLead.restoreFactoryDefaults();
    rightDriveLead.restoreFactoryDefaults();
    leftDriveFollow.restoreFactoryDefaults();
    rightDriveFollow.restoreFactoryDefaults();
    
    leftDriveLead.addFollower(leftDriveFollower);
    rightDriveLead.addFollower(rightDriveFollower);

    rightDriveLead.setInverted(true)
  }
  public void teleopMoving(){
    m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
  }
}
