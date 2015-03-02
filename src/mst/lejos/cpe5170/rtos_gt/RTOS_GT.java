package mst.lejos.cpe5170.rtos_gt;

import java.io.DataInputStream;
import java.io.DataOutputStream;







import lejos.robotics.RegulatedMotor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.addon.SensorSelector;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class RTOS_GT {
	
	static NXTRegulatedMotor steeringMotor = Motor.A;
	static NXTRegulatedMotor driveMotor_Driver = Motor.B;
	static NXTRegulatedMotor driveMotor_Passen = Motor.C;
	static int leftMax = -110;
	static int rightMax = 110;
	
	public static void main(String[] args) throws Exception 
	{
		//I tried to create a calibration system, but the gears slip too much
	
	
		while(!Button.ESCAPE.isDown()) {
			steeringMotor.rotateTo(leftMax);
			steeringMotor.rotateTo(rightMax);
			driveMotor_Driver.stop();
			driveMotor_Passen.flt();
			//LCD.clear();
			//LCD.drawString("Degrees", 0, 0);
			//LCD.drawInt(steeringMotor.getTachoCount(), 0, 1);
			//Thread.sleep(500);
		}
		steeringMotor.rotateTo(0);
	}
	
	public void elecDifferential(){
		
	}
	
}

