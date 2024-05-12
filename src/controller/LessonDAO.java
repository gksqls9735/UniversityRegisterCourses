package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LessonVO;

public class LessonDAO {

	// 과목 목록
	public void getLessonTotalList() {

		String sql = "SELECT * FROM LESSON ORDER BY NO ASC";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LessonVO lVo = new LessonVO();
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("일련번호\t과목약어\t과목명");

			while (rs.next()) {
				lVo.setNo(rs.getInt("NO"));
				lVo.setL_abbre(rs.getString("L_ABBRE"));
				lVo.setL_name(rs.getString("L_NAME"));

				System.out.println(lVo.toString());
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

	// 과목 등록
	public void setLessonRegister(LessonVO lVo) {

		String sql = "INSERT INTO LESSON VALUES (LESSON_SEQ.NEXTVAL, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, lVo.getL_abbre());
			pstmt.setString(2, lVo.getL_name());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				System.out.println(lVo.getL_name() + " 과목 등록 완료.");
				System.out.println("과목 등록 성공!!!");
			} else {
				System.out.println("과목 등록 실패!!!");
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

	// 과목 수정
	public boolean setLessonUpdate(LessonVO lVo) {

		String sql = "UPDATE LESSON SET L_ABBRE = ?, L_NAME = ? WHERE NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean lessonUpdateSuccess = false;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, lVo.getL_abbre());
			pstmt.setString(2, lVo.getL_name());
			pstmt.setInt(3, lVo.getNo());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(lVo.getL_name() + " 과목 수정 완료.");
				System.out.println("과목 수정 성공!!!");
				lessonUpdateSuccess = true;
			} else {
				System.out.println("과목 수정 실패!!!");
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

		return lessonUpdateSuccess;
	}

	// 과목 삭제
	public void setLessonDelete(int no) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM LESSON WHERE NO = ?");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				System.out.println("과목 삭제 완료.");
				System.out.println("과목 삭제 성공!!!");
			} else {
				System.out.println("과목 삭제 실패!!!");
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
}
