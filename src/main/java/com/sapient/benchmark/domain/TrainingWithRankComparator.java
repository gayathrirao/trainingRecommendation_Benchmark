package com.sapient.benchmark.domain;

import java.util.Comparator;

public class TrainingWithRankComparator implements Comparator<TrainingWithRank>{

	@Override
	public int compare(TrainingWithRank o1, TrainingWithRank o2) {
		return o1.getRank() - o2.getRank();
	}

}
