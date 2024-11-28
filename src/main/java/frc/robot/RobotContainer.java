import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
public class RobotContainer {
  // The robot's subsystems
  private final ArcadeDrive m_robotDrive = new ArcadeDrive();
  private final Command m_Auto = Commands.simpleAuto(m_robotDrive);
  SendableChooser<Command> m_chooser = new SendableChooser<>();
    public RobotContainer(){
        m_robotDrive.setDefaultCommand(
            // A split-stick arcade command, with forward/backward controlled by the left
            // hand, and turning controlled by the right.
            Commands.run(
                () ->
                    m_robotDrive.arcadeDrive(
                        -m_driverController.getLeftY(), -m_driverController.getRightX()),
                m_robotDrive));

        // Add commands to the autonomous command chooser
        m_chooser.setDefaultOption("Simple Auto", m_Auto);
        Shuffleboard.getTab("Autonomous").add(m_chooser);
        Shuffleboard.getTab("Drivetrain").add(m_robotDrive);


        CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
        CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));
        CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));
    }
    
    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }


    
}