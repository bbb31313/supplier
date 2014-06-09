package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wy_time")
public class WyTime {

	@Id
	@Column(name="TIMENAME")
	private String timename;
	@Column(name="TIMEMODE")
	private Long timemode;
	
	
	public String getTimename() {
		return timename;
	}
	public void setTimename(String timename) {
		this.timename = timename;
	}
	public Long getTimemode() {
		return timemode;
	}
	public void setTimemode(Long timemode) {
		this.timemode = timemode;
	}
}
