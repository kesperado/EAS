package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CoursePO;
import vo.CourseVO;
import businesslogicservice.courseblservice.CourseBLService;
import data.coursedata.CourseData;
import dataservice.coursedataservice.CourseDataService;

/**
 * @author ���� :knox
 * ��˵����
 * ����� �γ̵ľ����߼������࣬�����˷����γ̣����ſγ̣����ƿγ���Ϣ�Լ���ѯ�γ̵ȵȲ���
 */
public class CourseBL extends UnicastRemoteObject implements CourseBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	//�����γ�
	public boolean publishCourse(String name, int credit, String coursetype,
			String grade, String targetdepartment, String department)
			throws RemoteException {
		CoursePO coursePO = new CoursePO(createCourseID(), name, credit, grade,
				targetdepartment, department, coursetype);
		CourseDataService courseDataService = new CourseData();
		return courseDataService.publishCoures(coursePO);
	}

	@Override
	//���ſγ�
	public boolean arrangeCourse(String courseid, String time,
			String classroom, int memberlimit) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		ArrayList<CoursePO> coursePOs = courseDataService.showAllCourses();
		for (CoursePO somecoursePO : coursePOs) {
			if (somecoursePO.getTime() != null
					&& somecoursePO.getClassroom() != null) {
				if (time.equals(somecoursePO.getTime())
						&& classroom.equals(
								somecoursePO.getClassroom())&&(!somecoursePO.getID().equals(courseid))) {
					return false;
				}
			}
		}//������ŵĿγ������еĿγ̳�ͻ
		coursePO.setTime(time);
		coursePO.setClassroom(classroom);
		coursePO.setMemberLimit(memberlimit);
		return courseDataService.updateCoures(coursePO);
	}

	@Override
	//���ƿγ���Ϣ
	public boolean completerCourseInfo(String courseid, String outline,
			String textbook, String referencebook, String testtime)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		coursePO.setOutline(outline);
		coursePO.setTextbook(textbook);
		coursePO.setReferenceBook(referencebook);
		coursePO.setTestTime(testtime);
		return courseDataService.updateCoures(coursePO);
	}

	@Override
	//������ʦ����ϵͳ״̬Ϊ��ʼѡ��֮��ͻὫ���Ӧ��һЩ�γ̸�Ϊ��ѡ��
	public boolean startSelectCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("רҵѡ�޿�"));
		coursePOs.addAll(courseDataService.queryCourseByType("ͨʶ��"));
		coursePOs.addAll(courseDataService.queryCourseByType("���ֿ�"));
		coursePOs.addAll(courseDataService.queryCourseByType("������"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanSelect("Yes");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;

	}

	@Override
	// �ͽ�������ѡ�޿εĿ���������Ϊyes,��ζ�������ֿο�����ѡ��Ҳ���Բ�ѡ
	public boolean startQuitAndReselectCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("רҵѡ�޿�"));
		coursePOs.addAll(courseDataService.queryCourseByType("ͨʶ��"));
		coursePOs.addAll(courseDataService.queryCourseByType("���ֿ�"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanQuit("Yes");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;
	}

	@Override
	//����ѡ���ҽ�����ѡ�γ�
	public boolean finishSelectAndQuitCourse() {
		boolean issucceed = true;
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("רҵѡ�޿�"));
		coursePOs.addAll(courseDataService.queryCourseByType("ͨʶ��"));
		coursePOs.addAll(courseDataService.queryCourseByType("���ֿ�"));
		coursePOs.addAll(courseDataService.queryCourseByType("������"));
		for (CoursePO coursePO : coursePOs) {
			coursePO.setCanSelect("No");
			coursePO.setCanQuit("No");
			if (!courseDataService.updateCoures(coursePO)) {
				issucceed = false;
				break;
			}
		}
		return issucceed;

	}

	@Override
	//ɾ���γ�
	public boolean deleteCourse(String courseid) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		return courseDataService.deleteCoures(courseid);
	}

	@Override
	//���ݿγ����Ͳ�ѯ�γ�
	public ArrayList<CourseVO> queryCourseByType(String coursetype) {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByType(coursetype);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//���ݿγ̿���Ժϵ��ѯ�γ�
	public ArrayList<CourseVO> queryCourseByDepartment(String department)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService.queryCourseByDepartment(department);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}
	
	@Override
	//�����꼶�Ϳ���Ժϵ��ѯ�γ�
	public ArrayList<CourseVO> queryCourseByGradeAndDepartment(String grade,
			String department) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByGradeAndDepartment(grade, department);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//�����꼶������Ժϵ�Ϳγ����Ͳ�ѯ�γ�
	public ArrayList<CourseVO> queryCourseByGradeDepartmentAndType(
			String grade, String department, String coursetype)
			throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = courseDataService
				.queryCourseByGradeDepartmentAndType(grade, department,
						coursetype);
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (CoursePO coursePO : coursePOs) {
			courseVOs.add(new CourseVO(coursePO));
		}
		return courseVOs;
	}

	@Override
	//���ݿγ̺Ų�ѯ�γ���ϸ��Ϣ
	public CourseVO queryCourseByID(String courseid) throws RemoteException {
		CourseDataService courseDataService = new CourseData();
		return new CourseVO(courseDataService.queryCourseByID(courseid));
	}

	//ϵͳ�Զ����ɿγ̺ţ�Ϊϵͳ�����γ̺�+1
	private String createCourseID() {
		CourseDataService courseDataService = new CourseData();
		String maxID = courseDataService.getMaxCourseID();
		int newCourseID = Integer.parseInt(maxID) + 1;
		return "" + newCourseID;
	}



}
