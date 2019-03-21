/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package agency;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelAgent {

	private final RestTemplate rest;

	public TravelAgent(RestTemplate rest) {
		this.rest = rest;
	}

	@HystrixCommand(fallbackMethod = "getBackupGuide")
	public String getGuide() {
		return rest.getForObject("https://company/available", String.class);
	}

	String getBackupGuide() {
		return "None available! Your backup guide is: Cookie";
	}

}
