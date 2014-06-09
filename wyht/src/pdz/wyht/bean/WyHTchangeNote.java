package pdz.wyht.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
* <p>Title: WyHTchangeNote.java</p>
* <p>Description:合同变更操作记录表</p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company:pdz</p>
* @author pdz
* @date 2014-3-26 上午10:40:49
* @version V1.0
*/
@Entity
@Table(name="WY_HTCHANGE_NOTES")
public class WyHTchangeNote implements java.io.Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NID")
	private String nid;
	@Column(name="USERNAME")
	private String username;
	@Column(name="USERID")
	private String userid;
	@Column(name="htid")
	private String htid;
	@Column(name="zlhtid")
	private String zlhtid;
	@Column(name="changestyle")
	private String changestyle;
	@Column(name="changecontent")
	private String changecontent;
	@Column(name="changedate")
	private String changedate;
	
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public String getZlhtid() {
		return zlhtid;
	}
	public void setZlhtid(String zlhtid) {
		this.zlhtid = zlhtid;
	}
	public String getChangestyle() {
		return changestyle;
	}
	public void setChangestyle(String changestyle) {
		this.changestyle = changestyle;
	}
	public String getChangecontent() {
		return changecontent;
	}
	public void setChangecontent(String changecontent) {
		this.changecontent = changecontent;
	}
	public String getChangedate() {
		return changedate;
	}
	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}
	

}