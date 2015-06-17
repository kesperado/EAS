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
 * @author ���� :knox
 * ��˵����
 * ������û��߼������࣬��������ɾ�Ĳ��ʦ��ѧ������Щ����
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
	//ע���ʦ
	public boolean RegisterTeacher(String name, String teachertype,
			String department) throws RemoteException {
		TeacherPO teacherPO = new TeacherPO(createTeacherID(), name, teachertype, department);
		UserDataService userDataService = new UserData();
		return userDataService.addTeacher(teacherPO);
	}

	@Override
	//ע��ѧ��
	public boolean RegisterStudent(String name, String grade, String department)
			throws RemoteException {
		StudentPO studentPO = new StudentPO(createStudentID(), name, grade, department);
		UserDataService userDataService = new UserData();
		return userDataService.addStudent(studentPO);
	}
	
	@Override
	//���ƽ�ʦ������Ϣ
	public boolean completeTeacherInfo(String teacherid,String age,String hometown,String contact)throws RemoteException{
		UserDataService userDataService = new UserData();
		TeacherPO teacherPO = userDataService.queryTeacherByID(teacherid);
		teacherPO.setAge(age);
		teacherPO.setHometown(hometown);
		teacherPO.setContact(contact);
		return userDataService.updateTeacher(teacherPO);
	}
	@Override
	//����ѧ��������Ϣ
	public boolean completeStudentInfo(String studentid,String age,String hometown,String contact)throws RemoteException{
		UserDataService userDataService = new UserData();
		StudentPO studentPO = userDataService.queryStudentByID(studentid);
		studentPO.setAge(age);
		studentPO.setHometown(hometown);
		studentPO.setContact(contact);
		return userDataService.updateStudent(studentPO);
	}
	@Override
	//�޸Ľ�ʦ����
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
	//�޸�ѧ������
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
	//��ʦ��½��֤
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
	//ѧ����½��֤
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
	//ɾ����ʦ
	public boolean deleteTeacher(String teacherid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return userDataService.deleteTeacher(teacherid);
	}

	@Override
	//ɾ��ѧ��
	public boolean deleteStudent(String studentid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return userDataService.deleteStudent(studentid);
	}

	@Override
	//��ѯ��ʦ
	public TeacherVO queryTeacherByID(String teacherid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return new TeacherVO(userDataService.queryTeacherByID(teacherid));
	}

	@Override
	//��ѯѧ��
	public StudentVO queryStudentByID(String studentid) throws RemoteException {
		UserDataService userDataService = new UserData();
		return new StudentVO(userDataService.queryStudentByID(studentid));
	}

	@Override
	//ͨ����ʦ�����Ժϵ��ѯ��ʦ
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
	//ͨ����ʦ�����ѯ��ʦ
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
	//ͨ��ѧ���꼶��Ժϵ��ѯѧ��
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
	//ͨ��Ժϵ��ѯ��ʦ
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
	//ͨ��Ժϵ��ѯѧ��
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
	
	//�Զ����ɽ�ʦID
	private String createTeacherID(){
		UserDataService userDataService = new UserData();
		String maxID = userDataService.getMaxTeacherID();
		int newTeacherID = Integer.parseInt(maxID)+1;
		return ""+newTeacherID;
	}
	
	//�Զ�����ѧ��ID
	private String createStudentID(){
		UserDataService userDataService = new UserData();
		String maxID = userDataService.getMaxStudentID();
		int newStudentID = Integer.parseInt(maxID)+1;
		return ""+newStudentID;
	}


}
