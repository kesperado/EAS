package data.plandata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.plandataservice.PlanDataService;
import po.PlanPO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教学计划数据操作，主要对数据库中的plan表进行增删改查
 */
public class PlanData implements PlanDataService{
	
	@Override
	public boolean updatePlan(PlanPO planPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update department set plan=?,inadmit=?, outadmit=? where name =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, planPO.getDetails());
			preparedStatement.setInt(2, planPO.getInadmit());
			preparedStatement.setInt(3, planPO.getOutadmit());
			preparedStatement.setString(4, planPO.getDepartment());
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return false;
	}

	@Override
	public PlanPO findPlan(String department) {
		PlanPO planPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from department where name =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				planPO=new PlanPO();
				planPO.setDetails(resultSet.getString("plan"));
				planPO.setInadmit(resultSet.getInt("inadmit"));
				planPO.setOutadmit(resultSet.getInt("outadmit"));
				
			}
			return planPO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public ArrayList<PlanPO> showPlans() {
		ArrayList<PlanPO> planlist = new ArrayList<PlanPO>();
		PlanPO planPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from department ";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				planPO=new PlanPO();
				planPO.setDetails(resultSet.getString("plan"));
				planPO.setInadmit(resultSet.getInt("inadmit"));
				planPO.setInadmit(resultSet.getInt("outadmit"));
				planlist.add(planPO);
			}
			return planlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
		
	}

}
