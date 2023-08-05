package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
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
      boolean rightTrigger = driverController.getRightTriggerAxis()>0.0;
      boolean leftTrigger = driverController.getLeftTriggerAxis()>0.0;
   


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
    

      if (aButton == true) {
        shooter.setFlywheelOutput(12);
        shooter.setIndexerVoltage(12);
      } else if (aButton == false) {
        shooter.setFlywheelOutput(0);
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
        if (timer.get() >= 2.7){
          isShooting = false;
          shooter.setFlywheelOutput(0);
          shooter.setIndexerVoltage(0);
          timer.reset();
          timer.stop();
        }
      }


      // if (leftTrigger== true ) {
      //   shooter.setHoodOutput(-0.1);
      // } else if (leftTrigger == false) {
      //   shooter.setHoodOutput(0.0);
      // } else if (rightTrigger == true) {
      //   shooter.setHoodOutput(0.1);
      // } else if (rightTrigger == false) {
      //   shooter.setHoodOutput(0.0);
      // }
     
    
    }
  }

