package cn.edu.zucc.service;

import cn.edu.zucc.domain.entity.LeaveApplication;

import java.util.List;

public interface LeaveService {

    void create(LeaveApplication leaveApplication);
    void approval(LeaveApplication leave); //审批
    List<LeaveApplication> getLists(String petitioner); //当前用户所有请假单列表
    LeaveApplication getList(Long id, String petitioner); //当前用户所有请假单列表
    List<LeaveApplication> getAllLists(); //所有用户的所有请假列表
    List<LeaveApplication> getAllInitiatingList(); //所有用户的发起请假列表
}
