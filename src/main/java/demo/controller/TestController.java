package demo.controller;


import com.jingantech.ngiam.Format;
import com.jingantech.ngiam.ResponseBody;
import com.jingantech.ngiam.cas.CasService;
import demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class TestController {

    public static void main(String[] args) throws IOException {
//        String url = "http://localhost:8080/ngiam-rst/cas/serviceValidate";
//        String ticket = "ST-e9c7002a-8b3a-480d-8de3-9475c063d24d";
//        String service = "third-party-sso";
//        ResponseBody body = CasService.casValidate(url, ticket, service, Format.json);
//        System.out.println(body.getUser());
//        System.out.println(body.getAttributes().get("userApiKey"));
    }

}






