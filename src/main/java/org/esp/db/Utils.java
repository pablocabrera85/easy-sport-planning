package org.esp.db;

import org.esp.core.model.User;
import org.esp.core.model.trainning.Plan;

public class Utils {

	public static String getPlanPath(User user,Plan plan){
		StringBuilder builder = new StringBuilder();
		builder.append(","+user.getEmail()+",");
		Plan current = plan;
		while(current!=null){
			builder.append(current.getName()+",");
			if(current.getPlans().isEmpty()){
				current=null;
			}else{
				current = current.getPlans().get(0);
			}
		}
		return builder.toString();
	}
	
	public static DAOPlan getPlanData(Plan plan){
		Plan current = plan;
		while(current!=null){
			if(current.getPlans().isEmpty()){
				return DAOPlan.fromPlan(current);
			}else{
				current = current.getPlans().get(0);
			}
		}
		return null;
	}

	public static boolean isParentPath(String path,String parent) {
		
		String[] childPaths = path.split(",");
		
		String[] parentPaths = parent.split(",");
		boolean first=path.indexOf(parent)==0;
		boolean second =(childPaths.length== parentPaths.length+1);
		return first && second;
	}
}
