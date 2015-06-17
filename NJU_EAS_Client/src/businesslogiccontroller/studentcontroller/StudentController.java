package businesslogiccontroller.studentcontroller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.teachcourseblservice.TeachCourseBLService;
import client.ClientRunner;
import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;
/**
 * @author ���� :knox
 * ��˵����
 * �����ѧ���Ĳ��������࣬��������bl��Ĳ�ͬ�ӿ���ʵ���ض�����
 */
public class StudentController {

	private StudentVO currentStudent = null;

	public StudentController(StudentVO studentVO) {
		this.currentStudent = studentVO;
	}

	public boolean changeMyPassword(String oldpassword, String newpassword) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.changeStudentPassword(
					this.currentStudent.getID(), oldpassword, newpassword);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean completeMyInfo(String age, String hometown, String contact) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/userBLService");
			return userBLService.completeStudentInfo(
					this.currentStudent.getID(), age, hometown, contact);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// ��ȡϵͳ״̬�Գ�ʼ�����ֽ���
	public String getSystemStatus() {
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.getSystemStatus();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ѧ��ѡ�������Σ�������С����������ʱ��ֱ��ѡ��
	// ��һ�㻹Ҫ�룺����ý�����ѧ��ѡ����һ���������Ժ��һֱ��ʾ���Ѿ�ѡ���˸������Σ���������ʾѡ�ν���֮��ġ���
	// ��Ӧ����showCourseList������ɣ�����Ӧ���档
	public boolean selectSportsCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			int currentMember = selectionRecordBLService.showCourseStudents(
					courseid).size();
			if (currentMember < courseVO.getMemberLimit()) {
				return (selectionRecordBLService.selectCourse(courseid,
						studentid));
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}

	// ѧ��ѡ��ѡ�޿Σ��Ƚ���Ԥѡ���С�
	public boolean selectOptionalCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			return selectionRecordBLService.preselectCourse(courseid,
					studentid, getMySelectRank(), courseVO.getMemberLimit());

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	// ֻҪ������������������������������Ϳ��Լ�ʱѡ��
	public boolean reselectOptionalCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			CourseVO courseVO = courseBLService.queryCourseByID(courseid);
			if (courseVO.getMemberLimit() > selectionRecordBLService
					.showCourseStudents(courseid).size())
				return selectionRecordBLService.selectCourse(courseid,
						studentid);

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	// �ɹ�ѡ��ĳ�ſ�֮����ѡ Ӧ��ֻ���г��ܹ�����ѡ�Ŀγ�
	public boolean quitCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.quitCourse(courseid, studentid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}

	// ��ѡԤѡ�α��еĿ�
	public boolean quitpreCourse(String courseid, String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.quitpreCourse(courseid, studentid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}
	
	// ����ĳ�ſγ�
	public boolean assessCourse(String courseid, int score) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.assessCourse(courseid, score));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}

	// ѡ��רҵ��ʱ��Ҫͨ�����������ʾ����ѡ��Ŀε��б�
	public ArrayList<CourseVO> showCourseList(String grade, String department,
			String coursetype) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return (courseBLService.queryCourseByGradeDepartmentAndType(grade,
					department, coursetype));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// ѡͨʶ�����ֺ�������ʱ�̵��������������û���꼶��Ժϵ֮�֣�
	public ArrayList<CourseVO> showCourseList(String coursetype) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return courseBLService.queryCourseByType(coursetype);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// ��ʾ�γ���ϸ��Ϣ
	public CourseVO showCourseInfo(String courseid) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			return (courseBLService.queryCourseByID(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	//��ʾ�γ̵��ον�ʦ
	public ArrayList<TeacherVO> showCourserTeachers(String courseid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/teachCourseBLService");
			return (teachCourseBLService.showCourserTeachers(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	//��ʾ�γ̵�ѧ��
	public ArrayList<StudentVO> showCourseStudents(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return selectionRecordBLService.showCourseStudents(courseid);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	//���ݿγ̺Ų�ѯ�γ����гɼ�
	public ArrayList<SelectionRecordVO> showCourseScores(String courseid){
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return(selectionRecordBLService.showCourseScores(courseid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}

	//��ʾԤѡ��ĳ�γ̵�ѧ��
	public ArrayList<StudentVO> showCoursepreStudents(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return selectionRecordBLService.showCoursepreStudents(courseid);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// ��ʾѧ����ѡ��Ŀγ��б�
	public ArrayList<CourseVO> showMyCourseList(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentCourses(studentid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	//��ʾ�Լ�Ԥѡ��ѡ���б�
	public ArrayList<CourseVO> showMypreCourseList(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentpreCourses(studentid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	// ��ʾѧ���ĳɼ���ͨ���������������ͨ��selectionrecord���ÿ���γ̵�id�õ������֣���ô�Ϳ��Խ�id,name��score��ʾ���б�����
	public ArrayList<SelectionRecordVO> showMyScores(String studentid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showStudentScores(studentid));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	// Arraylist���ѧ�ְ���רҵָѡ�Σ�רҵѡ�޿Σ�ͨʶ��+���ֿΣ��������޿Σ����������Σ����Ĵ�����㡣
	public ArrayList<Integer> showMyCredits(String studentid) {
		ArrayList<Integer> creditList = new ArrayList<Integer>();
		int credit1 = 0;
		int credit2 = 0;
		int credit3 = 0;
		int credit4 = 0;
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/courseBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			ArrayList<SelectionRecordVO> selectionRecordVOs = selectionRecordBLService
					.showStudentScores(studentid);
			for (SelectionRecordVO selectionRecordVO : selectionRecordVOs) {
				if (selectionRecordVO.getScore() >= 60) {
					CourseVO courseVO = courseBLService
							.queryCourseByID(selectionRecordVO.getCourseID());
					if (courseVO.getCourseType().equals("רҵָѡ��")) {
						credit1 += courseVO.getCredit();
					} else if (courseVO.getCourseType().equals("רҵѡ�޿�")) {
						credit2 += courseVO.getCredit();
					} else if (courseVO.getCourseType().equals("ͨʶ��")
							|| courseVO.getCourseType().equals("���ֿ�")) {
						credit3 += courseVO.getCredit();
					} else {
						credit4 += courseVO.getCredit();
					}
				}
			}
			creditList.add(credit1);
			creditList.add(credit2);
			creditList.add(credit3);
			creditList.add(credit4);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return creditList;
	}


	// ѧ������һ��private�Ļ������ѡ�޿�ѡ�����ȼ��ķ��������������selectOptionalCourse�����С�private��Ϊ�˱���������Ե�������
	private String getMySelectRank() {
		ArrayList<Integer> creditList = showMyCredits(getcurrentStudent()
				.getID());
		int optionalcredit = creditList.get(2);
		if (getcurrentStudent().getGrade().trim().equals("����һ�꼶")) {
			return "middle";
		} else if (getcurrentStudent().getGrade().trim().equals("���ƶ��꼶")) {
			if (optionalcredit < 4) {
				return "high";
			} else if (optionalcredit > 6) {
				return "low";
			} else {
				return "middle";
			}
		} else if (getcurrentStudent().getGrade().trim().equals("�������꼶")) {
			if (optionalcredit < 9) {
				return "high";
			} else if (optionalcredit > 11) {
				return "low";
			} else {
				return "middle";
			}
		} else  if (getcurrentStudent().getGrade().trim().equals("�������꼶")){ 
			if (optionalcredit < 14) {
				return "high";
			} else {
				return "low";
			}
		}else{
			return "middle";
		}
	}
	
	public ArrayList<String> getMyNotices(){
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService)Naming.lookup("rmi://"+ClientRunner.getIP()+":1099/systemInfoBLService");
			return systemInfoBLService.getMyNotices(currentStudent.getID());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public StudentVO getcurrentStudent() {
		return currentStudent;
	}

}
