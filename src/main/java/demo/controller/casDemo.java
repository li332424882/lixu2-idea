package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@Controller
@RequestMapping("new")
public class casDemo {

    private static final long serialVersionUID = 1L;

    // 当前应用登录处理页面，仅供参考，应用系统写到到配置文件中去
    private static final String LOCAL_LOGIN = "http://127.0.0.1:8085/new/index.html";
    // 当前应用登出处理页面，仅供参考，应用系统写到到配置文件中去
    private static final String LOCAL_LOGOUT = "http://10.10.35.10:8888/casDemo12/casLogout";
    // 单点登录服务器地址，仅供参考，应用系统写到到配置文件中去
    private static String CAS_SERVER = "http://sso.jingantech.com/ngiam-rst/cas";
    //http://{ssoaddress}/ngiam-rst/cas/serviceValidate
    private static final String APP_NAME = "casDemo12";

    @RequestMapping("/login")
    public String index() {
      return "views/login";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUSerByYSerNameAndPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // user为在应用系统判断用户是否已经登陆，如果已经登陆，则不需要到NGIAM认证端认证
                if (request.getSession().getAttribute("user") == null) {
                    //ST-e0c847e0-9e6e-4345-b14a-ab92678c2add
                    System.out.println(APP_NAME + " 未登陆");

                    // ticket为交换用户信息的票据
                    String ticket = request.getParameter("ticket");
                    if (ticket == null) {

                System.out.println(APP_NAME + " ticket为空，开始获取ticket");
                // 如果ticket票据为空，跳转到认证端进行认证，service为认证完后的回调地址，NGIAM认证端认证完成后，会跳转到应用端，携带ticket票据


                String redirectUrl = CAS_SERVER + "/login?service=" + LOCAL_LOGIN;
                System.out.println("redirectUrl:" + redirectUrl);
                response.sendRedirect(redirectUrl);

            } else {
                // 如果ticket不为空，则使用ticket交换用户信息，用户信息格式有xml和json两种格式
                String info_url = CAS_SERVER + "/serviceValidate?service="
                        + LOCAL_LOGIN  + "&ticket=" + ticket + "&format=json";
                // 使用ticket交换用户信息，如果使用httpclient获取用户信息，需要引入apache
                // httpcliet的jar包，至少引入 httpclient.jar 和 httpcore.jar 两个jar包
                System.out.println(APP_NAME + " ticket不为空，开始获取用户信息");
                String res = getRes(info_url);
                System.out.println("res = " + res);

                // 返回的xml示例：<?xml version="1.0"
                // encoding="UTF-8"?><serviceResponse
                // xmlns:cas="http://www.yale.edu/tp/cas"><authenticationSuccess><user>superadmin</user><attributes><userId>5a3c785278ccc5142b04dbc8</userId><userApiKey>121c8466b2720685a5c0648821a3c57c</userApiKey></attributes></authenticationSuccess></serviceResponse>
                // String accountName = getAccountNameFromXML(res);

                // 返回的json示例：
                String accountName = getAccountNameFromJSON(res);

                // 设置session
                System.out.println("accountName = " + accountName);
                request.getSession().setAttribute("user", accountName);

                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(APP_NAME + "<br>");
                writer.write("登录名:" + accountName + "<br>");
                writer.write("<a href=\"" + LOCAL_LOGOUT + "\">退出登录</a>");
                writer.write("<a href=\"" + (CAS_SERVER + "/login?service=" + LOCAL_LOGIN) + "\">测试</a>");

            }
        } else {


            System.out.println(APP_NAME + " 已登陆");

            String accountName = (String) request.getSession().getAttribute(
                    "user");

            System.out.println("user == " + accountName);

            System.out.println("accountName = " + accountName);
            request.getSession().setAttribute("user", accountName);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(APP_NAME + "<br>");
            writer.write("登录名:" + accountName + "<br>");
            writer.write("<a href=\"" + LOCAL_LOGOUT + "\">退出登录</a>");
        }

        return null;
    }

    // 获取xml str
    public String getRes(String url) throws ClientProtocolException, IOException {
        String str = null;

        HttpGet httpget = new HttpGet(url);
        HttpResponse response = HttpClientBuilder.create().build().execute(httpget);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                str = new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"), "UTF-8");
            }
        }
        return str;
    }

    // 解析json
    private String getAccountNameFromJSON(String resStr) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> userData = mapper.readValue(resStr, Map.class);
            Map<String, Object> serviceResponse = (Map<String, Object>) userData.get("serviceResponse");
            Map<String, Object> authInfo = (Map<String, Object>) serviceResponse.get("authenticationSuccess");
            String accountName = authInfo.get("user").toString();
            return accountName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
