package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.LessonVO;

public class LessonRegisterManager {

	public static Scanner sc = new Scanner(System.in);

	// 과목 목록
	public void lessonList() {
		LessonDAO ld = new LessonDAO();
		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}

	// 과목 등록 관리
	public void lessonRegister() {
		LessonDAO ld = new LessonDAO();
		LessonVO lVo = new LessonVO();

		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("과목 정보 입력");
		System.out.print("과목 약어: ");
		String l_abbre = sc.nextLine().toUpperCase();
		System.out.print("과목명: ");
		String l_name = sc.nextLine();

		lVo.setL_abbre(l_abbre);
		lVo.setL_name(l_name);

		ld.setLessonRegister(lVo);
		System.out.println();

		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}

	// 과목 수정 관리
	public void lessonUpdate() {
		LessonDAO ld = new LessonDAO();
		LessonVO lVo = new LessonVO();

		System.out.println("과목 전체 리스트(사용중인 과목 변경 불가)");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("수정항 과목 일련번호 입력");
		System.out.print("일련번호: ");
		int no = Integer.parseInt(sc.nextLine());

		System.out.println();
		System.out.println("새로운 정보 모두 입력");
		System.out.print("과목약어: ");
		String l_abber = sc.nextLine().toUpperCase();
		System.out.print("과목명: ");
		String l_name = sc.nextLine();

		lVo.setNo(no);
		lVo.setL_abbre(l_abber);
		lVo.setL_name(l_name);

		ld.setLessonUpdate(lVo);
		System.out.println();
		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}

	// 과목 삭제 관리
	public void lessonDelete() {
		LessonDAO ld = new LessonDAO();

		System.out.println("과목 전체 리스트(사용중인 과목 삭제 불가)");
		ld.getLessonTotalList();
		System.out.println();

		System.out.println("삭제할 과목 일련번호 입력");
		System.out.print("일련번호: ");
		int no = Integer.parseInt(sc.nextLine());

		ld.setLessonDelete(no);

		System.out.println();
		System.out.println("과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
	}

}
