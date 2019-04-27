package cn.edu.zucc.domain.dao;

import cn.edu.zucc.domain.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveApplication,Long> {
    List<LeaveApplication> findlists(String petitioner);
    LeaveApplication findlist(long id, String petitioner);
    List<LeaveApplication> findAllInitiating();
}
