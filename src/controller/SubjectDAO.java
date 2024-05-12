package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SubjectVO;

public class SubjectDAO {
	// 학과 목록 출력 함수
	public void getSubjectTotalList() {
		String sql = "SELECT * FROM SUBJECT ORDER BY NO ASC";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO subVo = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("일련번호\t학과번호\t학과명");

			while (rs.next()) {
				subVo = new SubjectVO();
				subVo.setNo(rs.getInt("NO"));
				subVo.setS_num(rs.getNString("S_NUM"));
				subVo.setS_name(rs.getNString("S_NAME"));
				System.out.println(subVo.getNo() + "\t" + subVo.getS_num() + "\t" + subVo.getS_name());
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

	// 학과 목록 입력 함수
	public void setSubjectRegister(SubjectVO subVo) {

		String sql = "INSERT INTO SUBJECT VALUES (SUBJECT_SEQ.NEXTVAL, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subVo.getS_num());
			pstmt.setString(2, subVo.getS_name());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(subVo.getS_name() + " 학과 등록 완료.");
				System.out.println("학과 등록 성공!!!");
			} else {
				System.out.println("학과 등록 실패!!!");
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
	//학과 목록 수정 함수
	public void setSubjectUpdate(SubjectVO subVo) {
		String sql = "UPDATE SUBJECT SET S_NUM=?, S_NAME=? WHERE NO=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con =  DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subVo.getS_num());
			pstmt.setString(2, subVo.getS_name());
			pstmt.setInt(3, subVo.getNo());
			
			int i = pstmt.executeUpdate();
			
			if (i == 1) {
				System.out.println(subVo.getS_name() + "학과 수정 완료.");
				System.out.println("학과 수정 성공!!!");
			}else {
				System.out.println("학과 수정 실패!!!");
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
	// 학과 목록 삭제 함수
	public void setSubjectDelete(int no) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM SUBJECT WHERE NO = ?");

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("학과 삭제 완료.");
				System.out.println("학과 삭제 성공!!!");
			} else {
				System.out.println("학과 삭제 실패!!!");
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
