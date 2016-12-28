package com.pb.xc.controller.vo;

public class FileVo {
    private Integer id;

    private String name;

    private String fileurl;

    private String filetype;

    private Long size;

    private String bucket;

    private String accessType;

    private Boolean fileStatus;

    private Integer userId;

    private Integer type;

    private Integer stuus;
    
    private Integer helpApplyId;
    
    private Integer fileId;
    
    

    public Integer getHelpApplyId() {
		return helpApplyId;
	}

	public void setHelpApplyId(Integer helpApplyId) {
		this.helpApplyId = helpApplyId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null :  fileurl.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket == null ? null : bucket.trim();
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType == null ? null : accessType.trim();
    }

    public Boolean getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Boolean fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStuus() {
        return stuus;
    }

    public void setStuus(Integer stuus) {
        this.stuus = stuus;
    }
}