package com.sapient.benchmark.service.feign.fallback;

import org.springframework.stereotype.Component;

@Component
public class CandidateFeignClientFallBack {
	public String getCandidate(String emailid)
	{
		return null;
	}
}
