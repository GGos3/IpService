package ggos3.iplogger.Service;

import ggos3.iplogger.RequestHeader;

import java.util.Map;

public interface IpService {
    Map<String, String> parsingIp(RequestHeader header);
}
