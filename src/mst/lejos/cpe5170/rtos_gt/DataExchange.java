package mst.lejos.cpe5170.rtos_gt;

public class DataExchange {
	private int steeringDegree;
	private int driveSpeed;
	private float diffSpeed;
	
	public DataExchange(){
	}
	
	public void setDriveSpeed(int speed){
		driveSpeed = speed;
	}
	
	public void setDiffSpeed(float speed){
		diffSpeed = speed;
	}
	
	public float getDiffSpeed(){
		return diffSpeed;
	}
	
	public int getDriveSpeed(){
		return driveSpeed;
	}
	
	public void setSteeringDegree(int degree){
		steeringDegree = degree;
	}
	
	public int getSteeringDegree(){
		return steeringDegree;
	}

}
