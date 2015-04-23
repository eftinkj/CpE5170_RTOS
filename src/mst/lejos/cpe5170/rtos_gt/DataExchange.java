package mst.lejos.cpe5170.rtos_gt;
import java.util.concurrent.atomic.*;


public class DataExchange {
	
	private AtomicInteger steeringDegree = new AtomicInteger();
	private AtomicInteger driveSpeed = new AtomicInteger();
	private AtomicFloat diffRatio = new AtomicFloat();
	
	public DataExchange(){
	}
	
	public void setDriveSpeed(int speed){
		driveSpeed.set(speed);;
	}
	
	public void setDiffRatio(float ratio){
		diffRatio.set(ratio);
	}
	
	public float getDiffRatio(){
		return diffRatio.get();
	}
	
	public int getDriveSpeed(){
		return driveSpeed.get();
	}
	
	public void setSteeringDegree(int degree){
		steeringDegree.set(degree);;
	}
	
	public int getSteeringDegree(){
		return steeringDegree.get();
	}

}
