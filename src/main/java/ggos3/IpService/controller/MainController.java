package ggos3.IpService.controller;

import ggos3.IpService.RequestHeader;
import ggos3.IpService.Service.IpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final IpService ipService;

    @RequestMapping("/")
    public String log(HttpServletRequest req, Model model) {
        RequestHeader header = setRequestHeader(req);
        String ip = ipService.parsingIp(header);

        model.addAttribute("ip", ip);

        return "index";
    }

    @RequestMapping(value = "/", headers = "CF-Connecting-IP")
    public String xffLog(HttpServletRequest req, Model model) {
        String cloudFlareIP = req.getHeader("CF-Connecting-IP");

        model.addAttribute("ip", cloudFlareIP);

        return "index";
    }


    @RequestMapping("/info")
    public String ipInfo(HttpServletRequest req, Model model) {
        Map<String, String> headerMap = getHeaderMap(req);
        model.addAttribute("headerMap", headerMap);

        return "info";
    }

    /**
     * headerName, headerValue로 이루어진 Map을 생성해줌
     */
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

    private static RequestHeader setRequestHeader(HttpServletRequest req) {
        RequestHeader header = new RequestHeader();
        header.setXForwardedFor(req.getHeader("X-Forwarded-For"));
        header.setRemoteAddr(req.getRemoteAddr());
        header.setUserAgent(req.getHeader("User-Agent"));

        return header;
    }

}
