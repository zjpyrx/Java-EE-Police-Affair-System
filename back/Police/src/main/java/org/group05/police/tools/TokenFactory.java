package org.group05.police.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.catalina.User;

import java.util.Calendar;
import java.util.Date;

public class TokenFactory {
    //token超时变量，N（秒）后
    public static final int TOKEN_TIMEOUT = 60 * 60;
    //  密钥
    public static final String APP_SECRET = "xxx@#$%^&dong";

    //生成token方法:传入用户名、用户ID 作为payload写入，便于后期解析
    public static String createToken(int id, String name,int authority) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, TOKEN_TIMEOUT);
        String token = JWT.create()
                .withClaim("uid", id)        //payload，写入变量，用户ID
                .withClaim("uname", name)  //payload，写入变量，用户名
                .withClaim("authority",authority)
                .withExpiresAt(instance.getTime())   //设置过期
                .sign(Algorithm.HMAC256(APP_SECRET));//签名及算法
        return token;
    }
}
