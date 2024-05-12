package controller;

import java.util.Scanner;

import model.LessonVO;
import model.TraineeVO;

public class TraineeRegisterManager {

	public static Scanner sc = new Scanner(System.in);

	// 수강 신청 리스트
	public void traineeList() {
		boolean success = false;
		TraineeDAO td = new TraineeDAO();
		StudentDAO sd = new StudentDAO();

		System.out.println("수강 신청한 리스트");

		String sd_num = null;
		String id = null;
		String pw = null;

		do {
			System.out.print("아이디: ");
			id = sc.nextLine();
			System.out.print("비밀번호: ");
			pw = sc.nextLine();
			success = sd.getStudentLogin(id, pw);

			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
				System.out.print("메인 메뉴로 이동(Y/N): ");
				String mainMenu = sc.nextLine();

				if (mainMenu.equalsIgnoreCase("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);

		sd_num = sd.getStudentNum(id, pw);
		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotal(sd_num);
		System.out.println();
	}

	// 수강 신청 관리
	public void traineeRegister() {
		TraineeDAO td = new TraineeDAO();
		TraineeVO tv = new TraineeVO();

		LessonDAO ld = new LessonDAO();
		LessonVO lv = new LessonVO();

		StudentDAO stuD = new StudentDAO();

		String sd_num = null;
		String l_abber = null;
		String t_section = null;
		String id = null;
		String pw = null;
		boolean success = false;

		System.out.println("수강 가능 과목 전체 리스트");
		ld.getLessonTotalList();
		System.out.println();
		System.out.println("수강 신청을 위한 정보 입력");

		do {
			System.out.print("아이디: ");
			id = sc.nextLine();
			System.out.print("비밀번호: ");
			pw = sc.nextLine();

			success = stuD.getStudentLogin(id, pw);

			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
				System.out.println("메인 메뉴로 이동(Y/N): ");
				String mainMenu = sc.nextLine();

				if (mainMenu.equalsIgnoreCase("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);

		sd_num = stuD.getStudentNum(id, pw);

		System.out.println("학번" + sd_num);
		System.out.print("과목약어: ");
		l_abber = sc.nextLine();
		System.out.print("과목구분(교양, 전공, 부전공): ");
		t_section = sc.nextLine();

		// 수강 신청 등록
		tv.setSd_num(sd_num);
		tv.setL_abbre(l_abber);
		tv.setT_section(t_section);
		td.setTraineeRegister(tv);

		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotal(sd_num);
		System.out.println();
	}

	// 수강 취소 관리
	public void traineeDelete() {
		TraineeDAO td = new TraineeDAO();
		StudentDAO stud = new StudentDAO();

		String sd_num = null;
		int no = 0;
		String id = null;
		String pw = null;
		boolean success = false;

		System.out.println("수강 취소를 위한 정보 입력");
		do {
			System.out.print("아이디: ");
			id = sc.nextLine();
			System.out.print("비밀번호: ");
			pw = sc.nextLine();

			success = stud.getStudentLogin(id, pw);

			if (!success) {
				System.out.println("아이디 또는 비밀번호가 틀림 다시 입력");
				System.out.println("메인 메뉴로 이동(Y/N): ");
				String mainMenu = sc.nextLine();

				if (mainMenu.equalsIgnoreCase("Y")) {
					return;
				}
				System.out.println();
			}
		} while (!success);

		sd_num = stud.getStudentNum(id, pw);
		System.out.println();
		System.out.println("수강 신청한 리스트");
		td.getTraineeTotal(sd_num);
		System.out.println();
		System.out.println("취소할 수강 신청 일련번호 입력");
		System.out.print("일련번호: ");
		no = Integer.parseInt(sc.nextLine());
		td.setTraineeDelete(no);
		System.out.println();
		System.out.println("수강 신청 취소 후 리스트");
		td.getTraineeTotal(sd_num);
		System.out.println();
	}

}
