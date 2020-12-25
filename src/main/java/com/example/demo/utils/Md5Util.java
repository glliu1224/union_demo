package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Md5Util {

    /**
     * 过期时间15分钟
     */
    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * token
     */
    private static final String TOKEN_SECRET = "65e15213-fdbb-4887-bdf1-17fa51b13d0e";



    private final static Pattern pattern = Pattern.compile("\\d+");
    private final static String charset="utf-8";

    public static void main(String[] args) {

    }

    //加密处理
    public static String md5Encode(String userName, String password){
        try {
            //得到一个指定的编码格式的字节数组，Linux和windows默认的编码格式不同，所以要指定特定的编码
            byte[] data = userName.getBytes(charset);
            byte[] keys = password.getBytes();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                //结合key和相应的数据进行加密操作,ofxx的作用是补码，byte是8bits，而int是32bits
                int n = (0xff & data[i]) + (0xff & keys[i % keys.length]);
                sb.append("@" + n);
            }
            return sb.toString();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return password;

    }

    //解密处理
    public static String decode(String src,String key) {
        if(src == null || src.length() == 0){
            return src;
        }
        //正则表达式字符串匹配
        Matcher m = pattern.matcher(src);
        List<Integer> list = new ArrayList<Integer>();
        //find方法(部分匹配):尝试去发现输入串中是否匹配相应的子串
        while (m.find()) {
            try {
                //返回匹配到的子字符串
                String group = m.group();
                list.add(Integer.valueOf(group));
            } catch (Exception e) {
                e.printStackTrace();
                return src;
            }
        }
        //如果有匹配的字符串
        if (list.size() > 0) {
            try {
                byte[] data = new byte[list.size()];
                byte[] keys = key.getBytes();
                //相对于加密过程的解密过程
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) (list.get(i) - (0xff & keys[i % keys.length]));
                }
                return new String(data, charset);
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return src;
        } else {
            return src;
        }
    }

    /**
     * 生成签名
     * @param userName 用户名
     * @param userId
     * @return 加密的token
     */
    public static String sign(String userName, String userId) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //附带userName、userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName", userName)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 验证token是否正确
     * @param token 秘钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

}
