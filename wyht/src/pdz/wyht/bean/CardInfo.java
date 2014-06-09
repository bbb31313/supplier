package pdz.wyht.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="cardinfo")
public class CardInfo {
	
	@Id
	@Column(name="card")
	private String card;
	@Column(name="bank")
	private String bank;
	@Column(name="cdate")
	private String cate;
	@Column(name="money")
	private String money;
	@Column(name="bankname")
	private String bankname;
	
	
	
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	
	
}
