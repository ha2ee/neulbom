package VO;

public class LikeVo {
	int likeNumber,freeBoard_b_idx,like_check;
	String freeBoard_b_id;
	
	public LikeVo() {
	}

	public LikeVo(int likeNumber,  String freeBoard_b_id, int freeBoard_b_idx, int like_check) {
		super();
		this.likeNumber = likeNumber;
		this.freeBoard_b_idx = freeBoard_b_idx;
		this.like_check = like_check;
		this.freeBoard_b_id = freeBoard_b_id;
	}
	
	
	

	public LikeVo(int freeBoard_b_idx, String freeBoard_b_id) {
		this.freeBoard_b_idx = freeBoard_b_idx;
		this.freeBoard_b_id = freeBoard_b_id;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public int getFreeBoard_b_idx() {
		return freeBoard_b_idx;
	}

	public void setFreeBoard_b_idx(int freeBoard_b_idx) {
		this.freeBoard_b_idx = freeBoard_b_idx;
	}

	public int getLike_check() {
		return like_check;
	}

	public void setLike_check(int like_check) {
		this.like_check = like_check;
	}

	public String getFreeBoard_b_id() {
		return freeBoard_b_id;
	}

	public void setFreeBoard_b_id(String freeBoard_b_id) {
		this.freeBoard_b_id = freeBoard_b_id;
	}
	
	
}
