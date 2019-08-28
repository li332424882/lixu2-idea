package demo.controller;

import com.jingantech.ngiam.Format;
import com.jingantech.ngiam.ResponseBody;
import com.jingantech.ngiam.cas.CasService;
import demo.dao.UserDao;
import demo.domain.User;

import demo.service.UserService;
import demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;


    //查询所有用户
    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        System.out.println(

                userServiceImpl.findAll());

        }



    //登录校验用户
    @RequestMapping("/")
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("进去方法内部");
        //获取用户的session
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
        //获取cas登录后的票据
        String ticket2 = request.getParameter("ticket");
        System.out.println(ticket2);
        //user不为空说明已经登录过了
        if(user!=null){
            //跳转到首页
            response.sendRedirect("/hello.html");
            return;
        }
        if (ticket2 != null) {
            String url = "http://localhost:8080/ngiam-rst/cas/serviceValidate";
            String ticket = ticket2;
            String service = "http://localhost:8090";
            ResponseBody body = CasService.casValidate(url, ticket, service, Format.json);
            String user1 = body.getUser();
            System.out.println(user1);
            request.getSession().setAttribute("user",user1);
            //跳转到登录页面
            response.sendRedirect("/hello.html");
            return;

        } else {
            response.sendRedirect( "http://sso.jingantech.com/ngiam-rst/cas/login?service=http://localhost:8090");

        }
    }


//        String ticket1 = request.getParameter("url3");
//        HttpSession session = request.getSession();
//        Object user = session.getAttribute("user");
//        if(user==null){
//            System.out.println(user);
//            // response.sendRedirect("http://localhost:8080/ngiam-rst/cas/login");
//            String url = "http://localhost:8080/ngiam-rst/cas/serviceValidate";
//            String ticket = ticket1;
//            String service = "http://localhost:8090/index.html";
//            ResponseBody body = CasService.casValidate(url, ticket, service, Format.json);
//            String user1 = body.getUser();
//            System.out.println(user1);
//            request.getSession().setAttribute("user",user1);
//            //跳转到登录页面
//            return user1;
//        }
//        else{
//            System.out.println(user);
//            return user.toString();
//        }
//
//
//
//        }


        //先判断sessioon if null
     /*   String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        String url = "http://localhost:8080/ngiam-rst/cas/serviceValidate";
        String ticket = "ST-90a8ea88-9bc5-4725-b7fc-452214d99a59";
        String service = "http://localhost:8090/index.html";
        ResponseBody body = CasService.casValidate(url, ticket, service, Format.json);
        String user1 = body.getUser();
        System.out.println(user1);*/
//        UserController userController = new UserController();
//        List<User> list = userController.findAll();
//        if(list.contains(user1)){

//        }


//        try {
//            List<User> user = userServiceImpl.findUserNameAndPassword(username, password);
//            System.out.println(user);
//            if (user == null || user.size() == 0) {
//                response.sendRedirect("/register.html");
//            }
//            response.sendRedirect("/hello.html");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }




    //注册保存用户
    @RequestMapping("/register")
    public void register(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletResponse response) throws IOException {
        System.out.println(username + "" + password);
        userServiceImpl.register(username, password);
        System.out.println("用户注册成功");
        response.sendRedirect("/hello.html");
    }
    @RequestMapping("/check")
    public Boolean check(@RequestParam(name = "username") String username){
         return userServiceImpl.checkUsernsme(username);
    }
}






