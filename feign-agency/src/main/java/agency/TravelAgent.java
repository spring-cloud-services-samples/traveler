package agency;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TravelAgent {

    @Autowired
    CompanyClient company;

    @HystrixCommand(fallbackMethod = "getBackupGuide")
    public String getGuide() {
        return company.availableGuide();
    }

    String getBackupGuide() {
        return "None available! Your backup guide is: Cookie";
    }

}
