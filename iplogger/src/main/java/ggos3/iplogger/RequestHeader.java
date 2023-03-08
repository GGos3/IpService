package ggos3.iplogger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class RequestHeader {
    private String XForwardedFor;
    private String RemoteAddr;
    private String UserAgent;
}
