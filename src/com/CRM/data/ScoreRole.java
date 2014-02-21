package com.CRM.data;
import java.sql.Timestamp;

public class ScoreRole {
	private int id;
	private String uid;
	private int perrepostscore;
	private int percommentscore;
	private int perdaymaxscore;
	private Timestamp updatetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getPerrepostscore() {
		return perrepostscore;
	}
	public void setPerrepostscore(int perrepostscore) {
		this.perrepostscore = perrepostscore;
	}
	public int getPercommentscore() {
		return percommentscore;
	}
	public void setPercommentscore(int percommentscore) {
		this.percommentscore = percommentscore;
	}
	public int getPerdaymaxscore() {
		return perdaymaxscore;
	}
	public void setPerdaymaxscore(int perdaymaxscore) {
		this.perdaymaxscore = perdaymaxscore;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
}
