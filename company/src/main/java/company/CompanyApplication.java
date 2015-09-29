package company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import io.pivotal.springcloud.ssl.CloudFoundryCertificateTruster;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class CompanyApplication {

    @RequestMapping("/available")
    public String available() {
      List<String> guides = Arrays.asList("Melchior", "Gaspar", "Balthazar");
      Random rand = new Random();

      int randomNum = rand.nextInt(guides.size());
      return guides.get(randomNum);
    }

    public static void main(String[] args) {
        CloudFoundryCertificateTruster.trustApiCertificate();
        SpringApplication.run(CompanyApplication.class, args);
    }
}
