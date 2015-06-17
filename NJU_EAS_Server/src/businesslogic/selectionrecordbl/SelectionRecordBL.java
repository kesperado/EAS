package businesslogic.selectionrecordbl;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.CoursePO;
import po.SelectionRecordPO;
import po.preSelectionRecordPO;
import vo.CourseVO;
import vo.SelectionRecordVO;
import vo.StudentVO;
import businesslogicservice.selectionrecordblservice.SelectionRecordBLService;
import data.coursedata.CourseData;
import data.selectionrecorddata.SelectionRecordData;
import data.userdata.UserData;
import dataservice.coursedataservice.CourseDataService;
import dataservice.selectionrecorddataservice.SelectionRecordDataService;
import dataservice.userdataservice.UserDataService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * @author ���� :knox
 * ��˵����
 * �����ѡ���߼������࣬������ֱ��ѡ��γ̣�Ԥѡ�γ̣���ѡ�γ̣���ѡԤѡ�γ̣���ǩ����ѯ�ȵȲ���
 */
public class SelectionRecordBL extends UnicastRemoteObject  implements SelectionRecordBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
	}
	public SelectionRecordBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	//���ѡ�η����ǳɹ�ѡ�ε���˼��ֻ����������ļ��飺�α�������û���Ѿ�ѡ����Ŀγ�
	//���ѡ�η���һ��ʼ�� ������ʦ�����ʼѡ�ΰ�ť������ָѡ�ͱ��޿��Զ���ӵ�����Ŀ��ѧ���α����ʱ������
	//����ѡ��ֱѡʱ����ã������ж������ķ�����controller���棬���Բ���Ҫ�������ж�
	//����ѡ�ν���ʱ����Ԥѡ�α������ѡ�μ�¼ɸѡ�ɹ�ʱ��Ҳ��������������ѡ�μ�¼��ʽ��ӵ���ʽѡ���б���
	//��ѡʱ����ֱѡʱҲ������������
	public boolean selectCourse(String courseid, String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		if(selectionRecordDataService.findSelectionRecord(courseid, studentid)==null){
			return selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(courseid, studentid));
		}
		else{
			return false;
		}
	}

	@Override
	//Ԥѡ�γ�
	//ѧ��ѧ��ѡ��ѡ��ͨʶ���ֺͿ�רҵѡ�޿ε�ʱ�������������
	public boolean preselectCourse(String courseid,String studentid,String rank,int memberlimit)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		if(selectionRecordDataService.findpreSelectionRecord(courseid, studentid)==null){
			return selectionRecordDataService.addpreSelectionRecord(new preSelectionRecordPO(courseid, studentid,rank,memberlimit));
		}
		else{
			return false;
		}
	}
	
	//�����ֱ����ѡ��Ҫ���ڽ������г�����ѡ���б��ʱ��ֻ��ʾ��ѡ����Ϊ����ѡʱ����
	@Override
	public boolean quitCourse(String courseid, String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService=new CourseData();
		CoursePO coursePO = courseDataService.queryCourseByID(courseid);
		if(coursePO.getCanQuit().equals("Yes")){
			return selectionRecordDataService.deleteSelectionRecord(courseid, studentid);
		}else{
			return false;
		}
	}
	
	//ɾ��Ԥѡ�α��е�ѡ�μ�¼
	@Override
	public boolean quitpreCourse(String courseid,String studentid)throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		return selectionRecordDataService.deletepreSelectionRecord(courseid, studentid);
		
	}
	
	//ѧ���޼������ۿγ�
	@Override
	public boolean assessCourse(String courseid, int score)
			throws RemoteException {
		// TODO Auto-generated method stub
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		return selectionRecordDataService.addCourseAssessment(courseid, score);

	}
	
	//�鿴�γ̵�����
	@Override
	public double showCourseAssess(String courseid) throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<Integer> assessments = selectionRecordDataService.showCourseAssessment(courseid);
		double average=0;
		for(int a: assessments){
			average+=a;
		}
		if(assessments.size()!=0){
			average/=assessments.size();
		}
		return average;
	}

	
	//������ѡ��ʱ�����г�ǩ
	@Override
	public boolean selectionDraw()throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CoursePO> coursePOs = new ArrayList<CoursePO>();
		coursePOs.addAll(courseDataService.queryCourseByType("רҵѡ�޿�"));
		coursePOs.addAll(courseDataService.queryCourseByType("ͨʶ��"));
		coursePOs.addAll(courseDataService.queryCourseByType("���ֿ�"));
		for(CoursePO coursePO :coursePOs){
			ArrayList<preSelectionRecordPO> preSelectionRecordPOs = selectionRecordDataService.showCoursepreStudents(coursePO.getID());
			if(coursePO.getMemberLimit()>=preSelectionRecordPOs.size()){  //����γ̵��������޴���Ԥѡ���б��е�ѡ�μ�¼Ҳ���γ����е�ѡ����������ô�ͽ����м�¼������ʽ�б���
				for(preSelectionRecordPO preSelectionRecordPO: preSelectionRecordPOs){
					selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID()));
				}
			}else{  //��Ԥѡ�б��е�ѧ�����ݲ�ͬ�����ȼ����벻ͬ���б���
				ArrayList<preSelectionRecordPO> tobeselect=new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> high = new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> middle = new ArrayList<preSelectionRecordPO>();
				ArrayList<preSelectionRecordPO> low = new ArrayList<preSelectionRecordPO>();
				for(preSelectionRecordPO preSelectionRecordPO: preSelectionRecordPOs){
					if(preSelectionRecordPO.getRank().equals("high")){
						high.add(preSelectionRecordPO);
					}else if(preSelectionRecordPO.getRank().equals("middle")){
						middle.add(preSelectionRecordPO);
					}else if(preSelectionRecordPO.getRank().equals("low")){
						low.add(preSelectionRecordPO);
					}
				}
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(high);
				tobeselect.addAll(middle);
				tobeselect.addAll(middle);
				tobeselect.addAll(middle);
				tobeselect.addAll(low);                  //ʹ��ɸѡ�����и����ȼ����б���Ϊ5/8���е�Ϊ3/8���͵�Ϊ1/8
				int count=0;
				while(count<coursePO.getMemberLimit()){
					preSelectionRecordPO preSelectionRecordPO=tobeselect.get((int)(Math.random()*(tobeselect.size())));
					if(selectionRecordDataService.findSelectionRecord(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID())==null){
						selectionRecordDataService.addSelectionRecord(new SelectionRecordPO(preSelectionRecordPO.getCourseID(), preSelectionRecordPO.getStudentID()));
						count++;
					}
				}
			}
		}
		selectionRecordDataService.emptypreSeletionRecord();//���Ԥѡ���б�  
		return true;
	}
	
	@Override
	//�Ǽǳɼ�
	public boolean recordScore(ArrayList<SelectionRecordVO> scores)
			throws RemoteException {
		boolean isSucceed=true;
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		for(SelectionRecordVO score: scores){
		 if(!selectionRecordDataService.updateSelectionRecord(new SelectionRecordPO(score))){
			 isSucceed = false;
			 break;
		 }
		}
		return isSucceed;
	}

	@Override
	//ͨ��Excel�Ǽǳɼ�
	public boolean recordScoreByExcel(String filename,String courseid) throws RemoteException {
		// TODO Auto-generated method stub
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(filename));
			Sheet rs = rwb.getSheet(0);
			int cols=rs.getColumns();
			int rows=rs.getRows();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    //��һ�����������ڶ���������
                    String studentid=rs.getCell(j++, i).getContents();
                    int score=Integer.parseInt(rs.getCell(j++, i).getContents());
                    System.out.println("sutdentid:"+studentid+" score:"+score);
                    selectionRecordDataService.updateSelectionRecord(new SelectionRecordPO(courseid, studentid, score));
                    //http://www.cnblogs.com/zyw-205520/p/3762954.html �ο�����
                }
            }
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	//���ݿγ�id��ѯ�γ̵�ѧ���ĳɼ�
	public ArrayList<SelectionRecordVO> showCourseScores(String courseid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<SelectionRecordPO> selectionRecordPOs= selectionRecordDataService.showCourseStudents(courseid);
		ArrayList<SelectionRecordVO> selectionRecordVOs =new ArrayList<SelectionRecordVO>();
		for(SelectionRecordPO selectionRecordPO : selectionRecordPOs){
			selectionRecordVOs.add(new SelectionRecordVO(selectionRecordPO));
		}
		return selectionRecordVOs;
	}

	@Override
	//����ѧ��id��ѯѧ�����Ϲ��Ŀγ̵ĳɼ�
	public ArrayList<SelectionRecordVO> showStudentScores(String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		ArrayList<SelectionRecordPO> selectionRecordPOs=  selectionRecordDataService.showStudentCourses(studentid);
		ArrayList<SelectionRecordVO> selectionRecordVOs =new ArrayList<SelectionRecordVO>();
		for(SelectionRecordPO selectionRecordPO : selectionRecordPOs){
			selectionRecordVOs.add(new SelectionRecordVO(selectionRecordPO));
		}
		return selectionRecordVOs;
	}

	@Override
	//���ݿγ�id��ѯѧ���б�
	public ArrayList<StudentVO> showCourseStudents(String courseid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		UserDataService userDataService = new UserData();
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		ArrayList<SelectionRecordPO> selectionRecordPOs =selectionRecordDataService.showCourseStudents(courseid);
		for(SelectionRecordPO selectionRecordPO:selectionRecordPOs){
			studentVOs.add(new StudentVO(userDataService.queryStudentByID(selectionRecordPO.getStudentID())));
		}
		return studentVOs;
	}

	@Override
	//����ѧ��id�鿴ѧ���Ŀα�
	public ArrayList<CourseVO> showStudentCourses(String studentid)
			throws RemoteException {
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		ArrayList<SelectionRecordPO> selectionRecordPOs =selectionRecordDataService.showStudentCourses(studentid);
		for(SelectionRecordPO selectionRecordPO:selectionRecordPOs){
			courseVOs.add(new CourseVO(courseDataService.queryCourseByID(selectionRecordPO.getCourseID())));
		}
		return courseVOs;
	}
	
	//�鿴Ԥѡ�ÿγ̵�ѧ���б�
	public ArrayList<StudentVO> showCoursepreStudents(String courseid)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		UserDataService userDataService = new UserData();
		ArrayList<StudentVO> studentVOs = new ArrayList<StudentVO>();
		ArrayList<preSelectionRecordPO> preselectionRecordPOs =selectionRecordDataService.showCoursepreStudents(courseid);
		for(preSelectionRecordPO preselectionRecordPO:preselectionRecordPOs){
			studentVOs.add(new StudentVO(userDataService.queryStudentByID(preselectionRecordPO.getStudentID())));
		}
		return studentVOs;
	}
	
	//�鿴ѧ��Ԥѡ�Ŀγ��б�
	public ArrayList<CourseVO> showStudentpreCourses(String studentid)throws RemoteException{
		SelectionRecordDataService selectionRecordDataService = new SelectionRecordData();
		CourseDataService courseDataService = new CourseData();
		ArrayList<CourseVO> courseVOs = new ArrayList<CourseVO>();
		ArrayList<preSelectionRecordPO> preselectionRecordPOs =selectionRecordDataService.showStudentpreCourses(studentid);
		for(preSelectionRecordPO preselectionRecordPO:preselectionRecordPOs){
			courseVOs.add(new CourseVO(courseDataService.queryCourseByID(preselectionRecordPO.getCourseID())));
		}
		return courseVOs;
	}




}
