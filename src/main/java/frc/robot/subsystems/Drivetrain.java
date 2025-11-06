package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    // TODO: Declare four SparkMax motor objects



    // TODO: Declare encoder objects for left and right leader motors



    // TODO: Declare a DifferentialDrive object (Hint: it takes left + right leader motors)


    public Drivetrain() {

        // TODO: Initialize SparkMax objects using CAN IDs from Constants
        // (Use MotorType.kBrushless)


        // TODO: Configure each motor (write a helper method below to avoid copy/paste)
        //  - Set idle mode to brake
        //  - Set current limit (35-45A recommended)
        //  - Set motor inversion (right side should be inverted)


        // TODO: Make leftB follow leftA
        // TODO: Make rightB follow rightA


        // TODO: Create encoder objects from each leader motor
        // leftEncoder = leftA.getEncoder();
        // rightEncoder = rightA.getEncoder();


        // TODO: Set encoder conversion factors
        // Hint: setPositionConversionFactor and setVelocityConversionFactor
        // same for rightEncoder


        // TODO: Initialize DifferentialDrive
    }


    // TODO: Write a method "tankDrive(double leftSpeed, double rightSpeed)"
    // This method should call drive.tankDrive(...)
    // DO NOT directly call motor.set() here


    // TODO: Create accessor methods:
    // public double getLeftDistanceMeters() 
    // public double getRightDistanceMeters() 


    @Override
    public void periodic() {
        // TODO: Send encoder data to SmartDashboard
    }


    // OPTIONAL:
    // Write helper method to set brake/coast modes dynamically
    // Write method to stop drivetrain (set speeds to 0)
}
