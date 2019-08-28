/*
package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
public class UserController2 {
    private static final long serialVersionUID = 1L;
    //当前应用登录页面,
    private static final String LOGIN="http://127.0.0.1:8090";
    //单点登录服务器地址,仅供参考,用用系统写到配置中去
    private static String CAS_SERVER="http://sso.jingantech.com/ngiam-rst/cas";
    //当前应用登出处理页面
    private static final String LOGOUT = "http://10.10.35.10:8888/casDemo12/casLogout";
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //user判断用户是否登录,如果已经登录则不需要nginm认证端认证
        if(request.getSession().getAttribute("user")==null){
            System.out.println("用户未登陆");

            //ticket为交换用户信息的票据
            String ticket=request.getParameter("ticket");
            if(ticket==null){
                System.out.println("ticket票据为空,获取ticket");
                //如果ticket为空,跳转到认证端认证,service为认证完后的回调地址,ngiam
                //认证完成后,会跳转到应用端,并携带ticket票据
                String redirectUrl=CAS_SERVER+"login?service="+LOGIN;
                response.sendRedirect(redirectUrl);
            }
        else{
            // 如果ticket不为空，则使用ticket交换用户信息，用户信息格式有xml和json两种格式
               String info_url = CAS_SERVER+"/serviceValidate?service="+LOGIN+"ticket="+ticket
                // 使用ticket交换用户信息，如果使用httpclient获取用户信息，需要引入apache
                // httpcliet的jar包，至少引入 httpclient.jar 和 httpcore.jar 两个jar包
                System.out.println("用户和ticket不为空，开始获取用户信息");
                String res = getRes(info_url);
                System.out.println("res = " + res);

                // 返回的xml示例：<?xml version="1.0"
                // encoding="UTF-8"?><serviceResponse
                // xmlns:cas="http://www.yale.edu/tp/cas"><authenticationSuccess><user>superadmin<
                // /user><attributes><userId>5a3c785278ccc5142b04dbc8</userId><userApiKey>121c8466b2720685a5c0648821a3c57c
                // </userApiKey></attributes></authenticationSuccess></serviceResponse>
                // String accountName = getAccountNameFromXML(res);

                // 返回的json示例：
                String accountName = getAccountNameFromJSON(res);

                // 设置session
                System.out.println("accountName = " + accountName);
                request.getSession().setAttribute("user", accountName);

                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
             */
/*  writer.write(APP_NAME + "<br>");
                writer.write("登录名:" + accountName + "<br>");
                writer.write("<a href=\"" + LOCAL_LOGOUT + "\">退出登录</a>");
                writer.write("<a href=\"" + (CAS_SERVER + "/login?service=" + LOCA*//*


            }

    }
        return null;
}
    // 获取xml str
    public String getRes(String url) throws IOException {
        String str = null ;
       */
/* 目前大大小小的项目中，都不可避免两个系统之间的通讯交互，此处简单整理一下关于HttpClient常用的HttpGet和HttpPost这两个类的编码方式。
        HttpClient常用的HttpGet和HttpPost这两个类分别对应Get方式和Post方式。
        无论是使用HttpGet，还是使用HttpPost，都必须通过如下3步来访问HTTP资源。
        1.创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
        2.使用DefaultHttpClient类的execute方法发送HTTP GET或HTTP POST请求，并返回HttpResponse对象。
        3.通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理。*//*

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = HttpClientBuilder.create().build().execute(httpGet);
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();

            if(entity==null){
                str=new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"),"utf-8");
            }
        }
        return str;
    }

    //解析 json
    private String getAccountNameFromJSON(String resStr)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String,Object> userData = mapper.readValue(resStr, Map.class);
            Map<String,Object> serviceResponse =(Map<String,Object>) userData.get("serviceResponse");
            Map<String, Object> authInfo = (Map<String, Object>) serviceResponse.get("authenticationSuccess");
            String accountName=authInfo.get("user").toString();
            return accountName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}*/
