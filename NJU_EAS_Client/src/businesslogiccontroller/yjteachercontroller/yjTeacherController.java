package businesslogiccontroller.yjteachercontroller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import businesslogicservice.systeminfoblservice.SystemInfoBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.teachcourseblservice.TeachCourseBLService;
import client.ClientRunner;
import vo.CourseVO;
import vo.PlanVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import vo.TeacherVO;

/**
 * @author ���� :knox ��˵���� �����Ժϵ������ʦ�Ĳ��������࣬��������bl��Ĳ�ͬ�ӿ���ʵ���ض�����
 */
public class yjTeacherController {
	private TeacherVO currentyjTeacher = null;

	public yjTeacherController(TeacherVO teacherVO) {
		this.currentyjTeacher = teacherVO;
	}

	public boolean changeMyPassword(String oldpassword, String newpassword) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/userBLService");
			return userBLService.changeTeacherPassword(
					this.currentyjTeacher.getID(), oldpassword, newpassword);
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
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/userBLService");
			return userBLService.completeTeacherInfo(
					this.currentyjTeacher.getID(), age, hometown, contact);
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
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/systemInfoBLService");
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

	// �Զ����ɿγ̺Ű�ķ����γ�
	public boolean publishCourse(String name, int credit, String coursetype,
			String grade, String targetdepartment) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
			return courseBLService.publishCourse(name, credit, coursetype,
					grade, targetdepartment, currentyjTeacher.getDepartment());
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

	// Ժϵ������ʦ���ſγ�ʱ��ص�
	public boolean arrangeCourse(String courseid, String time,
			String classroom, int memberlimit) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
			return (courseBLService.arrangeCourse(courseid, time, classroom,
					memberlimit));
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

	// ��ʾ�γ����е��ον�ʦ
	public ArrayList<TeacherVO> showCourserTeachers(String courseid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/teachCourseBLService");
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

	public ArrayList<SelectionRecordVO> showCourseScores(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/selectionRecordBLService");
			return (selectionRecordBLService.showCourseScores(courseid));
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

	public ArrayList<TeacherVO> showTeacherList(String teachertype,
			String department) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/userBLService");
			return (userBLService.queryTeacherByTypeAndDepartment(teachertype,
					department));
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

	public ArrayList<StudentVO> showStudentList(String grade, String department) {
		try {
			UserBLService userBLService = (UserBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/userBLService");
			return (userBLService.queryStudentByGradeAndDepartment(grade,
					department));
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
	public ArrayList<Integer> showStudentCredits(String studentid) {
		ArrayList<Integer> creditList = new ArrayList<Integer>();
		int credit1 = 0;
		int credit2 = 0;
		int credit3 = 0;
		int credit4 = 0;
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/selectionRecordBLService");
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

	// ��ʾ�ý�ʦ������Ҫ���ڵĿγ�
	public ArrayList<CourseVO> showTeacherCourseList(String teacherid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/teachCourseBLService");
			return teachCourseBLService.showrTeacherCourses(teacherid);
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

	// ��ӿγ̽�ʦ
	public boolean addCourserTeacher(String courseid, String rteacherid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/teachCourseBLService");
			return (teachCourseBLService
					.addCourserTeacher(courseid, rteacherid));
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

	// ɾ���γ̽�ʦ
	public boolean deleteCourserTeacher(String courseid, String rteacherid) {
		try {
			TeachCourseBLService teachCourseBLService = (TeachCourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/teachCourseBLService");
			return (teachCourseBLService.deleteCourserTeacher(courseid,
					rteacherid));
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

	// ��ʾ��Ժϵĳ�꼶��ȫ���γ̣���Ϣ���ݰ���������� ��id,name,credit,time,classroom
	public ArrayList<CourseVO> showCourseList(String grade, String department) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
			return (courseBLService.queryCourseByGradeAndDepartment(grade,
					department));
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
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
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

	// ��ʾ�γ̵�ѧ��
	public ArrayList<StudentVO> showCourseStudents(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/selectionRecordBLService");
			return (selectionRecordBLService.showCourseStudents(courseid));
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
	
	// �鿴ĳ�ſγ̵�����
	public double showCourseAssess(String courseid) {
		try {
			SelectionRecordBLService selectionRecordBLService = (SelectionRecordBLService) Naming
					.lookup("rmi://"+ClientRunner.getIP()+":1099/selectionRecordBLService");
			return (selectionRecordBLService.showCourseAssess(courseid));
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
		return 0;

	}

	// ɾ���γ̣�ͬ��һ��������ֻ��ɾ���б����еĿγ�
	public boolean deleteCourse(String courseid) {
		try {
			CourseBLService courseBLService = (CourseBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/courseBLService");
			return (courseBLService.deleteCourse(courseid));
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

	// �޸Ľ�ѧ�ƻ�
	public boolean modifyPlan(String plan, int inadmit, int outadmit) {
		try {
			PlanBLService planBLService = (PlanBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/planBLService");
			return (planBLService.updatePlan(currentyjTeacher.getDepartment(),
					plan, inadmit, outadmit));
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

	public PlanVO showPlan() {
		try {
			PlanBLService planBLService = (PlanBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/planBLService");
			return (planBLService.findPlan(currentyjTeacher.getDepartment()));
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

	public ArrayList<String> getMyNotices() {
		try {
			SystemInfoBLService systemInfoBLService = (SystemInfoBLService) Naming
					.lookup("rmi://" + ClientRunner.getIP()
							+ ":1099/systemInfoBLService");
			return systemInfoBLService.getMyNotices(currentyjTeacher.getID());
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

	public TeacherVO getcurrentyjTeacherVO() {
		return currentyjTeacher;
	}
}
