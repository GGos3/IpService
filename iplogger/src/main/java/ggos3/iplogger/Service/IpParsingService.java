package ggos3.iplogger.Service;

import ggos3.iplogger.RequestHeader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IpParsingService implements IpService{
    String xForwardedFor;
    String remoteAddr;
    String realIP;

    @Override
    public Map<String, String> parsingIp(RequestHeader header) {
        Map<String, String> ipMap = new HashMap<>();
        getHeader(header);

        if (xForwardedFor == null || xForwardedFor.length() == 0) {
            ipMap.put("Remote-Address", remoteAddr);

            return ipMap;
        }

        String[] ipArray = xForwardedFor.split(",");
        realIP = ipArray[0];

        ipMap.put("realIP", realIP);
        ipMap.put("X-Forwarded-For", xForwardedFor);

        return ipMap;
    }
    public void getHeader(RequestHeader header) {
        this.xForwardedFor = header.getXForwardedFor();
        this.remoteAddr = header.getRemoteAddr();
    }

}
