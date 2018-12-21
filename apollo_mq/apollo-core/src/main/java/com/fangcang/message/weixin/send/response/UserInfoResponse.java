package com.fangcang.message.weixin.send.response;

import java.io.Serializable;

public class UserInfoResponse extends BaseResponse implements Serializable {

	private static final long serialVersionUID = -7238058412417847331L;

	/**
	 * 微信用户openid
	 */
	private String openid;

	/**
	 * 是否订阅
	 */
	private String subscribe;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 用户的语言
	 */
	private String language;

	/**
	 * 用户头像
	 */
	private String headimgurl;

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	private String subscribe_time;

	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	private String unionid;

	/**
	 * 公众号运营者对粉丝的备注
	 */
	private String remark;

	/**
	 * 用户所在的分组ID
	 */
	private String groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}
