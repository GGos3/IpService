package ggos3.IpService.controller;

import ggos3.IpService.RequestHeader;
import ggos3.IpService.Service.IpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final IpService ipService;

    @RequestMapping
    public String curlResp(HttpServletRequest req) {
        RequestHeader header = setRequestHeader(req);
        String ip = ipService.parsingIp(header);

        return ip;
    }

    @RequestMapping(value = "/info")
    public Map<String, String> curlInfo(HttpServletRequest req) {
        Map<String, String> headerMap = getHeaderMap(req);

        return headerMap;
    }


    private static RequestHeader setRequestHeader(HttpServletRequest req) {
        RequestHeader header = new RequestHeader();
        header.setXForwardedFor(req.getHeader("X-Forwarded-For"));
        header.setRemoteAddr(req.getRemoteAddr());
        header.setUserAgent(req.getHeader("User-Agent"));

        return header;
    }

    private static Map<String, String> getHeaderMap(HttpServletRequest req) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        return headerMap;
    }
}
