package com.sapient.benchmark.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Prerequisites {
	private List<String> FunctionalDomain;
	private List<String> Technology; 
	private List<String> Roles;
	private List<String> UpskillFor;
}
