package ggos3.IpService.Service;

import ggos3.IpService.RequestHeader;
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

    /**
     * 헤더값을 받아 실제 ip를 추출해 준다.
     *
     * @param header Remote-Address, X-Forwarded-For
     * @return ip
     */

    @Override
    public String parsingIp(RequestHeader header) {
        getHeader(header);
        int ProxyCount = getProperty();

        /*
        XFF 헤더가 비어있을때 remoteAddr를 반환하고,
        만약 remoteAddr이 ipv6 형태의 localhost라면 ipv4 형태로 변환해준다
        */
        if (xForwardedFor == null || xForwardedFor.length() == 0) {
            if(remoteAddr.equals("0:0:0:0:0:0:0:1"))
                remoteAddr = "127.0.0.1";

            return remoteAddr;
        }

        String[] ipArray = xForwardedFor.split(",");
        int i = (ipArray.length) - ProxyCount;

        try {
            ip = ipArray[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            ip = ipArray[i - 1];
        }

        return ip;
    }

    // application.yml 에서 환경변수를 불러온다.
    public int getProperty() {
        try {
            Integer.parseInt(Forwarded);
        } catch (NumberFormatException e) {
            return 0;
        }

        return Integer.parseInt(Forwarded);
    }

    public void getHeader(RequestHeader header) {
        this.xForwardedFor = header.getXForwardedFor();
        this.remoteAddr = header.getRemoteAddr();
    }
}
