
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
public static Command simpleAuto(ArcadeDrive drive) {
    return  Commands.sequence(
    
        new FunctionalCommand(
            private final Timer m_timer = new Timer();
            m_timer.startTimer();
            // Reset encoders on command start
            drive::resetEncoders,
            // Drive forward while the command is executing
            () -> drive.arcadeDrive(0.7, 0),
            // Stop driving at the end of the command
            interrupt -> drive.arcadeDrive(0, 0),
            // End the command when the robot's driven distance exceeds the desired value
            () -> m_timer.getTime() >= 15000,
            // Require the drive subsystem
            drive;
            m_timer.stop(););
        new FunctionalCommand(
            m_timer.reset(0);
            m_timer.startTimer();
            // Reset encoders on command start
            drive::resetEncoders,
            // Drive backward while the command is executing
            () -> drive.arcadeDrive(0, 0.7),
            // Stop driving at the end of the command
            interrupt -> drive.arcadeDrive(0, 0),
            // End the command when the robot's driven distance exceeds the desired value
            () -> m_timer.getTime() >= 1500 //needs to be a tuned value -> encoders are not assumed on the CAN Spark max controller (I might be wrong about this)
            m_timer.stop();

            // Require the drive subsystem
            drive);
    );
}