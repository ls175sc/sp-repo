package com.tedu.sp11.ServiceFallback;

import com.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class ItemServiceFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        //當執行item-service失敗，應用當前這個降級類
        //*和null都表示所有微服務失敗都應用當前降級類
        //"*";   //null
        return "item-service";
    }

    //該方法返回封裝降級響應的對象，ClientHttpResponse中封裝降級響應
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return response();
    }

    private ClientHttpResponse response() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                log.info("fallback body");
                String s = JsonResult.error().msg("商品後臺服務錯誤").toString();
                return new ByteArrayInputStream(s.getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
