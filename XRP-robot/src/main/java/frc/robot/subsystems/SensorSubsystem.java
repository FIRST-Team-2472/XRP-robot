package frc.robot.subsystems;
import  edu.wpi.first.wpilibj.xrp.*;

public class SensorSubsystem {
    public XRPRangefinder distanceSensor = new XRPRangefinder();

    public double getRangeInches() {
        return distanceSensor.getDistanceInches();
    }
    public double getRangeMeters() {
        return distanceSensor.getDistanceMeters();
    }
}
