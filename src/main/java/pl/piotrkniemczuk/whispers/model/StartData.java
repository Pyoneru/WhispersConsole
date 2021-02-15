package pl.piotrkniemczuk.whispers.model;

import lombok.Data;

@Data
public class StartData {
    private boolean server;
    private String host;
    private int port;
    private String nickname;
}
