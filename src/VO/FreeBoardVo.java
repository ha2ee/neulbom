package VO;

import java.sql.Date;

//조회한 하나의 글정보를 저장할 용도
//수정할 하나의 글정보를 조회한후 저장할 용도
//DB에 추가한 새글정보를 임시로 저장할 용도
public class FreeBoardVo {
	private int b_idx;
	private String b_id,b_nickname,b_title,b_content;
	private int b_group,b_level;
	private Date b_date, b_update_date;
	private int b_cnt;
	private String b_file;
	private int b_like;
	
	public FreeBoardVo() {}

	public FreeBoardVo(int b_idx, String b_id, String b_nickname, String b_title, String b_content, int b_group,
			int b_level, Date b_date, Date b_update_date, int b_cnt, String b_file, int b_like) {
		
		this.b_idx = b_idx;
		this.b_id = b_id;
		this.b_nickname = b_nickname;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_date = b_date;
		this.b_update_date = b_update_date;
		this.b_cnt = b_cnt;
		this.b_file = b_file;
		this.b_like = b_like;
	}

	public FreeBoardVo(int b_idx, String b_id, String b_nickname, String b_title, String b_content, int b_group,
			int b_level, int b_cnt, String b_file, int b_like) {
		
		this.b_idx = b_idx;
		this.b_id = b_id;
		this.b_nickname = b_nickname;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_file = b_file;
		this.b_like = b_like;
	}

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getB_nickname() {
		return b_nickname;
	}

	public void setB_nickname(String b_nickname) {
		this.b_nickname = b_nickname;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public int getB_group() {
		return b_group;
	}

	public void setB_group(int b_group) {
		this.b_group = b_group;
	}

	public int getB_level() {
		return b_level;
	}

	public void setB_level(int b_level) {
		this.b_level = b_level;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public Date getB_update_date() {
		return b_update_date;
	}

	public void setB_update_date(Date b_update_date) {
		this.b_update_date = b_update_date;
	}

	public int getB_cnt() {
		return b_cnt;
	}

	public void setB_cnt(int b_cnt) {
		this.b_cnt = b_cnt;
	}

	public String getB_file() {
		return b_file;
	}

	public void setB_file(String b_file) {
		this.b_file = b_file;
	}

	public int getB_like() {
		return b_like;
	}

	public void setB_like(int b_like) {
		this.b_like = b_like;
	}
	
	
	
}
