package com.sapient.benchmark.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.service.feign.fallback.CandidateFeignClientFallBack;


@FeignClient(name = "candidate", fallback=CandidateFeignClientFallBack.class)
public interface CandidateFeignClient {
	
	  @GetMapping("/candidate/{emailid}")
	   public Candidate getCandidate(@PathVariable(value="emailid") String  emailid);
}
