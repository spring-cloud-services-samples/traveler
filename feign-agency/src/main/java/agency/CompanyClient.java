package agency;

import org.springframework.stereotype.Component;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "company", fallback = CompanyClient.CompanyFallback.class)
public interface CompanyClient {
  @RequestMapping(method = GET, value="/available")
  String availableGuide();

@Component
  public static class CompanyFallback implements CompanyClient {
    @Override
    public String availableGuide() {
      return "None available!";
    }
  }
}
