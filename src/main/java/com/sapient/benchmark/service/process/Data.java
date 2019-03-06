package com.sapient.benchmark.service.process;

import java.util.List;
import java.util.Map;

import com.sapient.benchmark.domain.Candidate;
import com.sapient.benchmark.domain.Training;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Data {

	Map<String, List<Training>> recommendedTrainings;
	Candidate candidate;
	
	public Data(Map<String, List<Training>> trainings)
	{
		recommendedTrainings = trainings;
	}
	public Data(Map<String, List<Training>> trainings,Candidate c)
	{
		recommendedTrainings = trainings;
		candidate = c;
	}
}
