package cn.edu.zucc.controller;

import cn.edu.zucc.annotation.Role;
import cn.edu.zucc.annotation.UserLoginToken;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.LeaveApplication;
//import cn.edu.zucc.service.LeaveService;
import cn.edu.zucc.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/leave")
public class leaveController {

    @Autowired
    LeaveService leaveService;

    @Role("admin")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "123";
    }
    //发起请假user请假
    @Role("user")
    @RequestMapping(value = "/leaveOrder",method = RequestMethod.POST)
    public R<String> changePassword(@ModelAttribute LeaveApplication leaveApplication){
        System.out.println("/leaveOrder:create a LeaveApplication");
        leaveService.create(leaveApplication);
        return R.success("请假单创建成功");
    }
    @Role("admin")
    @RequestMapping(value = "/approve",method = RequestMethod.POST)
    //审批（管理员修改状态为通过或者不通过，并填写批语）
    public R<String> Approve(@RequestParam String name){

        return R.success("管理员修改成功!");
    }

    @UserLoginToken
    @RequestMapping(value="/", method = RequestMethod.GET) //所有用户的所有请假列表（限管理员）
    public R<List<LeaveService>> getLists() {
        return R.data(leaveService.getAllLists());
    }

    @UserLoginToken
    @RequestMapping(value="/Pending", method = RequestMethod.GET) //所有用户的发起请假列表（限管理员）
    public R<List<LeaveService>> getAllInitiatingList() {
        return R.data(leaveService.getAllInitiatingList());
    }

    @UserLoginToken
    @RequestMapping(value="/{name}", method = RequestMethod.GET) //当前用户所有请假单列表
    public R<List<LeaveService>> getLists(@PathVariable String name) {
        return R.data(leaveService.getLists(name));
    }

    @UserLoginToken
    @RequestMapping(value="/create", method = RequestMethod.POST) //发起请假
    public R<String> postLeave(@Valid @ModelAttribute LeaveApplication leaveApplication) {
        leaveService.create(leaveApplication);
        return R.success("success");
    }

    @Role("admin")
    @UserLoginToken
    @RequestMapping(value="/approval", method = RequestMethod.PUT) //审批
    public R<String> putLeave(@RequestParam Long id, @RequestParam String name, @RequestParam String state, @RequestParam String remarks) {
        LeaveApplication leaveApplication = leaveService.getList(id, name);
        leaveApplication.setState(state);
        leaveApplication.setRemarks(remarks);
        leaveService.approval(leaveApplication);
        return R.success("success");
    }
}
