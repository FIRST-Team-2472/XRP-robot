// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.XRPDrivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TankdriveCommand extends Command {
    XRPDrivetrain drivetrain;
    Supplier<Double> leftY, rightX;
    Supplier<Boolean> aPressed, bPressed;
    double speed, speedMultiplier = 1;
    double zAngle;

  public TankdriveCommand(XRPDrivetrain drivetrain, Supplier<Double> rightX, Supplier<Double> leftY,  Supplier<Boolean> aPressed, Supplier<Boolean> bPressed) {
    this.drivetrain = drivetrain;
    this.leftY = leftY;
    this.rightX = rightX;
    this.aPressed = aPressed;
    this.bPressed = bPressed;
    
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(aPressed.get() && !bPressed.get()) {
        speedMultiplier = 0.5  ; // younger kid speed
    } else if (bPressed.get()) {
        speedMultiplier = 1; // older kid speed
    }
    speed = speedMultiplier * leftY.get();
    zAngle = rightX.get();
    if (rightX.get() != 0 && leftY.get() != 0) {
        drivetrain.arcadeDrive(speed, zAngle);
        System.out.println("speed +" + speed + ", zAngle "+ zAngle);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    speed = 0;
    zAngle = 0;
    drivetrain.arcadeDrive(speed, zAngle);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
