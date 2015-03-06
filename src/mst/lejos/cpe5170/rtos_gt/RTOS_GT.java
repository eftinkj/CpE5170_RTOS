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
	static int leftMax = -110;
	static int rightMax = 110;
	static float width = 14; //14 cm
	static float length = 22; //22 cm
	
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
		
	public float differential(){
		if ((motor.side == driveMotor_Driver && steering.degree >= 0) or (motor.side == driveMotor_Passen && steering.degree >= 0))
				{
					return (float) 1.0;
				}
				else	{
						float pivotDistance = length / tan(abs(steering.degree)* Math.PI/180);
					float innerWheel = (float) (pivotDistance - width/2.0);
	        float outerWheel = (float) (pivotDistance + width/2.0);
			return (float) (innerWheel/outerWheel);
		}
	
	
	}
	
}




/*								|------>Differential----v	|----------> slipping w/ accel------v 	
 * 				start ----> Steering ---------------> accel/decel --------------------------> terminus
 * 															|----------> slipping w/ decel------^
 * 
 * 				start ----> bluetooth control receipt ---> terminus
 * 
 * 									|---------------------> Initiate brake--------v
 * 				start ----> collision detection -------------------------------> terminus
 * 
 * 
 */

