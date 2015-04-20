package mst.lejos.cpe5170.rtos_gt;

public class DataExchange {
	private int steeringDegree;
	private int driveSpeed;
	private float diffRatio;
	
	public DataExchange(){
	}
	
	public void setDriveSpeed(int speed){
		driveSpeed = speed;
	}
	
	public void setDiffRatio(float ratio){
		diffRatio = ratio;
	}
	
	public float getDiffRatio(){
		return diffRatio;
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
