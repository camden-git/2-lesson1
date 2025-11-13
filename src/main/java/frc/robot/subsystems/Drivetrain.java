package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    private final SparkMax leftA;
    private final SparkMax leftB;
    private final SparkMax rightA;
    private final SparkMax rightB;

    private final RelativeEncoder leftEncoder;
    private final RelativeEncoder rightEncoder;

    private final DifferentialDrive drive;

    public Drivetrain() {
        leftA = new SparkMax(1, MotorType.kBrushless);
        leftB = new SparkMax(2, MotorType.kBrushless);
        rightA = new SparkMax(3, MotorType.kBrushless);
        rightB = new SparkMax(4, MotorType.kBrushless);

        // NOTE: follow is now part of configureMotor()
        configureMotor(leftA, false, null);
        configureMotor(leftB, false, leftA);

        configureMotor(rightA, true, null);
        configureMotor(rightB, true, rightA);

        leftEncoder = leftA.getEncoder();
        rightEncoder = rightA.getEncoder();

        drive = new DifferentialDrive(leftA, rightA);
    }

    private void configureMotor(SparkMax motor, boolean invert, SparkBase followLeader) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40);
        config.inverted(invert);
        config.idleMode(IdleMode.kBrake);

        // ✅ Follow Leader
        if (followLeader != null) {
            config.follow(followLeader);
        }

        // ✅ Encoder Conversion Factors *must be set here*
        double wheelDiameterMeters = 0.1016; // 4" wheel
        double wheelCircumference = Math.PI * wheelDiameterMeters;
        double gearReduction = 6.75;

        config.encoder.positionConversionFactor(wheelCircumference / gearReduction);
        config.encoder.velocityConversionFactor((wheelCircumference / gearReduction) / 60.0);

        motor.configure(config, null, null);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public double getLeftDistanceMeters() { return leftEncoder.getPosition(); }
    public double getRightDistanceMeters() { return rightEncoder.getPosition(); }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drive/LeftDistance", getLeftDistanceMeters());
        SmartDashboard.putNumber("Drive/RightDistance", getRightDistanceMeters());
        SmartDashboard.putNumber("Drive/LeftVelocity", leftEncoder.getVelocity());
        SmartDashboard.putNumber("Drive/RightVelocity", rightEncoder.getVelocity());
    }
}
