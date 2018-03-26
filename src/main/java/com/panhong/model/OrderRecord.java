package com.panhong.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="orderrecord")
public class OrderRecord {
	
	@Id
	@Column(name="OrderID",nullable=false,unique=true)
	@GeneratedValue
	private int orderID;
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="MachineID",referencedColumnName="machineID")
	private Machine machineID;
	
	@Transient
	private String openid;
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Column(name="chargeback")
	private String chargeback;
	
	@Column(name="isChecked")
	private String isChecked;
	
	@Column(name="isGenerated")
	private String isGenerated;
	
	public String getIsGenerated() {
		return isGenerated;
	}

	public void setIsGenerated(String isGenerated) {
		this.isGenerated = isGenerated;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getChargeback() {
		return chargeback;
	}

	public void setChargeback(String chargeback) {
		this.chargeback = chargeback;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="WechatID",referencedColumnName="openid")
	private User wechatID;
	
//	@Column(name="PaymentStatus",nullable=false)
//	private String paymentStatus;//订单支付状态
	
//	@Column(name="Amount",nullable=false)
//	private double amount;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="OrderDate",nullable=false)
//	private Date orderDate;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="PaymentDate",nullable=false)
//	private Date paymentDate;
	
	@Column(name="OrderType")
	private String orderType;
	
	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}


	@Column(name="bank_type")
	private String bank_type;//支付卡银行\
	
	@Transient
	private String attach; //设备Id
	
	@Column(name="cash_fee")
	private String cash_fee;//微信零钱支付金额
	
	@Column(name="fee_type")
	private String fee_type;//币种
	
	@Column(name="is_subscribe")
	private String is_subscribe;//是否订阅
	
	@Column(name="mch_id")
	private String mch_id;//商户号
	
	@Column(name="out_trade_no",unique=true)
	private String out_trade_no;//商户单号
	
	@Column(name="result_code")
	private String result_code;//请求prepay_id返回
	
	@Column(name="return_code")
	private String return_code;//请求sign返回
	
	@Column(name="return_msg")
	private String return_msg;//交易返回
	
	@Column(name="sign")
	private String sign;//订单请求签名
	
	@Column(name="time_end")
	private String time_end;//交易完成时间
	
	@Column(name="total_fee")
	private String total_fee;//总金额
	
	@Column(name="trade_state")
	private String trade_state;//交易状态  确定交易是否成功 判断是否退单
	
	@Column(name="trade_type")
	private String trade_type;//支付类型
	
	@Column(name="transaction_id")
	private String transaction_id; //订单编号
	
	//新增代金券字段
	@Column(name="coupon_count")
	private String coupon_count; //代金券使用数量
	
	@Column(name="coupon_fee")
	private String coupon_fee; //代金券金额
	
	@Column(name="coupon_fee_0")
	private String coupon_fee_0; //充值代金券
	
	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public String getCoupon_fee_0() {
		return coupon_fee_0;
	}

	public void setCoupon_fee_0(String coupon_fee_0) {
		this.coupon_fee_0 = coupon_fee_0;
	}

	//default constructor
	public OrderRecord(){
		
	}

//	//full constructor
//	public OrderRecord(Machine machineID, User wechatID, String paymentStatus, 
//			 Date paymentDate, String orderType) {
//		super();
//		this.machineID = machineID;
//		this.wechatID = wechatID;
//		this.paymentStatus = paymentStatus;
//		this.paymentDate = paymentDate;
//		this.orderType = orderType;
//	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Machine getMachineID() {
		return machineID;
	}

	public void setMachineID(Machine machineID) {
		this.machineID = machineID;
	}

	public User getWechatID() {
		return wechatID;
	}

	public void setWechatID(User wechatID) {
		this.wechatID = wechatID;
	}

//	public String getPaymentStatus() {
//		return paymentStatus;
//	}
//
//	public void setPaymentStatus(String paymentStatus) {
//		this.paymentStatus = paymentStatus;
//	}

//	public double getAmount() {
//		return amount;
//	}
//
//	public void setAmount(double amount) {
//		this.amount = amount;
//	}

//	public Date getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(Date orderDate) {
//		this.orderDate = orderDate;
//	}

//	public Date getPaymentDate() {
//		return paymentDate;
//	}
//
//	public void setPaymentDate(Date paymentDate) {
//		this.paymentDate = paymentDate;
//	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	
	
}
