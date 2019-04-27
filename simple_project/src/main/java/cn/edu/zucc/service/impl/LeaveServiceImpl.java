package cn.edu.zucc.service.impl;

import cn.edu.zucc.domain.dao.LeaveRepository;
import cn.edu.zucc.domain.entity.LeaveApplication;
import cn.edu.zucc.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;
    @Override
    @CachePut(value = "leaveApplication",key = "#leaveApplication.id")
    public void create(LeaveApplication leaveApplication) {
        leaveRepository.save(leaveApplication);
    }
    @Override
    public void approval(LeaveApplication leaveApplication) {
        leaveRepository.save(leaveApplication);
    } //审批
    @Override
    public List<LeaveApplication> getLists(String petitioner) {
        return leaveRepository.findlists(petitioner);
    } //当前用户所有请假单列表
    @Override
    public LeaveApplication getList(Long id, String petitioner) {
        return leaveRepository.findlist(id, petitioner);
    } //当前用户所有请假单列表
    @Override
    public List<LeaveApplication> getAllLists() {
        return leaveRepository.findAll();
    } //所有用户的所有请假列表
    @Override
    public List<LeaveApplication> getAllInitiatingList() {
        return leaveRepository.findAllInitiating();
    } //所有用户的所有请假列表
}
