package cn.jxust.partTimeJob.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 兼职信息实体类
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Job {

    private int id;

    private String jobName; //职称名字

    private String detail; //职位描述

    private String address; //工作地点

    private String postTime; //发布时间

    private String jobType; //兼职类型

    private String name; //联系人名字

    private String phone; //联系人电话

    private String email; //联系人邮箱

    @JsonIgnore
    private int isShow; //是否显示,1为显示,-1为不显示

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", detail='" + detail + '\'' +
                ", address='" + address + '\'' +
                ", postTime='" + postTime + '\'' +
                ", jobType='" + jobType + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isShow=" + isShow +
                '}';
    }

    @JsonIgnore
    public boolean isEmpty() {
        return jobName == null && detail == null && address == null && postTime == null && jobType == null && name == null && phone == null && email == null && isShow == 0;
    }
}
