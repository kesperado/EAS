package data.teachcoursedata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.teachcoursedataservice.TeachCourseDataService;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是教师授课数据操作，主要对数据库中的teachcoure表进行增删改查
 */
public class TeachCourseData implements TeachCourseDataService{

	@Override
	public boolean addCourserTeacher(String courseid, String teacherid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into teachcourse values(?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, teacherid);
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
	public boolean deleteCourserTeacher(String courseid, String teacherid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from teachcourse where courseid= ? and teacherid= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, teacherid);
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
	public ArrayList<String> showCourseList(String teacherid) {
		ArrayList<String> courselist = new ArrayList<String>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teachcourse where teacherid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				courselist.add(resultSet.getString("courseid"));
			}
			return courselist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public ArrayList<String> showrTeacherList(String courseid) {
		ArrayList<String> teacherlist = new ArrayList<String>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teachcourse where courseid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherlist.add(resultSet.getString("teacherid"));
			}
			return teacherlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

}
