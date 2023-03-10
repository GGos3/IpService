package ggos3.IpService.Service;

import ggos3.IpService.RequestHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.yml")
public class IpParsingService implements IpService{
    String xForwardedFor;
    String remoteAddr;
    String ip;
    @Value("${config.Forwarded}")
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
        System.out.println(Forwarded);
        int i = (ipArray.length) - Forwarded;
        ip = ipArray[i];

        ipMap.put("Ip", ip);
        ipMap.put("X-Forwarded-For", xForwardedFor);

        return ipMap;
    }

    // application.yml
    public int getProperty() {
        return Integer.parseInt(Forwarded);
    }

    public void getHeader(RequestHeader header) {
        this.xForwardedFor = header.getXForwardedFor();
        this.remoteAddr = header.getRemoteAddr();
    }
}
