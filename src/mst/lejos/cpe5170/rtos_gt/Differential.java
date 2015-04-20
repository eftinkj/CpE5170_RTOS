package mst.lejos.cpe5170.rtos_gt;

public class Differential extends Thread{
	private DataExchange DEObj;
	
	public Differential(DataExchange DE){
		DEObj = DE;
	}
	
	public void run(){
		while(true){
			if ((DEObj.getSteeringDegree() < RTOS_Constants.turnThreshold) && (DEObj.getSteeringDegree() > -RTOS_Constants.turnThreshold))
			{
				DEObj.setDiffRatio(1.0f);
			}
			else
			{
				float pivotDistance = (float) (RTOS_Constants.length / Math.tan(Math.abs(DEObj.getSteeringDegree())* Math.PI/180));
				float innerWheel = (float) (pivotDistance - RTOS_Constants.width/2.0);
				float outerWheel = (float) (pivotDistance + RTOS_Constants.width/2.0);
				DEObj.setDiffRatio ((float) innerWheel/outerWheel);
			}
				}
					
			}
}

