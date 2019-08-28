package demo.domain;

import java.util.Map;

/**
 * Created by fuzhong on 2017/1/9.
 */

/**
 * 账户信息
 */
public class ResponseBody {

    /** 账户名 */
    private String user;

    /** 其他属性 */
    private Map<String, String> attributes;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
