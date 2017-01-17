package com.pb.xc.controller.vo;

import java.util.Date;

public class BuyVo {
    private Integer id;

    private Integer userId;

    private Integer state;

    private Date time;

    private Double money;
    
    private String note;
    
    private String strTime;//时间的string格式
    
    private String address;//用户地址
    
    private String name;//用户姓名
    
    private String tel;//用户电话
    
    private String username;//用户名
    
    
    
    

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}