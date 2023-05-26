package frc.robot.commands;



import java.util.Map;

import org.chsrobotics.lib.drive.differential.ArcadeDrive;
import org.chsrobotics.lib.drive.differential.CurvatureDrive;
import org.chsrobotics.lib.drive.differential.DifferentialDriveMode;
import org.chsrobotics.lib.drive.differential.DifferentialDrivetrainInput;
import org.chsrobotics.lib.drive.differential.MixedDrive;
import org.chsrobotics.lib.drive.differential.TankDrive;
import org.chsrobotics.lib.input.JoystickAxis;
import org.chsrobotics.lib.telemetry.DashboardChooser;
import org.chsrobotics.lib.telemetry.HighLevelLogger;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Config;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopControl extends CommandBase {


    private final XboxController driverController = new XboxController(0); 
   
    private final Drivetrain drivetrain;
    private final Shooter shooter;
    private final double driveModifier;
    boolean lastbButton = false;
    boolean isShooting = false;

    private final Timer timer = new Timer();

    public TeleopControl(Drivetrain drivetrain, Shooter shooter, double driveModifier) {
    this.drivetrain = drivetrain;
    this.shooter = shooter;
    this.driveModifier = driveModifier;
    }

    @Override
    public void initialize() {
      timer.start();
    }

    @Override
    public void execute() {
      boolean aButton = driverController.getAButton();
      boolean bButton = driverController.getBButton();
      boolean yButton = driverController.getYButton();
      boolean rightBumper = driverController.getRightBumper();
      boolean leftBumper = driverController.getLeftBumper();
      



      // drivetrain.setOutput(driveMode.execute());
        
      double turn = driverController.getRightX();
      double forward = driverController.getLeftY();
      if (turn >0){
        drivetrain.setOutput(forward*driveModifier, (forward+turn)*driveModifier);
      } else {
        drivetrain.setOutput((forward-turn)*driveModifier,forward*driveModifier);
      }
            
      if (rightBumper == true){
        drivetrain.setOutput(-1*driveModifier, 1*driveModifier);
      } else if (leftBumper == true){
        drivetrain.setOutput(1*driveModifier, -1*driveModifier);
      }
    

      if(yButton == true) {
        shooter.setIndexerVoltage(-11);
      } else if (yButton == false) {
        shooter.setIndexerVoltage(0);
      }
      SmartDashboard.putBoolean("bButton", bButton);
      SmartDashboard.putBoolean("lastbButton", lastbButton);
      if (bButton == true && lastbButton == false) {
        if (!isShooting) {
          isShooting = true;
          timer.restart();
          SmartDashboard.putString("Button", "pressed");
        }
      }
      lastbButton = bButton;
      SmartDashboard.putNumber("Timer", timer.get());
      if (isShooting) {
        shooter.setFlywheelOutput(12);
        shooter.setIndexerVoltage(12);
        if (shooter.getLowerBreakbeam()){
          shooter.setIndexerVoltage(0);
        }
        if (timer.get() >= 2.6){
          isShooting = false;
          shooter.setFlywheelOutput(0);
        }
      }

    
    }
  }

