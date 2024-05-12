package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TraineeVO;

public class TraineeDAO {
	// 수강 신청
	public void setTraineeRegister(TraineeVO tVo) {

		String sql = "INSERT INTO TRAINEE VALUES (TRAINEE_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tVo.getSd_num());
			pstmt.setString(2, tVo.getL_abbre());
			pstmt.setString(3, tVo.getT_section());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("수강 신청 완료.");
				System.out.println("수강 신청 성공!!!");
			} else {
				System.out.println("수강 신청 실패!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 수강 신청 삭제
	public void setTraineeDelete(int no) {

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM TRAINEE WHERE NO = ?");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("수강 신청 취소 완료.");
				System.out.println("수강 신청 취소 성공!!!");
			} else {
				System.out.println("수강 신청 취소 실패!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 개인 수강 신청 전체 목록
	public void getTraineeTotal(String sd_num) {

		String sql = "SELECT TR.NO AS NO, TR.SD_NUM AS SD_NUM, TR.L_ABBRE AS L_ABBRE, LE.L_NAME AS L_NAME, ST.SD_NAME AS SD_NAME, "
				+ "T_SECTION, T_DATE FROM TRAINEE TR, LESSON LE, STUDENT ST "
				+ "WHERE TR.SD_NUM = ? AND TR.L_ABBRE = LE.L_ABBRE AND TR.SD_NUM = ST.SD_NUM ORDER BY T_DATE ASC";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = new TraineeVO();

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd_num);
			rs = pstmt.executeQuery();

			System.out.println("일련번호\t학생번호\t\t과목약어\t과목명\t학생이름\t과목구분\t등록일");

			while (rs.next()) {
				tVo.setNo(rs.getInt("NO"));
				tVo.setSd_num(rs.getString("SD_NUM"));
				tVo.setL_abbre(rs.getString("L_ABBRE"));
				tVo.setT_section(rs.getString("T_SECTION"));
				tVo.setT_date(rs.getDate("T_DATE"));

				System.out.println(
						tVo.getNo() + "\t" + tVo.getSd_num() + "\t" + tVo.getL_abbre() + "\t" + rs.getString("L_NAME")
								+ "\t" + rs.getString("SD_NAME") + "\t" + tVo.getT_section() + "\t" + tVo.getT_date());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 선택한 과목명의 과목 번호
	public String getLessonNum(String l_name) {

		String sql = "SELECT L_ABBRE FROM LESSON WHERE L_NAME = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String l_abbre = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, l_name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				l_abbre = rs.getString("L_ABBRE");
			} else {
				System.out.println("수강 과목 번호");
				System.out.println("선택한 " + l_name + "과목의 과목번호가 없습니다.");
				System.out.println("과목 검색 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return l_abbre;
	}

}
