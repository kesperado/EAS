package businesslogic.userbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.StudentPO;
import po.TeacherPO;
import vo.StudentVO;
import vo.TeacherVO;
import businesslogicservice.userblservice.UserBLService;
import data.userdata.UserData;
import dataservice.userdataservice.UserDataService;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是用户逻辑操作类，包含了增删改查教师、学生等这些操作
 */
public class UserBL extends UnicastRemoteObject implements UserBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	//注册教师
	public boolean RegisterTeacher(String name, String teachertype,
			String department) throws RemoteException {
		TeacherPO teacherPO = new TeacherPO(createTeacherID(), name, teachertype, department);
		UserDataService userDataService = new UserData();
		return userDataService.addTeacher(teacherPO);
	}

	@Override
	//注册学生
	public boolean RegisterStudent(String name, String grade, String department)
			throws RemoteException {
		StudentPO studentPO = new StudentPO(createStudentID(), name, grade, department);
		UserDataService userDataService = new UserData();
		return userDataService.addStudent(studentPO);
	}
	
	@Override
	//完善教师个人信息
	public boolean completeTeacherInfo(String teacherid,String age,String hometown,String contact)throws RemoteException{
		UserDataService userDataService = new UserData();
		TeacherPO teacherPO = userDataService.queryTeacherByID(teacherid);
		teacherPO.setAge(age);
		teacherPO.setHometown(hometown);
		teacherPO.setContact(contact);
		return userDataService.updateTeacher(teacherPO);
	}
	@Override
	//完善学生个人信息
	public boolean completeStudentInfo(String studentid,String age,String hometown,String contact)throws RemoteException{
		UserDataService userDataService = new UserData();
		StudentPO studentPO = userDataService.queryStudentByID(studentid);
		studentPO.setAge(age);
		studentPO.setHometown(hometown);
		studentPO.setContact(contact);
		return userDataService.updateStudent(studentPO);
	}
	@Override
	//修改教师密码
	public boolean changeTeacherPassword(String teacherid,String oldpassword,String newpassword)throws RemoteException{
		UserDataService userDataService = new UserData();
		TeacherPO teacherPO = userDataService.queryTeacherByID(teacherid);
		if(teacherPO.getPassword().equals(oldpassword)){
			teacherPO.setPassword(newpassword);
			return userDataService.updateTeacher(teacherPO);
		}else{
			return false;
		}
	}
	@Override
	//修改学生密码
	public boolean changeStudentPassword(String studentid,String oldpassword,String newpassword)throws RemoteException{
		UserDataService userDataService = new UserData();
		StudentPO studentPO = userDataService.queryStudentByID(studentid);
		if(studentPO.getPassword().equals(oldpassword)){
			studentPO.setPassword(newpassword);
			return userDataService.updateStudent(studentPO);
		}else{
			return false;
		}
	}
	

	@Override
	//教师登陆验证
	public boolean teacherLogin(String teacherid, String password,
			String teachertype) throws RemoteException {
		UserDataService userDataService = new UserData();
		TeacherPO teacherPO = userDataService.queryTeacherByID(teacherid);
		if((teacherPO!=null)&&(teacherPO.getPassword().equals(password))&&(teacherPO.getTeacherType().equals(teachertype))){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	//学生登陆验证
	public boolean studentLogin(String studentid, String password)
			throws RemoteException {
		UserDataService userDataService = new UserData();
		StudentPO studentPO = userDataService.queryStudentByID(studentid);
		if((studentPO!=null)&&(studentPO.getPassword().equals(password))){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	//删除教师
	public boolean deleteTeacher(String teacherid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return userDataService.deleteTeacher(teacherid);
	}

	@Override
	//删除学生
	public boolean deleteStudent(String studentid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return userDataService.deleteStudent(studentid);
	}

	@Override
	//查询教师
	public TeacherVO queryTeacherByID(String teacherid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return new TeacherVO(userDataService.queryTeacherByID(teacherid));
	}

	@Override
	//查询学生
	public StudentVO queryStudentByID(String studentid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return new StudentVO(userDataService.queryStudentByID(studentid));
	}

	@Override
	//通过教师种类和院系查询教师
	public ArrayList<TeacherVO> queryTeacherByTypeAndDepartment(
			String teachertype, String department) throws RemoteException {
		UserDataService userDataService = new UserData();
		ArrayList<TeacherPO> teacherPOs = userDataService.queryTeacherByTypeAndDepartment(teachertype, department);
		ArrayList<TeacherVO> teacherVOs = new ArrayList<TeacherVO>();
		for(TeacherPO teacherPO:teacherPOs){
			teacherVOs.add(new TeacherVO(teacherPO));
		}
		return teacherVOs;
	}

	@Override
	//通过教师种类查询教师
	public ArrayList<TeacherVO> queryTeacherByType(String teachertype)
			throws RemoteException {
		UserDataService userDataService = new UserData();
		ArrayList<TeacherPO> teacherPOs = userDataService.queryTeacherByType(teachertype);
		ArrayList<TeacherVO> teacherVOs = new ArrayList<TeacherVO>();
		for(TeacherPO teacherPO:teacherPOs){
			teacherVOs.add(new TeacherVO(teacherPO));
		}
		return teacherVOs;
	}

	@Override
	//通过学生年级和院系查询学生
	public ArrayList<StudentVO> queryStudentByGradeAndDepartment(String grade,
			String department) throws RemoteException {
		UserDataService userDataService = new UserData();
		ArrayList<StudentPO> studentPOs = userDataService.queryStudentByGradeAndDepartment(grade, department);
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		for(StudentPO studentPO:studentPOs){
			studentVOs.add(new StudentVO(studentPO));
		}
		return studentVOs;
	}
	

	@Override
	//通过院系查询教师
	public ArrayList<TeacherVO> queryTeacherByDepartment(String department)
			throws RemoteException {
		UserDataService userDataService = new UserData();
		ArrayList<TeacherPO> teacherPOs = userDataService.queryTeacherByDepartment(department);
		ArrayList<TeacherVO> teacherVOs = new ArrayList<TeacherVO>();
		for(TeacherPO teacherPO:teacherPOs){
			teacherVOs.add(new TeacherVO(teacherPO));
		}
		return teacherVOs;
	}

	@Override
	//通过院系查询学生
	public ArrayList<StudentVO> queryStudentByDepartment(String department)
			throws RemoteException {
		UserDataService userDataService = new UserData();
		ArrayList<StudentPO> studentPOs = userDataService.queryStudentByDepartment(department);
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		for(StudentPO studentPO:studentPOs){
			studentVOs.add(new StudentVO(studentPO));
		}
		return studentVOs;
	}
	
	//自动生成教师ID
	private String createTeacherID(){
		UserDataService userDataService = new UserData();
		String maxID = userDataService.getMaxTeacherID();
		int newTeacherID = Integer.parseInt(maxID)+1;
		return ""+newTeacherID;
	}
	
	//自动生成学生ID
	private String createStudentID(){
		UserDataService userDataService = new UserData();
		String maxID = userDataService.getMaxStudentID();
		int newStudentID = Integer.parseInt(maxID)+1;
		return ""+newStudentID;
	}


}
