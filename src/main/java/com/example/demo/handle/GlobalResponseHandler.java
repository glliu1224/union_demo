package com.example.demo.handle;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.demo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
*@Description 响应数据全局处理，在使用时在每个controller方法中不必定义Result包装返回数据，
 *            此类将会拦截响应数据对响应数据进行包装
*@Author glliu
*@Date 2020/12/25
*/
@Slf4j
@ControllerAdvice(basePackages = {"com.example.demo.controller"})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 注入此Bean是为了防止在响应String类型数据时，报错:Result cannot be cast to java.lang.String
     * 防止Spring使用自带转换器将响应数据转换成json时报错
     * @return
     */
    @Bean
    public HttpMessageConverters custHttpMessageConverter() {
        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
    }
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //判断支持的类型因为我们定义的BaseResponse里面的data可能是任何类型，这里就不判断同一放过
        //如果你想对执行的返回体进行操作，可以将上访的Object换成自己类型
        return true;
    }

    /**
     * 此处对返回数据做统一处理，在返回数据时，返回String类型数据会报错Result cannot be cast to java.lang.String
     * 原因是，如果接口返回的是String类型数据，Spring会使用StringHttpMessageConvert来处理返回体
     * 但是此处变成了Result.message,是一个对象，导致类型转换失败
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
        log.info("请求返回数据类型Class={}", body.getClass().getName());
        Result result = null;
        //兼容原来的接口返回
        if (body instanceof Result) {
            result = (Result) body;
        } else {
            result = new Result();
            if (null != body && !"".equals(body)) {
                result.setCode(200);
                result.setMessage("成功");
                result.setData(body);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("响应参数:{}", result.toString());
        }
        return result;
    }
}
