package com.sapient.benchmark.service.process.ranking;

import java.util.Set;

import com.sapient.benchmark.domain.TrainingWithRank;
import com.sapient.benchmark.service.process.Data;

public interface Ranker {
	Set<TrainingWithRank> rank(Data data);
}
