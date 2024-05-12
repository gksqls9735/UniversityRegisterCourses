package model;

import java.util.Date;

public class TraineeVO {	
	private int no;
	private String sd_num;
	private String l_abbre;
	private String t_section;
	private Date t_date;
	
	public TraineeVO() {
		super();
	}

	public TraineeVO(int no, String sd_num, String l_abbre, String t_section, Date t_date) {
		super();
		this.no = no;
		this.sd_num = sd_num;
		this.l_abbre = l_abbre;
		this.t_section = t_section;
		this.t_date = t_date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSd_num() {
		return sd_num;
	}

	public void setSd_num(String sd_num) {
		this.sd_num = sd_num;
	}

	public String getL_abbre() {
		return l_abbre;
	}

	public void setL_abbre(String l_abbre) {
		this.l_abbre = l_abbre;
	}

	public String getT_section() {
		return t_section;
	}

	public void setT_section(String t_section) {
		this.t_section = t_section;
	}

	public Date getT_date() {
		return t_date;
	}

	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}

	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", sd_num=" + sd_num + ", l_abbre=" + l_abbre + ", t_section=" + t_section
				+ ", t_date=" + t_date + "]";
	}
	
	
}	
