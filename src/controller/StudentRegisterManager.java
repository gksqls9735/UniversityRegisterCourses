package controller;

import java.util.Scanner;

import model.StudentVO;

public class StudentRegisterManager {
	
	public static Scanner sc = new Scanner(System.in);
	
	//학생 등록
	public void studentRegister() {
		StudentDAO stuD = new StudentDAO();
		StudentVO stuVo = new StudentVO();
		SubjectDAO subD = new SubjectDAO();
		boolean id_check = false;
		
		System.out.println("학생 정보 입력");
		System.out.print("이름: ");
		String sd_name = sc.nextLine();
		String sd_id = null;
		do {
			System.out.print("아이디(8자 이상 12자 이): ");
			sd_id = sc.nextLine();
			id_check = stuD.getStudentIdOverlap(sd_id);
			if (id_check) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요.");
			}
		} while (id_check);
		System.out.print("비밀번호: ");
		String sd_passwd = sc.nextLine();
		
		subD.getSubjectTotalList();
		
		System.out.print("학과번호: ");
		String s_num = sc.nextLine();
		System.out.print("생년월일: ");
		String sd_birthday = sc.nextLine();
		System.out.print("전화번호: ");
		String sd_phone = sc.nextLine();
		System.out.print("주소: ");
		String sd_address = sc.nextLine();
		System.out.print("이메일: ");
		String sd_email = sc.nextLine();
		
		stuVo.setSd_name(sd_name);
		stuVo.setSd_id(sd_id);
		stuVo.setSd_passwd(sd_passwd);
		stuVo.setS_num(s_num);
		stuVo.setSd_birthday(sd_birthday);
		stuVo.setSd_phone(sd_phone);
		stuVo.setSd_address(sd_address);
		stuVo.setSd_email(sd_email);
		
		stuD.setStudentRegister(stuVo);
		
		System.out.println();
		System.out.println("학생 전체 리스트");
		stuD.getStudent(stuVo.getSd_id(), stuVo.getSd_passwd());
		System.out.println();		
	}
	//학생 정보 수정
	public void studentUpdate() {
		StudentDAO stuD = new StudentDAO();
		StudentVO stuVo = new StudentVO();
		boolean success = false;
		
		String sd_id = null;
		String sd_passwd = null;
		
		do {
			System.out.print("아이디: ");
			sd_id = sc.nextLine();
			System.out.print("비밀번호: ");
			sd_passwd = sc.nextLine();
			success = stuD.getStudentLogin(sd_id, sd_passwd);
			
			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
			}
		} while (!success);
		
		String sd_num = stuD.getStudentNum(sd_id, sd_passwd);
		
		System.out.println("수정할 학생");
		System.out.print("학번: " + sd_num);
		
		System.out.println();
		System.out.println("변경할 정보 입력");
		System.out.print("이름: ");
		String sd_name = sc.nextLine();
		System.out.print("아이디: ");
		sd_id = sc.nextLine();
		System.out.print("비밀번호: ");
		sd_passwd = sc.nextLine();
		System.out.print("학과번호: ");
		String s_num = sc.nextLine();
		System.out.print("전화번호: ");
		String sd_phone = sc.nextLine();
		System.out.print("주소: ");
		String sd_address = sc.nextLine();
		System.out.print("이메일: ");
		String sd_email = sc.nextLine();
		
		stuVo.setSd_name(sd_name);
		stuVo.setSd_num(sd_num);
		stuVo.setSd_id(sd_id);
		stuVo.setSd_passwd(sd_passwd);
		stuVo.setS_num(s_num);
		stuVo.setSd_phone(sd_phone);
		stuVo.setSd_address(sd_address);
		stuVo.setSd_email(sd_email);
		
		stuD.setStudentUpdate(stuVo);
		
		System.out.println("학생 정보 수정 결과");
		stuD.getStudent(sd_id, sd_passwd);
		System.out.println();		
	}
	//학생 목록 출력
	public void studentTotalList() {
		StudentDAO stuD = new StudentDAO();
		System.out.println("학생 전체 리스트");
		System.out.print("관리자 비밀번호: ");
		String pw = sc.nextLine();
		
		if (pw.equals("admin1234")) {
			stuD.getStudentTotalList();
			
		} else {
			System.out.println("관리자 비밀번호가 틀립니다.");
		}
		
	}

}

