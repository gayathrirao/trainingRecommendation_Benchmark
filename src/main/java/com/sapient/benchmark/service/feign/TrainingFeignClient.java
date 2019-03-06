package com.sapient.benchmark.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sapient.benchmark.domain.Training;
import com.sapient.benchmark.service.feign.fallback.CandidateFeignClientFallBack;

@FeignClient(name = "training", fallback=CandidateFeignClientFallBack.class)
public interface TrainingFeignClient {

	  @GetMapping("/training/code/{code}")
	   public Training getTraining(@PathVariable(value="code") String  code);
	  @GetMapping("/training/functionaldomain/{fdomains}")
	   public List<Training> getTrainingForDomain(@PathVariable(value="fdomains") List<String>  domains);
	  @GetMapping("/training/technology/{techs}")
	  public List<Training> getTrainingByTechnology(@PathVariable("techs") List<String> techs);
	  @GetMapping("/training/roles/{roles}")
		public List<Training> getTrainingByroles(@PathVariable("roles") List<String> roles);
	  
		
}
