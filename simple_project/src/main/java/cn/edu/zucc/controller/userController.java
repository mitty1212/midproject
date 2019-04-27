package cn.edu.zucc.controller;

//import com.coding.studiodemo.User.R;
//import com.coding.studiodemo.User.User;
//import com.coding.studiodemo.User.UserRepository;
//import antlr.StringUtils;
import cn.edu.zucc.annotation.PassToken;
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
@RequestMapping(value="/users")
public class userController {

//    @Autowired
//    UserRepository service;
//

//
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public R<String> postUser(@ModelAttribute User user){
//        service.save(user);
//        return R.success("success");
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public R<User> getUser(@PathVariable Long id){
//        return R.data(service.findById(id).get());
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
//    public R<String> putUser(@PathVariable Long id,@ModelAttribute User user){
//        user.setId(id);
//        service.save(user);
//        return R.success("success");
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public R<String> deleteUser(@PathVariable Long id){
//        service.deleteById(id);
//        return R.success("success");
//    }
//
//    @RequestMapping(value = "/findByNameLike/{pattern}",method = RequestMethod.GET)
//    public R<List<User>> getAllUsersByLike(@PathVariable String pattern){
////        return R.data(service.findById(id).get());
//        return R.data(service.findByNameLike(pattern));
////        service.findByNameLike();
//    }




    //----old2 code
    @Autowired
    UserService service;

    @UserLoginToken
    @RequestMapping(value="/",method = RequestMethod.GET)
    public R<List<User>> getUserList(){
        //  /users/  get 用户获取用户列表
        return R.data(service.getAllUsers());
    }
//    @RequestMapping(value="/",method = RequestMethod.POST)
//     public R<String> postUser(@ModelAttribute User user){
//        // /users/ post 用户创建User
//        service.create(user.getName(),user.getAge());
//        return R.success("succes");
//     }
    @UserLoginToken
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
     public R<User> getUser(@PathVariable Long id){
        // /users/{id} users中id为id的user的信息
        return R.data(service.getUser(id));
     }

//     @RequestMapping(value="/{id}",method = RequestMethod.PUT)
//    public R<String> putUser(@PathVariable Long id,@ModelAttribute User user){
//        // /users/{id} put 用于更新用户信息
//         service.update(id,user.getName(),user.getAge());
//        return R.success("success");
//    }

    //------    3-28 start
    @UserLoginToken
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public R<String> deleteUSer(@PathVariable Long id){
        // /users/{id} delete 用于删除user
        service.deleteById(id);
        return R.success("success");
    }
//    @UserLoginToken
    @PassToken
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public R<String> loginUser(@RequestParam String name,@RequestParam String password){
//        User user = service.getUser(name,password);
        User user = service.getUser(name);
        if(user==null){
            return R.fail("用户不存在");
        }
//        System.out.println(user.getName());
//        System.out.println(user.getPassword());
//        System.out.println(user.getSalt());
//        System.out.println(HashKit.md5(password+user.getSalt()));
        return user.getPassword().equals(HashKit.md5(password+user.getSalt()))?R.Loginsuccess(user):R.fail("密码错误");
//        return user == null?R.fail("用户不存在或密码错误"):R.Loginsuccess(user);
    }
    @PassToken
    @RequestMapping(value = "/changepw",method = RequestMethod.POST)
    public R<String> changePassword(@RequestParam String name,@RequestParam String password,
                                    @RequestParam String newpassword){
//        System.out.println(name);
//        System.out.println(password);
//        System.out.println(newpassword);
        User user = service.getUser(name);
        user.setSalt(HashKit.generateSalt(8));
        user.setPassword(HashKit.md5(newpassword+user.getSalt()));
        service.update(user);
        return R.success("密码修改成功");
    }

    //------   3-28 end


    //----old2 code
    // --------old code
//    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,User>());
//
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public List<User> getUserList(){
//        List<User> r = new ArrayList<User>(users.values());
//        return  r;
//    }
//
//
    @UserLoginToken
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public R<String> postUser(@Valid @ModelAttribute User user){//@RequestBody
//        users.put(user.getId(),user);
//        if(StringUtils.isEmpty(user.getName())){
//            throw new NullPointerException("用户名不能为空");
//        }
        user.setSalt(HashKit.generateSalt(8));
        user.setPassword(HashKit.md5(user.getPassword()+user.getSalt()));
        service.create(user);
        return R.success("success");
    }
//
//    @RequestMapping(value="/{id}",method = RequestMethod.GET)
//    public User getUser(@PathVariable Long id){
//        return users.get(id);
//    }
//
//
//    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
//    public String putUser(@PathVariable Long id,@ModelAttribute User user){
//        User u = users.get(id);
//        u.setName(user.getName());
//        u.setAge(user.getAge());
//        u.setMonthincome(user.getMonthincome());
//        users.put(id,u);
//        return "success";
//    }
//
//    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
//    public String deleteUSer(@PathVariable Long id){
//        users.remove(id);
//        return "success";
//    }
//    @RequestMapping(value="/yearincome",method=RequestMethod.POST)
//    public R<List<User>> yearincome(){
//        List<User> list=new ArrayList<User>(users.values());
//        list.stream().forEach(user->user.setYearincome(user.getMonthincome()*12));
//
//        return R.data(list);
//    }

    //--------old code
}
