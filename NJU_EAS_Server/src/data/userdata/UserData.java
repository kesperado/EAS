package data.userdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.databaseutility.DatabaseUtility;
import dataservice.userdataservice.UserDataService;
import po.StudentPO;
import po.TeacherPO;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是用户数据操作，主要对数据库中的user表进行增删改查
 */
public class UserData implements UserDataService{

	@Override
	public String getMaxTeacherID(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select max(id) as maxid from teacher";
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
	public String getMaxStudentID(){
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select max(id) as maxid from student";
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
	public boolean addTeacher(TeacherPO teacherPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into teacher(id,name,password,teachertype,department) values(?,?,?,?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherPO.getID());
			preparedStatement.setString(2, teacherPO.getName());
			preparedStatement.setString(3, teacherPO.getPassword());
			preparedStatement.setString(4, teacherPO.getTeacherType());
			preparedStatement.setString(5, teacherPO.getDepartment());
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
	public boolean deleteTeacher(String teacherid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from teacher where id = ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,teacherid);
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
	public boolean updateTeacher(TeacherPO teacherPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update teacher set age=?,hometown=?,contact=?,password=? where id=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherPO.getAge());
			preparedStatement.setString(2, teacherPO.getHometown());
			preparedStatement.setString(3, teacherPO.getContact());
			preparedStatement.setString(4, teacherPO.getPassword());
			preparedStatement.setString(5, teacherPO.getID());
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
	public ArrayList<TeacherPO> queryTeacherByTypeAndDepartment(String teachertype,String department){
		ArrayList<TeacherPO> teacherlist = new ArrayList<TeacherPO>();
		TeacherPO teacherPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teacher where teachertype=? and department =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teachertype);
			preparedStatement.setString(2,department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherPO=new TeacherPO();
				teacherPO.setID(resultSet.getString("id"));
				teacherPO.setName(resultSet.getString("name"));
				teacherPO.setPassword(resultSet.getString("password"));
				teacherPO.setTeacherType(resultSet.getString("teachertype"));
				teacherPO.setDepartment(resultSet.getString("department"));
				teacherPO.setAge(resultSet.getString("age"));
				teacherPO.setHometown(resultSet.getString("hometown"));
				teacherPO.setContact(resultSet.getString("contact"));
				teacherlist.add(teacherPO);
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
	
	
	@Override
	public ArrayList<TeacherPO> queryTeacherByType(String teachertype) {
		ArrayList<TeacherPO> teacherlist = new ArrayList<TeacherPO>();
		TeacherPO teacherPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teacher where teachertype=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teachertype);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherPO=new TeacherPO();
				teacherPO.setID(resultSet.getString("id"));
				teacherPO.setName(resultSet.getString("name"));
				teacherPO.setPassword(resultSet.getString("password"));
				teacherPO.setTeacherType(resultSet.getString("teachertype"));
				teacherPO.setDepartment(resultSet.getString("department"));
				teacherPO.setAge(resultSet.getString("age"));
				teacherPO.setHometown(resultSet.getString("hometown"));
				teacherPO.setContact(resultSet.getString("contact"));
				teacherlist.add(teacherPO);
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

	@Override
	public TeacherPO queryTeacherByID(String teacherid) {
		TeacherPO teacherPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teacher where id =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherPO=new TeacherPO();
				teacherPO.setID(resultSet.getString("id"));
				teacherPO.setName(resultSet.getString("name"));
				teacherPO.setPassword(resultSet.getString("password"));
				teacherPO.setTeacherType(resultSet.getString("teachertype"));
				teacherPO.setDepartment(resultSet.getString("department"));
				teacherPO.setAge(resultSet.getString("age"));
				teacherPO.setHometown(resultSet.getString("hometown"));
				teacherPO.setContact(resultSet.getString("contact"));

			}
			return teacherPO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}
	

	@Override
	public ArrayList<TeacherPO> queryTeacherByDepartment(String department) {
		ArrayList<TeacherPO> teacherlist = new ArrayList<TeacherPO>();
		TeacherPO teacherPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teacher where department=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherPO=new TeacherPO();
				teacherPO.setID(resultSet.getString("id"));
				teacherPO.setName(resultSet.getString("name"));
				teacherPO.setPassword(resultSet.getString("password"));
				teacherPO.setTeacherType(resultSet.getString("teachertype"));
				teacherPO.setDepartment(resultSet.getString("department"));
				teacherPO.setAge(resultSet.getString("age"));
				teacherPO.setHometown(resultSet.getString("hometown"));
				teacherPO.setContact(resultSet.getString("contact"));
				teacherlist.add(teacherPO);
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
	
	@Override
	public ArrayList<TeacherPO> showAllTeachers() {
		ArrayList<TeacherPO> teacherlist = new ArrayList<TeacherPO>();
		TeacherPO teacherPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from teacher ";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				teacherPO=new TeacherPO();
				teacherPO.setID(resultSet.getString("id"));
				teacherPO.setName(resultSet.getString("name"));
				teacherPO.setPassword(resultSet.getString("password"));
				teacherPO.setTeacherType(resultSet.getString("teachertype"));
				teacherPO.setDepartment(resultSet.getString("department"));
				teacherPO.setAge(resultSet.getString("age"));
				teacherPO.setHometown(resultSet.getString("hometown"));
				teacherPO.setContact(resultSet.getString("contact"));
				teacherlist.add(teacherPO);
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

	@Override
	public boolean addStudent(StudentPO studentPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "insert into student(id,name,password,grade,department)values(?,?,?,?,?)";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, studentPO.getID());
			preparedStatement.setString(2, studentPO.getName());
			preparedStatement.setString(3, studentPO.getPassword());
			preparedStatement.setString(4, studentPO.getGrade());
			preparedStatement.setString(5, studentPO.getDepartment());
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
	public boolean deleteStudent(String studentid) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "delete from student where id = ?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,studentid);
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
	public boolean updateStudent(StudentPO studentPO) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "update student set age=?,hometown=?,contact=?,password=? where id=?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, studentPO.getAge());
			preparedStatement.setString(2, studentPO.getHometown());
			preparedStatement.setString(3, studentPO.getContact());
			preparedStatement.setString(4, studentPO.getPassword());
			preparedStatement.setString(5, studentPO.getID());
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
	public StudentPO queryStudentByID(String studentid) {
		StudentPO studentPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from student where id =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, studentid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				studentPO=new StudentPO();
				studentPO.setID(resultSet.getString("id"));
				studentPO.setName(resultSet.getString("name"));
				studentPO.setPassword(resultSet.getString("password"));
				studentPO.setGrade(resultSet.getString("grade"));
				studentPO.setDepartment(resultSet.getString("department"));
				studentPO.setAge(resultSet.getString("age"));
				studentPO.setHometown(resultSet.getString("hometown"));
				studentPO.setContact(resultSet.getString("contact"));
			}
			return studentPO;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}
	
	@Override
	public ArrayList<StudentPO> queryStudentByGradeAndDepartment(String grade,String department){
		ArrayList<StudentPO> studentlist = new ArrayList<StudentPO>();
		StudentPO studentPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from student where grade=? and department =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2,department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				studentPO=new StudentPO();
				studentPO.setID(resultSet.getString("id"));
				studentPO.setName(resultSet.getString("name"));
				studentPO.setPassword(resultSet.getString("password"));
				studentPO.setGrade(resultSet.getString("grade"));
				studentPO.setDepartment(resultSet.getString("department"));
				studentPO.setAge(resultSet.getString("age"));
				studentPO.setHometown(resultSet.getString("hometown"));
				studentPO.setContact(resultSet.getString("contact"));
				studentlist.add(studentPO);
			}
			return studentlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
		
	}


	@Override
	public ArrayList<StudentPO> queryStudentByDepartment(String department) {
		ArrayList<StudentPO> studentlist = new ArrayList<StudentPO>();
		StudentPO studentPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from student where department =?";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1,department);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				studentPO=new StudentPO();
				studentPO.setID(resultSet.getString("id"));
				studentPO.setName(resultSet.getString("name"));
				studentPO.setPassword(resultSet.getString("password"));
				studentPO.setGrade(resultSet.getString("grade"));
				studentPO.setDepartment(resultSet.getString("department"));
				studentPO.setAge(resultSet.getString("age"));
				studentPO.setHometown(resultSet.getString("hometown"));
				studentPO.setContact(resultSet.getString("contact"));
				studentlist.add(studentPO);
			}
			return studentlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}
	
	@Override
	public ArrayList<StudentPO> showAllStudents() {
		ArrayList<StudentPO> studentlist = new ArrayList<StudentPO>();
		StudentPO studentPO = null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select * from student";
		try {
			connection=DatabaseUtility.getConnection();
			preparedStatement =connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				studentPO=new StudentPO();
				studentPO.setID(resultSet.getString("id"));
				studentPO.setName(resultSet.getString("name"));
				studentPO.setPassword(resultSet.getString("password"));
				studentPO.setGrade(resultSet.getString("grade"));
				studentPO.setDepartment(resultSet.getString("department"));
				studentlist.add(studentPO);
			}
			return studentlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DatabaseUtility.free(resultSet, connection, preparedStatement);
		}
		return null;
	}



}
