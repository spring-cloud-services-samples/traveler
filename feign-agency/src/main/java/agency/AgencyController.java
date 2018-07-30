package agency;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgencyController {

	private static final String RESPONSE_TEMPLATE = "Your guide will be: %s";

	private final CompanyClient company;

	public AgencyController(CompanyClient company) {
		this.company = company;
	}

	@RequestMapping("/")
	public String guide() {
		return String.format(RESPONSE_TEMPLATE, company.availableGuide());
	}

}
