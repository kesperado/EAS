package data.selectionrecorddata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.selectionrecorddataservice.SelectionRecordDataService;
import po.SelectionRecordPO;
import po.preSelectionRecordPO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是选课记录数据操作，主要对数据库中的selectionrecord表和preselectionrecord表进行增删改查
 */
public class SelectionRecordData implements SelectionRecordDataService{

	@Override
	public boolean addSelectionRecord(SelectionRecordPO selectionRecordPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into selectionrecord values(?,?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, selectionRecordPO.getCourseID());
			preparedStatement.setString(2, selectionRecordPO.getStudentID());
			preparedStatement.setInt(3, selectionRecordPO.getScore());
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
	public boolean deleteSelectionRecord(String courseid, String studentid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from selectionrecord where courseid= ? and studentid= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, studentid);
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
	public boolean updateSelectionRecord(SelectionRecordPO selectionRecordPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update selectionrecord set score =? where courseid =? and studentid = ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setInt(1, selectionRecordPO.getScore());
			preparedStatement.setString(2, selectionRecordPO.getCourseID());
			preparedStatement.setString(3, selectionRecordPO.getStudentID());
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
	public SelectionRecordPO findSelectionRecord(String courseid,String studentid){
		SelectionRecordPO selectionRecordPO =null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from selectionrecord where courseid=? and studentid= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, studentid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				selectionRecordPO = new SelectionRecordPO();
				selectionRecordPO.setCourseID(resultSet.getString("courseid"));
				selectionRecordPO.setStudentID(resultSet.getString("studentid"));
				selectionRecordPO.setScore(resultSet.getInt("score"));
			}
			return selectionRecordPO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
		
	}

	@Override
	public ArrayList<SelectionRecordPO> showCourseStudents(String courseid) {
		ArrayList<SelectionRecordPO> selectionRecordPOs = new ArrayList<SelectionRecordPO>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from selectionrecord where courseid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				SelectionRecordPO selectionRecordPO = new SelectionRecordPO();
				selectionRecordPO.setCourseID(resultSet.getString("courseid"));
				selectionRecordPO.setStudentID(resultSet.getString("studentid"));
				selectionRecordPO.setScore(resultSet.getInt("score"));
				selectionRecordPOs.add(selectionRecordPO);
			}
			return selectionRecordPOs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	
	@Override
	public ArrayList<SelectionRecordPO> showStudentCourses(String studentid) {
		ArrayList<SelectionRecordPO> selectionRecordPOs = new ArrayList<SelectionRecordPO>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from selectionrecord where studentid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, studentid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				SelectionRecordPO selectionRecordPO = new SelectionRecordPO();
				selectionRecordPO.setCourseID(resultSet.getString("courseid"));
				selectionRecordPO.setStudentID(resultSet.getString("studentid"));
				selectionRecordPO.setScore(resultSet.getInt("score"));
				selectionRecordPOs.add(selectionRecordPO);
			}
			return selectionRecordPOs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public boolean addpreSelectionRecord(
			preSelectionRecordPO preselectionRecordPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into preselectionrecord values(?,?,?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, preselectionRecordPO.getCourseID());
			preparedStatement.setString(2, preselectionRecordPO.getStudentID());
			preparedStatement.setString(3, preselectionRecordPO.getRank());
			preparedStatement.setInt(4, preselectionRecordPO.getMemberLimit());
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
	public boolean deletepreSelectionRecord(String courseid, String studentid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from preselectionrecord where courseid= ? and studentid= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, studentid);
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
	public boolean emptypreSeletionRecord(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from preselectionrecord where courseid>='0'";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
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
	public preSelectionRecordPO findpreSelectionRecord(String courseid,
			String studentid) {
		preSelectionRecordPO preselectionRecordPO =null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from preselectionrecord where courseid=? and studentid= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setString(2, studentid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				preselectionRecordPO = new preSelectionRecordPO();
				preselectionRecordPO.setCourseID(resultSet.getString("courseid"));
				preselectionRecordPO.setStudentID(resultSet.getString("studentid"));
				preselectionRecordPO.setRank(resultSet.getString("rank"));
				preselectionRecordPO.setMemberLimit(resultSet.getInt("memberlimit"));
			}
			return preselectionRecordPO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public ArrayList<preSelectionRecordPO> showCoursepreStudents(String courseid) {
		ArrayList<preSelectionRecordPO> preselectionRecordPOs = new ArrayList<preSelectionRecordPO>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from preselectionrecord where courseid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				preSelectionRecordPO preselectionRecordPO = new preSelectionRecordPO();
				preselectionRecordPO.setCourseID(resultSet.getString("courseid"));
				preselectionRecordPO.setStudentID(resultSet.getString("studentid"));
				preselectionRecordPO.setRank(resultSet.getString("rank"));
				preselectionRecordPO.setMemberLimit(resultSet.getInt("memberlimit"));
				preselectionRecordPOs.add(preselectionRecordPO);
			}
			return preselectionRecordPOs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public ArrayList<preSelectionRecordPO> showStudentpreCourses(String studentid) {
		ArrayList<preSelectionRecordPO> preselectionRecordPOs = new ArrayList<preSelectionRecordPO>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from preselectionrecord where studentid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, studentid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				preSelectionRecordPO preselectionRecordPO = new preSelectionRecordPO();
				preselectionRecordPO.setCourseID(resultSet.getString("courseid"));
				preselectionRecordPO.setStudentID(resultSet.getString("studentid"));
				preselectionRecordPO.setRank(resultSet.getString("rank"));
				preselectionRecordPO.setMemberLimit(resultSet.getInt("memberlimit"));
				preselectionRecordPOs.add(preselectionRecordPO);
			}
			return preselectionRecordPOs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}

	@Override
	public boolean addCourseAssessment(String courseid, int score) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into courseassess values(?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			preparedStatement.setInt(2, score);
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
	public ArrayList<Integer> showCourseAssessment(String courseid) {
		ArrayList<Integer> assessments = new ArrayList<Integer>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from courseassess where courseid=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				assessments.add(Integer.parseInt(resultSet.getString("score"))); 
			}
			return assessments;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}


}
