package mst.lejos.cpe5170.rtos_gt;

public class Differential extends Thread{
	private DataExchange DEObj;
	
	public Differential(DataExchange DE){
		DEObj = DE;
	}
	
	public void run(){
		while(true){
			if ((DEObj.getSteeringDegree() < turnThreshold) && (DEObj.getSteeringDegree() > -turnThreshold))
			{
				DEObj.setDiffSpeed(1.0f);
			}
			else
			{
				float pivotDistance = (float) (length / Math.tan(Math.abs(DEObj.getSteeringDegree())* Math.PI/180));
				float innerWheel = (float) (pivotDistance - width/2.0);
				float outerWheel = (float) (pivotDistance + width/2.0);
				DEObj.setDiffSpeed ((float) innerWheel/outerWheel);
			}
				}
					
			}
		}
	}

	
	
	
	
	
	
}
