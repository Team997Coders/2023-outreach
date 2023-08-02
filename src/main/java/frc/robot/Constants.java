// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  public static final class Drivetrain {
      public static int FRONT_RIGHT_CAN_ID = 2;
      public static int BACK_RIGHT_CAN_ID = 4;
      public static int FRONT_LEFT_CAN_ID = 1;
      public static int BACK_LEFT_CAN_ID = 3;
      public static boolean INVERT_LEFT = true;
      public static boolean INVERT_RIGHT = false;

      public static int LINEAR_RAMP_RATE = 10;
      public static int ANGULAR_RAMP_RATE = 10;
  }
  public static int DRIVE_CONTROLLER_PORT = 0;

  public static final class Shooter {
    public static int TOP_FLYWHEEL_CAN_ID = 5;
    public static int BOTTOM_FLYWHEEL_CAN_ID = 6;
    public static int HOOD_CAN_ID = 7;

    public static int INDEXER_GATE_CAN_ID = 12;

    public static boolean INDEXER_GATE_IS_INVERTED = true;
    public static boolean FLYWHEEL_IS_INVERTED = true;

    public static int UPPER_BREAKBEAM_DIO = 0;
    public static int LOWER_BREAKBEAM_DIO = 1;
    public static int HOOD_SWITCH_DIO = 6;

    public static int[] HOOD_ENCODER_DIO = {7, 8};

    public static final FeedbackDevice HOOD_FEEDBACK_DEVICE = FeedbackDevice.QuadEncoder;
  }





}
