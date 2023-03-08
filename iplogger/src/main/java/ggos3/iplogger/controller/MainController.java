package ggos3.iplogger.controller;

import ggos3.iplogger.RequestHeader;
import ggos3.iplogger.Service.IpService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {
    private final IpService ipService;
    @Autowired
    public MainController(IpService ipService) {
        this.ipService = ipService;
    }

    @RequestMapping("/")
    public Map<String, String> log(HttpServletRequest req) {
        RequestHeader header = setRequestHeader(req);
        Map<String, String> ipMap = ipService.parsingIp(header);

        return ipMap;
    }

    private static RequestHeader setRequestHeader(HttpServletRequest req) {
        RequestHeader header = new RequestHeader();
        header.setXForwardedFor(req.getHeader("X-Forwarded-For"));
        header.setRemoteAddr(req.getRemoteAddr());
        header.setUserAgent(req.getHeader("User-Agent"));

        return header;
    }

}
