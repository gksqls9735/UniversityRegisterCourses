package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StudentVO;

public class StudentDAO {

	// 학생 등록
	public void setStudentRegister(StudentVO stuVo) {
		String sql = "INSERT INTO STUDENT VALUES (STUDENT_SEQ.NEXTVAL, TO_CHAR(SYSDATE, 'YY')|| ? || LPAD(STUDENT_SEQ.NEXTVAL,4,'0'), ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stuVo.getS_num());
			pstmt.setString(2, stuVo.getSd_name());
			pstmt.setString(3, stuVo.getSd_id());
			pstmt.setString(4, stuVo.getSd_passwd());
			pstmt.setString(5, stuVo.getS_num());
			pstmt.setString(6, stuVo.getSd_birthday());
			pstmt.setString(7, stuVo.getSd_phone());
			pstmt.setString(8, stuVo.getSd_address());
			pstmt.setString(9, stuVo.getSd_email());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(stuVo.getSd_name() + "학생 등록 완료.");
				System.out.println("학생 등록 성공!");
			} else {
				System.out.println("학생 등록 실패!");
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

	// 학생 목록 수정
	public void setStudentUpdate(StudentVO stuVo) {
		String sql = "UPDATE STUDENT SET SD_NAME= ?, SD_ID= ?, SD_PASSWD= ?, S_NUM= ?, SD_PHONE= ?, SD_ADDRESS=?, SD_EMAIL=? WHERE SD_NUM=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stuVo.getSd_name());
			pstmt.setString(2, stuVo.getSd_id());
			pstmt.setString(3, stuVo.getSd_passwd());
			pstmt.setString(4, stuVo.getS_num());
			pstmt.setString(5, stuVo.getSd_phone());
			pstmt.setString(6, stuVo.getSd_address());
			pstmt.setString(7, stuVo.getSd_email());
			pstmt.setString(8, stuVo.getSd_num());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(stuVo.getSd_num() + "학생 정보 수정 완료.");
				System.out.println("학생 정보 수정 성공!!!");
			} else {
				System.out.println("학생 정보 수정 실패!!!");
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

	// 동일 학과 학생 일련번호
	public String getStudentCount(String subjectNum) {
		
		String sql = "SELECT LPAD(COUNT(*)+1, 4,'0') AS STUDENTCOUNT FROM STUDENT WHERE S_NUM = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String serialNumber = null;
		
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subjectNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNumber = rs.getString("STUDENTCOUNT");
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
		return serialNumber;
	}

	//학생 아이디 중복 체크
	public boolean getStudentIdOverlap(String idOverlap) {
		
		String sql = "SELECT * FROM STUDENT WHERE SD_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idOverlapResult = false;
		
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idOverlapResult = true;
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
		
		return idOverlapResult;
	}
	
	//학생 로그인
	public boolean getStudentLogin(String id, String pw) {
		
		String sql = "SELECT * FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginSuccess = false;
		
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loginSuccess = true;
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
		
		return loginSuccess;
	}
	
	//학생 번호
	public String getStudentNum(String id, String pw) {
		
		String sql = "SELECT SD_NUM FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sd_num = null;
		
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sd_num = rs.getString("SD_NUM");
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
		
		return sd_num;
	}
	
	//학생 정보
	public void getStudent(String id, String pw) {
		
		String sql = "SELECT * FROM STUDENT WHERE SD_ID = ? AND SD_PASSWD = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			StudentVO stuVo = null;
			System.out.println("일련번호\t학생번호\t\t성명\t아이디"
					+ "\t\t비밀번호\t학과\t\t생년월일\t\t전화번호\t\t주소\t\t\t"
					+ "이메일\t\t\t등록일자");
			if (rs.next()) {
				stuVo = new StudentVO();
				stuVo.setNo(rs.getInt("NO"));
				stuVo.setSd_num(rs.getString("SD_NUM"));
				stuVo.setSd_name(rs.getString("SD_NAME"));
				stuVo.setSd_id(rs.getString("SD_ID"));
				stuVo.setSd_passwd(rs.getString("SD_PASSWD"));
				stuVo.setS_num(rs.getString("S_NUM"));
				stuVo.setSd_birthday(rs.getString("SD_BIRTHDAY"));
				stuVo.setSd_phone(rs.getString("SD_PHONE"));
				stuVo.setSd_address(rs.getString("SD_ADDRESS"));
				stuVo.setSd_email(rs.getString("SD_EMAIL"));
				stuVo.setSd_date(rs.getDate("SD_DATE"));
				System.out.println(stuVo.toString());
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
	
	// 학생 목록 출력
	public void getStudentTotalList() {
		String sql = "SELECT * FROM STUDENT ORDER BY NO ASC";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO stuVo = null;
		
		System.out.println("일련번호\t학생번호\t\t성명\t아이디"
				+ "\t\t비밀번호\t학과\t\t생년월일\t\t전화번호\t\t주소\t\t\t"
				+ "이메일\t\t\t등록일자");

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stuVo = new StudentVO();
				stuVo.setNo(rs.getInt("NO"));
				stuVo.setSd_num(rs.getString("SD_NUM"));
				stuVo.setSd_name(rs.getString("SD_NAME"));
				stuVo.setSd_id(rs.getString("SD_ID"));
				stuVo.setSd_passwd(rs.getString("SD_PASSWD"));
				stuVo.setS_num(rs.getString("S_NUM"));
				stuVo.setSd_birthday(rs.getString("SD_BIRTHDAY"));
				stuVo.setSd_phone(rs.getString("SD_PHONE"));
				stuVo.setSd_address(rs.getString("SD_ADDRESS"));
				stuVo.setSd_email(rs.getString("SD_EMAIL"));
				stuVo.setSd_date(rs.getDate("SD_DATE"));
				System.out.println(stuVo.toString());
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

}