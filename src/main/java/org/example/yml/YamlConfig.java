package org.example.yml;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YamlConfig {

    @Value("${time_interval}")
    private String timeInterval;

    public void parseTime() {
        System.out.println("Parsing...");
        System.out.println("timeInterval = " + timeInterval);
    }

}
