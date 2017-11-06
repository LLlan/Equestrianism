package com.fh.entity.information;

public class Advertiser {
	private String phone;//手机号
	private String password;//密码
	private String linkmanQQ;//联系人QQ
	private String advertiser_id;//主键id
	private String rolMark;//角色标识1-广告主，2-媒介主
	private double zhangHYE;//账户余额
	private double yiTiXian;//已提现 
	private double zongSY;//总收益
	private double dongJJE;//冻结金额
	private String nikeName;//昵称
	private String linkman;//联系人
	private String email;//邮箱
	private String company;//公司名称
	private String weixinAccount;//微信账号
	
	public String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public double getZhangHYE() {
		return zhangHYE;
	}
	public void setZhangHYE(double zhangHYE) {
		this.zhangHYE = zhangHYE;
	}
	public double getYiTiXian() {
		return yiTiXian;
	}
	public void setYiTiXian(double yiTiXian) {
		this.yiTiXian = yiTiXian;
	}
	public double getZongSY() {
		return zongSY;
	}
	public void setZongSY(double zongSY) {
		this.zongSY = zongSY;
	}
	public String getAdvertiser_id() {
		return advertiser_id;
	}
	public void setAdvertiser_id(String advertiser_id) {
		this.advertiser_id = advertiser_id;
	}
	public double getDongJJE() {
		return dongJJE;
	}
	public void setDongJJE(double dongJJE) {
		this.dongJJE = dongJJE;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLinkmanQQ() {
		return linkmanQQ;
	}
	public void setLinkmanQQ(String linkmanQQ) {
		this.linkmanQQ = linkmanQQ;
	}
	public String getRolMark() {
		return rolMark;
	}
	public void setRolMark(String rolMark) {
		this.rolMark = rolMark;
	}
}
