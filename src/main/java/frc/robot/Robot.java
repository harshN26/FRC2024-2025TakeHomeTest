package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;



public class Robot extends TimedRobot {
  ArcadeDrive drive=new ArcadeDrive(0,1,2,3);
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();
  private final Timer m_timer = new Timer(1);
  RobotContainer m_robotContainer;
  private Command m_autonomousCommand;

  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);
  Compressor compressor=new Compressor(PneumaticsModuleType.REVPH);

  
  @Override

  public Robot(){
    m_robotContainer=new RobotContainer();
  }
  public void robotInit() {
     drive.initRobotDrive();
     compressor.initSendable();
  }
  public void robotPeriodic() {
     CommandScheduler.getInstance.run();
  }
  public autoInit(){
      m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  public autoPeriodic(){
      
  }
  public teleOpInit(){
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    drive.arcadeDrive(-m_stick.getY(), -m_stick.getX());

    m_solenoid.set(m_stick.getRawButton(1));
  }
}

