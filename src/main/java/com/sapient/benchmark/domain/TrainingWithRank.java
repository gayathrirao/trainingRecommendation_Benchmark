package com.sapient.benchmark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TrainingWithRank implements Comparable<TrainingWithRank>
{
	Training training;
	Integer rank;

	public TrainingWithRank(Training training, int rank) {
		super();
		this.training = training;
		this.rank = rank;
		}
	@Override
	public int compareTo(TrainingWithRank trng) {
		return trng.rank - this.rank;
	}
}