package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
  
    private final MotorControllerGroup flywheel = new MotorControllerGroup(new CANSparkMax(Constants.Shooter.TOP_FLYWHEEL_CAN_ID, MotorType.kBrushless), new CANSparkMax(Constants.Shooter.BOTTOM_FLYWHEEL_CAN_ID, MotorType.kBrushless));
    private final WPI_VictorSPX indexerGate = new WPI_VictorSPX(Constants.Shooter.INDEXER_GATE_CAN_ID);

    private final DigitalInput upperBreakbeam = new DigitalInput(Constants.Shooter.UPPER_BREAKBEAM_DIO);
    private final DigitalInput lowerBreakBeam = new DigitalInput(Constants.Shooter.LOWER_BREAKBEAM_DIO);

    public Shooter() {
        flywheel.setInverted(Constants.Shooter.FLYWHEEL_IS_INVERTED);
        indexerGate.setInverted(Constants.Shooter.INDEXER_GATE_IS_INVERTED);
    }

    public void setIndexerVoltage(double voltage) {
        indexerGate.set(voltage);
    }

    public void setFlywheelOutput(double speed) {
        flywheel.set(speed);
    }

    public double getFlywheelSpeed() {
        return flywheel.get();
    }

    public boolean getUpperBreakbeam() {
        return upperBreakbeam.get();
    }

    public boolean getLowerBreakbeam() {
        return lowerBreakBeam.get();
    }
 

    
    
}
