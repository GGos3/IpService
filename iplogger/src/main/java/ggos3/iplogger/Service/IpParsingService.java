package ggos3.iplogger.Service;

import ggos3.iplogger.RequestHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:ProxyForwarded.properties")
public class IpParsingService implements IpService{
    String xForwardedFor;
    String remoteAddr;
    String ip;
    @Value("${Forwarded}")
    private String Forwarded;

    @Override
    public Map<String, String> parsingIp(RequestHeader header) {
        Map<String, String> ipMap = new HashMap<>();
        getHeader(header);
        int Forwarded = getProperty();

        if (xForwardedFor == null || xForwardedFor.length() == 0) {
            if(remoteAddr.equals("0:0:0:0:0:0:0:1"))
                remoteAddr = "127.0.0.1";
            ipMap.put("Remote-Address", remoteAddr);

            return ipMap;
        }

        String[] ipArray = xForwardedFor.split(",");
        int i = (ipArray.length) - Forwarded;
        ip = ipArray[i];

        ipMap.put("Ip", ip);
        ipMap.put("X-Forwarded-For", xForwardedFor);

        return ipMap;
    }

    // ProxyForwarded.properties
    public int getProperty() {
        return Integer.parseInt(Forwarded);
    }

    public void getHeader(RequestHeader header) {
        this.xForwardedFor = header.getXForwardedFor();
        this.remoteAddr = header.getRemoteAddr();
    }
}
