package com.tedu.sp11.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter {
    //是否要對該次請求進行過濾
    @Override
    public boolean shouldFilter() {
        //對指定的ServiceId過濾，如果要過濾所有服務，直接返回true
        RequestContext context = RequestContext.getCurrentContext();
        String serviceId = (String) context.get(FilterConstants.SERVICE_ID_KEY);
        if (serviceId.equals("item-service")) return true;
        return false;
    }

    //具體過濾代碼
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String at = request.getParameter("token");
        if (at == null) {
            //此設置會阻止請求被路由到後臺微服務
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(200);
            context.setResponseBody(JsonResult.error().code(JsonResult.NOT_LOGIN).toString());
            log.info("Not login~!");
        }
        //zuul過濾器返回的數據設計為以後擴展使用，目前該返回值沒有被使用
        return null;
    }

    @Override
    public String filterType() {
        //設置為前置過濾
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //該過濾器順序要>5，才能得到service id
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }
}
