package cn.edu.zucc.controller;

import cn.edu.zucc.annotation.PassToken;
import cn.edu.zucc.annotation.Role;
import cn.edu.zucc.annotation.UserLoginToken;
import cn.edu.zucc.common.HashKit;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/mid")
public class middleworkController {

    @Role("admin")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "123";
    }
}
