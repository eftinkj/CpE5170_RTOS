package mst.lejos.cpe5170.rtos_gt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.Math;





import lejos.nxt.*;
import lejos.nxt.comm.*;
import lejos.robotics.*;
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

public class RTOS_GT //implements CommandPerformer//
{
	
	static NXTRegulatedMotor steeringMotor = Motor.A;
	static NXTRegulatedMotor driveMotor_Driver = Motor.B;
	static NXTRegulatedMotor driveMotor_Passen = Motor.C;
	public final static int leftMax = -105;
	public final static int rightMax = 105;
	public final static float width = 12.5f; //12.5 cm
	public final static float length = 17.5f; //17.5 cm
	static int steeringDegree = 0;
	static int driveSpeed = 600;
	static boolean differentialFlag = true;
	static int turnThreshold = 20;
	static int[] accelConstants = {0,1,1,2,3,5,9,15,25,40,67,110,181,299,493,812,1339,2207,3639,6000,9892};
	static float[] speedConstants = {1000,600,364,220,134,81,49,30,18,11,6.7f,4,2.5f,1.5f,0.9f,0.5f,0.3f,0.2f,0.1f,0.1f,0};
	static int powerPointer = 0;
	
	public static void main(String[] args) throws Exception 
	{
		//I tried to create a calibration system, but the gears slip too much
		driveMotor_Driver.setSpeed(1000);
		driveMotor_Passen.setSpeed(1000);
	
	
		while(!Button.ESCAPE.isDown()) {
			
			powerTest(accelConstants[powerPointer]);
			driveMotor_Driver.backward();
			driveMotor_Passen.backward();
			Thread.sleep(500);
			LCD.drawInt(accelConstants[powerPointer], 0, 1);
			powerPointer++;
			if (accelConstants[powerPointer] > 6000)
			{
				powerPointer = 0;
			}
					
			//steeringDegree = -10;
			//steeringMotor.rotateTo(steeringDegree);
			//drive(driveSpeed);
			//Thread.sleep(500);
			//steeringDegree = 10;
			//steeringMotor.rotateTo(steeringDegree);
			//drive(driveSpeed);
			//Thread.sleep(500);
			//LCD.clear();
			//LCD.drawString("Degrees", 0, 0);
			//LCD.drawInt(steeringMotor.getTachoCount(), 0, 1);
		}
		steeringMotor.rotateTo(0);
		driveMotor_Driver.setAcceleration(6000);
		driveMotor_Passen.setAcceleration(6000);
		driveMotor_Driver.stop();
		driveMotor_Passen.stop();
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
	//This section of code is heavily influenced by "Vehicle safety systems
	//	implemented on a LEGO car" by J. Wortmann Et. al.	
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
	
	public static void powerTest(int powerConst)
	{
		driveMotor_Driver.setAcceleration(powerConst);
		driveMotor_Passen.setAcceleration(powerConst);
		driveMotor_Driver.setSpeed(powerConst);
		driveMotor_Passen.setSpeed(powerConst);
		
	}

//	@Override
//	public void performCommand(int commandNr, byte[] parameter) {
//		switch (commandNr)
//		case
		
		// TODO Auto-generated method stub
		
//	}
	
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

