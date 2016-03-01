package agency;

import org.springframework.stereotype.Component;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("https://company")
interface CompanyClient {
  @RequestMapping(value="/available", method = GET)
  String availableGuide();
}
