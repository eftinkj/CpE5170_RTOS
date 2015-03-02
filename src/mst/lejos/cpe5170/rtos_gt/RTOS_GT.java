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
	
	static RegulatedMotor steeringMotor = Motor.A;
	
	public static void main(String[] args) throws Exception 
	{
	
		
		//Note to self - Roughly -115 to 115 is our steering limits
	
	
		while(!Button.ESCAPE.isDown()) {
			steeringMotor.flt();
			LCD.clear();
			LCD.drawString("Degrees", 0, 0);
			LCD.drawInt(steeringMotor.getTachoCount(), 0, 1);
			Thread.sleep(500);
		}
	}	
}