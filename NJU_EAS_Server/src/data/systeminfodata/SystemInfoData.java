package data.systeminfodata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.systeminfodataservice.SystemInfoDataService;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教学计划数据操作，主要对数据库中的systeminfo和systemnotice表进行增删改查
 */
public class SystemInfoData implements SystemInfoDataService{

	@Override
	public boolean inputStructure(String structure) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update systeminfo set content=? where item=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, structure);
			preparedStatement.setString(2, "structure");
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return false;
	}

	@Override
	public String showStructure() {
		String structure =null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from systeminfo where item=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, "structure");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				structure=resultSet.getString("content");
			}
			return structure;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public boolean setSystemStatus(String status) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update systeminfo set content=? where item=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, "status");
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return false;
	}

	@Override
	public String getSystemStatus() {
		String structure =null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from systeminfo where item=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, "status");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				structure=resultSet.getString("content");
			}
			return structure;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public boolean addNotice(String userid, String notice) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into systemnotice values(?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, notice);
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return false;
	}

	@Override
	public boolean deleteNotice(String userid, String notice) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from systemnotice where userid = ? and notice=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,userid);
			preparedStatement.setString(2,notice);
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
	public ArrayList<String> getUserNotices(String userid) {
		ArrayList<String> notices=new ArrayList<String>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from systemnotice where userid = ? ";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				notices.add(resultSet.getString("notice"));
			}
			return notices;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public boolean emptyNoticeList() {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from systemnotice where userid>0";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
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

	
}
