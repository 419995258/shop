package com.pb.xc.controller.vo;

import java.util.List;

import com.pb.xc.util.ObjectUtil;
import com.pb.xc.util.StringContentUtil;

public class ResultVo {
	private String currentpage;
	private String pageNum; // 当前第几页
	private String pageSize; // 每页几条
	private String pages; // 总页数
	private String total; // 总记录数 jqgrid总页数
	private List rows; // 查询后的具体内容
	private List del;
	private String records; // jqgrid总记录数
	private String page;// 当前页码

	private Integer projectId;
	private Integer orgId;
	private Integer reimbursementType;
	
	private String startDate;
	private String endDate;
	private Integer type;//类型
	private Integer status;
	private Integer my;
	private Integer cn;
	private Integer queryType; //搜索框类型
	private String queryText; //搜索框字符

	private Object o;
	
	private String url;
	private int userId;//用户id
	
	
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(Integer reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public List getDel() {
		return del;
	}

	public void setDel(List del) {
		this.del = del;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMy() {
		return my;
	}

	public void setMy(Integer my) {
		this.my = my;
	}

	public Integer getCn() {
		return cn;
	}

	public void setCn(Integer cn) {
		this.cn = cn;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) throws Exception {
		this.queryText = StringContentUtil.isEmpty(queryText)? "%%" : "%" + queryText + "%" ;
	}

}
