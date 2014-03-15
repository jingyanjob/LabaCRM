package com.CRM;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import weibo4j.model.WeiboException;

import com.CRM.data.Reservation;
import com.CRM.data.dao.ReservationDao;
import com.CRM.systemtasks.RepostAndComment;
import com.CRM.systemtasks.WeiboTask;

public class ReservationMgr {
	ReservationDao rvd ;
	public ReservationMgr() throws Exception{
		rvd = com.config.DataApiInstance.instanceReservationDao();
	}
	public void confirmReservation(String atoken, String id, String cid) throws UnsupportedEncodingException, WeiboException{
		Reservation r = this.getReservation(id, cid);
		r.setResstatus(0);
		rvd.insert(r);
		if(!cid.equals("0000")){
			WeiboTask wt = new WeiboTask();
			wt.reservationConfirm(atoken, r.getCid(), r.getId(), "#预订成功#" + r.getText());
		}
	}
	public void newReservation(String[] text,  String buid){
		Reservation rs = new Reservation();
		rs.setBuid(buid);
		rs.setText(text[0]);
		rs.setUsername(text[1]);
		rs.setUid("0000");
		rs.setCreatedat(new Date());
		rs.setId("0000");
		rs.setResstatus(2);
		rs.setCid("0000");
		rs.setResresp("");
		rvd.insert(rs);
	}
	public Reservation[] newReservation(RepostAndComment[] rac , String buid){
		Reservation[] rs = new Reservation[rac.length];
		for(int i=0; i<rac.length; i++){
			rs[i] = new Reservation();
			rs[i] = this.getReservation(rac[i].getId(), rac[i].getCid());
			if(rs[i] == null){
				rs[i] = new Reservation();
				rs[i].setUsername(rac[i].getUser().getScreenName());
				rs[i].setBuid(buid);
				rs[i].setUid(rac[i].getUser().getId());
				rs[i].setId(rac[i].getId());
				rs[i].setResstatus(2);
				rs[i].setCreatedat(rac[i].getCreatedAt());
				rs[i].setText(rac[i].getText());
				rs[i].setResresp("");
				rs[i].setCid(rac[i].getCid());
				rvd.insert(rs[i]);
				//total++;
			}else{
				//rs[i] = this.getReservation(rac[i].getId(), rac[i].getCid());
			}
		}
		return rs;
	}
	
	public Reservation getReservation(String id, String cid){
		return rvd.getReservation(id, cid);
	}
	public Reservation[] getReservations(String buid, int resstatus){
		return rvd.getReservations(buid, resstatus);
	}
	public Reservation[] getUserReservations(String username,String buid){
		return rvd.getReservationsByUserName(username, buid);
	}
}
