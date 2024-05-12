package controller;

import java.util.Scanner;

import model.SubjectVO;

public class SubjectRegisterManager {
	
	public static Scanner sc = new Scanner(System.in);
	//학과 목록 보여주는 기능 구현
	public void subjectList() {
		SubjectDAO subD = new SubjectDAO();
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
	}
	//학과 등록 관리
	public void subjectRegister() {
		SubjectDAO subD = new SubjectDAO();
		SubjectVO subVo = new SubjectVO();
		
		String s_num;
		String s_name;
		
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
		
		System.out.println("학과 정보 입력");
		System.out.print("학과 번호: ");
		s_num = sc.nextLine();
		
		System.out.print("학과명: ");
		s_name = sc.nextLine();
		
		subVo.setS_num(s_num);
		subVo.setS_name(s_name);
		
		subD.setSubjectRegister(subVo);
		
		System.out.println();
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
	}
	//학과 수정 관리
	public void subjectUpdate() {
		SubjectDAO subD = new SubjectDAO();
		SubjectVO subVo = new SubjectVO();
		
		int s_no;
		String s_num;
		String s_name;
		
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
		
		System.out.println("수정할 학과 일련번호 입력");
		System.out.print("일련 번호: ");
		s_no = Integer.parseInt(sc.nextLine());
		
		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("학과 번호: ");
		s_num = sc.nextLine();
		System.out.print("학과명: ");
		s_name = sc.nextLine();
		
		subVo.setNo(s_no);
		subVo.setS_num(s_num);
		subVo.setS_name(s_name);
		subD.setSubjectUpdate(subVo);
		
		System.out.println();
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
	}
	//학과 삭제 관리
	public void subjectDelete() {
		SubjectDAO subD = new SubjectDAO();
		SubjectVO subVo = new SubjectVO();
		
		int s_no;
		
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
		
		System.out.println("삭제할 학과 일련번호 입력");
		System.out.print("일련 번호: ");
		s_no = Integer.parseInt(sc.nextLine());
		
		subD.setSubjectDelete(s_no);
		
		System.out.println();
		System.out.println("학과 전체 리스트");
		subD.getSubjectTotalList();
		System.out.println();
		
	}
}
