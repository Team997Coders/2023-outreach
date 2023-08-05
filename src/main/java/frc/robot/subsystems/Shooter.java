package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
  
    private final CANSparkMax flywheel = new CANSparkMax(Constants.Shooter.TOP_FLYWHEEL_CAN_ID, MotorType.kBrushless);
    private final WPI_VictorSPX indexerGate = new WPI_VictorSPX(Constants.Shooter.INDEXER_GATE_CAN_ID);
    private final TalonSRX hood = new TalonSRX(Constants.Shooter.HOOD_CAN_ID);

    private final DigitalInput hoodSwitch = new DigitalInput(Constants.Shooter.HOOD_SWITCH_DIO);
    private final DigitalInput upperBreakbeam = new DigitalInput(Constants.Shooter.UPPER_BREAKBEAM_DIO);
    private final DigitalInput lowerBreakBeam = new DigitalInput(Constants.Shooter.LOWER_BREAKBEAM_DIO);

    private final RelativeEncoder flyWheelEncoder = flywheel.getEncoder();
    private final Encoder hoodEncoder = new Encoder(Constants.Shooter.HOOD_ENCODER_DIO[0], Constants.Shooter.HOOD_ENCODER_DIO[1]);

    public Shooter() {
        flywheel.setInverted(Constants.Shooter.FLYWHEEL_IS_INVERTED);
        indexerGate.setInverted(Constants.Shooter.INDEXER_GATE_IS_INVERTED);
        hood.configSelectedFeedbackSensor(Constants.Shooter.HOOD_FEEDBACK_DEVICE);
        hoodEncoder.reset();
    }

    public void setIndexerVoltage(double voltage) {
        indexerGate.setVoltage(voltage);
    }

    public void setFlywheelOutput(double voltage) {
        flywheel.setVoltage(voltage);
    }

    public double getFlywheelSpeed() {
        return flyWheelEncoder.getVelocity();
    }

    public boolean getUpperBreakbeam() {
        return !upperBreakbeam.get();
    }

    public boolean getLowerBreakbeam() {
        return !lowerBreakBeam.get();
    }

    public void setHoodOutput(double output) {
        hood.set(ControlMode.PercentOutput, output);
    }
 
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("UpperBreakbeam", getUpperBreakbeam());
        SmartDashboard.putBoolean("LowerBreakbeam", getLowerBreakbeam());
        SmartDashboard.putNumber("ShooterSpeed", getFlywheelSpeed());
        SmartDashboard.putBoolean("hood switch", hoodSwitch.get());
    }
    
    
}
