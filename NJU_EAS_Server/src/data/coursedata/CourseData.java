package data.coursedata;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.coursedataservice.CourseDataService;
import po.CoursePO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是课程数据操作类，包含了对数据库中课程表的增删改查
 */
public class CourseData implements CourseDataService{

	@Override
	public String getMaxCourseID(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select max(id) as maxid from course";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return resultSet.getString("maxid");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}
	@Override
	public boolean publishCoures(CoursePO coursePO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into course(id,name,credit,grade,targetdepartment,department,coursetype) values(?,?,?,?,?,?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, coursePO.getID());
			preparedStatement.setString(2, coursePO.getName());
			preparedStatement.setInt(3, coursePO.getCredit());
			preparedStatement.setString(4,coursePO.getGrade());
			preparedStatement.setString(5,coursePO.getTargetDepartment());
			preparedStatement.setString(6,coursePO.getDepartment());
			preparedStatement.setString(7,coursePO.getCourseType());
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return false;
	}

	@Override
	public boolean deleteCoures(String courseid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from course where id = ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,courseid);
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
	public boolean updateCoures(CoursePO coursePO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update course set time=?,classroom=?,memberlimit=?,outline=?,textbook=?,referencebook=? ,testtime=? ,canselect=? ,canquit=? where id =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, coursePO.getTime());
			preparedStatement.setString(2, coursePO.getClassroom());
			preparedStatement.setInt(3, coursePO.getMemberLimit());
			preparedStatement.setString(4, coursePO.getOutline());
			preparedStatement.setString(5,coursePO.getTextbook());
			preparedStatement.setString(6, coursePO.getReferenceBook());
			preparedStatement.setString(7, coursePO.getTestTime());
			preparedStatement.setString(8, coursePO.getCanSelect());
			preparedStatement.setString(9, coursePO.getCanQuit());
			preparedStatement.setString(10, coursePO.getID());
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
	public CoursePO queryCourseByID(String courseid) {
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course where id =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, courseid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
			}
			return coursePO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}
	
	@Override
	public ArrayList<CoursePO> queryCourseByType(String coursetype) {
		ArrayList<CoursePO> courselist = new ArrayList<CoursePO>();
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course where coursetype=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, coursetype);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
				courselist.add(coursePO);
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
	public ArrayList<CoursePO> queryCourseByDepartment(String department) {
		ArrayList<CoursePO> courselist = new ArrayList<CoursePO>();
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course where department=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
				courselist.add(coursePO);
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
	public ArrayList<CoursePO> queryCourseByGradeAndDepartment(String grade, String department) {
		ArrayList<CoursePO> courselist = new ArrayList<CoursePO>();
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course where grade = ? and department = ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2, department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
				courselist.add(coursePO);
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
	public ArrayList<CoursePO> queryCourseByGradeDepartmentAndType(String grade, String department,
			String coursetype) {
		ArrayList<CoursePO> courselist = new ArrayList<CoursePO>();
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course where grade = ? and department = ? and coursetype= ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2, department);
			preparedStatement.setString(3, coursetype);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
				courselist.add(coursePO);
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
	public ArrayList<CoursePO> showAllCourses() {
		ArrayList<CoursePO> courselist = new ArrayList<CoursePO>();
		CoursePO coursePO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from course ";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coursePO=new CoursePO();
				coursePO.setID(resultSet.getString("id"));
				coursePO.setName(resultSet.getString("name"));
				coursePO.setCredit(resultSet.getInt("credit"));
				coursePO.setTime(resultSet.getString("time"));
				coursePO.setClassroom(resultSet.getString("classroom"));
				coursePO.setMemberLimit(resultSet.getInt("memberlimit"));
				coursePO.setOutline(resultSet.getString("outline"));
				coursePO.setTextbook(resultSet.getString("textbook"));
				coursePO.setReferenceBook(resultSet.getString("referencebook"));
				coursePO.setTestTime(resultSet.getString("testtime"));
				coursePO.setGrade(resultSet.getString("grade"));
				coursePO.setTargetDepartment(resultSet.getString("targetdepartment"));
				coursePO.setDepartment(resultSet.getString("department"));
				coursePO.setCourseType(resultSet.getString("coursetype"));
				coursePO.setCanSelect(resultSet.getString("canselect"));
				coursePO.setCanQuit(resultSet.getString("canquit"));
				courselist.add(coursePO);
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


}
