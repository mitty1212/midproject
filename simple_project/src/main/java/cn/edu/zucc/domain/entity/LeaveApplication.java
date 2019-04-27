package cn.edu.zucc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class LeaveApplication {
    @Id
    @GeneratedValue
    private Long id;
    private String Applicant;           //申请人（name）
    private String startDate; //请假开始时间（精确到小时）
    //2019-05-04-12
    private String endDate;   //请假结束时间（精确到小时）
    //2019-05-04-16
    private int dayCount;               //请假合计天数（不足半天按半天算，超过半天不足一天按一天算）
    private String Approver = "admin";  //审批人（默认为管理员）
    private String type;                //请假类型（取值事假、病假、年休、产假、丧假、护理假）
    /*
    取值事假  valueLeave             产假         maternityLeave
    病假     sickLeave              丧假         funeralLeave
    年休     Annual leave           护理假        paternityLeave
     */
    private String comment;             //批语

    //joy code
    private String state;
    private String remarks;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String applicant) {
        Applicant = applicant;
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

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public void calculateDayCount(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh");
        try {
            Date sDate=sdf.parse(this.startDate);
            Date eDate=sdf.parse(this.endDate);
            long cut = sDate.getTime() - eDate.getTime();
            this.dayCount = (int)cut/(24*60*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getApprover() {
        return Approver;
    }

    public void setApprover(String approver) {
        Approver = approver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
