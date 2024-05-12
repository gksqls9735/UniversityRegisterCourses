package model;

public class LessonVO {
	private int no;
	private String l_abbre;
	private String l_name;

	public LessonVO() {
		super();
	}

	public LessonVO(int no, String l_abbre, String l_name) {
		super();
		this.no = no;
		this.l_abbre = l_abbre;
		this.l_name = l_name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getL_abbre() {
		return l_abbre;
	}

	public void setL_abbre(String l_abbre) {
		this.l_abbre = l_abbre;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	@Override
	public String toString() {
		return no + "\t" + l_abbre + "\t" + l_name;
	}

}
