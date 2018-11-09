package wfy.jxnu.mo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Order implements Serializable {

	private String id; // 订单号
	private float money; // 金额
	private String receiverinfo; // 收货人信息
	private int paystate; // 支付状态
	private Timestamp ordertime; // 下单时间
	private int userId; // 下单用户
	private List<OrderItem> items;// 订单中订单项
	private int totalNum; //商品总数量

	// 添加用户昵称和用户名字段
	private String username;
	private String nickname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getReceiverinfo() {
		return receiverinfo;
	}

	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}


	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
}
