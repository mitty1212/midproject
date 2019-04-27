package cn.edu.zucc.interceptor;

import cn.edu.zucc.annotation.PassToken;
import cn.edu.zucc.annotation.Role;
import cn.edu.zucc.annotation.UserLoginToken;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object object) throws Exception {

        String token = request.getHeader("token");

        String username = request.getHeader("username");


        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)object;
        Method method = handlerMethod.getMethod();

        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        if(method.isAnnotationPresent(Role.class)){
            Role role = method.getAnnotation(Role.class);

            User user = userService.getUser(username);
            System.out.println("**---Role---**");
            System.out.println("user          :"+user.getName());
            System.out.println("role.value()  :"+role.value());
            System.out.println("user.getRole():"+user.getRole());
            System.out.println(role.value().equals(user.getRole()));

            if(!role.value().equals(user.getRole())){
                throw new InvalidClientException("用户没有该权限");
            }
            return true;
        }

        if(method.isAnnotationPresent(UserLoginToken.class)){
            if(token == null){
//                System.out.println("123123");
                throw new InvalidClientException("没有Token，请重新登陆");
            }
            Jws<Claims> jwt = Jwts.parser()
                    .setSigningKey(R.KEY)
                    .parseClaimsJws(token);
            long userid = jwt.getBody().get("id",Long.class);

            User user = userService.getUser(userid);
            if(user == null){
                throw new Exception("用户不存在，请重新登陆");
            }
            if(jwt.getBody().get("exp", Date.class).before(new Date())){
                throw new Exception("登陆超时，请重新登陆");
            }
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
