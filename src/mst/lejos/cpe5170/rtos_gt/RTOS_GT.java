package mst.lejos.cpe5170.rtos_gt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.Math;







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
	final static int leftMax = -105;
	final static int rightMax = 105;
	final static double width = 12.5; //12.5 cm
	final static double length = 17.5; //17.5 cm
	static int steeringDegree = 0;
	static int driveSpeed = 600;
	static boolean differentialFlag = true;
	static int turnThreshold = 20;
	
	public static void main(String[] args) throws Exception 
	{
		//I tried to create a calibration system, but the gears slip too much
	
	
		while(!Button.ESCAPE.isDown()) {
			steeringDegree = -10;
			steeringMotor.rotateTo(steeringDegree);
			drive(driveSpeed);
			Thread.sleep(500);
			steeringDegree = 10;
			steeringMotor.rotateTo(steeringDegree);
			drive(driveSpeed);
			Thread.sleep(500);
			//LCD.clear();
			//LCD.drawString("Degrees", 0, 0);
			//LCD.drawInt(steeringMotor.getTachoCount(), 0, 1);
		}
		steeringMotor.rotateTo(0);
		driveMotor_Driver.setSpeed(0);
		driveMotor_Passen.setSpeed(0);
	}
		
	public static float differential(){
		if ((steeringDegree < turnThreshold) && (steeringDegree > -turnThreshold))
				{
					return (float) 1.0;
				}
				else
				{
					float pivotDistance = (float) (length / Math.tan(Math.abs(steeringDegree)* Math.PI/180));
					float innerWheel = (float) (pivotDistance - width/2.0);
					float outerWheel = (float) (pivotDistance + width/2.0);
					return (float) (innerWheel/outerWheel);
				}
	//This section of code is heavily influenced by Vehicle safety systems
	//	implemented on a LEGO car by J. Wortmann Et. al.	
	}
	
	public static void drive(int driveSpeed)
	{
		if (differentialFlag)
		{
			float k = differential() * driveSpeed;
			if (steeringDegree >= 0)
			{
				driveMotor_Passen.setSpeed(driveSpeed);
				driveMotor_Driver.setSpeed(k);
			}
			else
			{
				driveMotor_Driver.setSpeed(driveSpeed);
				driveMotor_Passen.setSpeed(k);
			}
		}
		else
		{
			driveMotor_Driver.setSpeed(driveSpeed);
			driveMotor_Passen.setSpeed(driveSpeed);
		}
		driveMotor_Driver.backward(); //side effect of the motors
		driveMotor_Passen.backward();
		
	}
	
}




/*								|------>Differential----v	|----------> slipping w/ accel------v 	
 * 				start ----> Steering ---------------> accel/decel --------------------------> terminus
 * 															|----------> slipping w/ decel------^
 * 
 * 
 * 
 * 				start ----> bluetooth control receipt ---> terminus
 * 
 * 
 * 
 * 									|---------------------> Initiate brake--------v
 * 				start ----> collision detection -------------------------------> terminus
 * 
 * 
 */

