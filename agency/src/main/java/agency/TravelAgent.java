package agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class TravelAgent {

    @Bean
    @LoadBalanced
    public RestTemplate rest() {
      return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBackupGuide")
    public String getGuide() {
        return restTemplate.getForObject("https://company/available", String.class);
    }

    String getBackupGuide() {
        return "None available! Your backup guide is: Cookie";
    }

}
