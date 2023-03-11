package ggos3.IpService.Service;

import ggos3.IpService.RequestHeader;

import java.util.Map;

public interface IpService {
    Map<String, String> parsingIp(RequestHeader header);
}
