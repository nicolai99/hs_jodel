package com.example.jodel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.jodel.vote.service.VoteService;
import com.example.jodel.city.model.City;
import com.example.jodel.city.service.CityService;
import com.example.jodel.exception.VoteIsAlreadySet;
import com.example.jodel.exception.RecordNotFound;
import com.example.jodel.jodel.service.JodelService;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.model.VoteType;

@ActiveProfiles("dev")
@SpringBootTest
class JodelApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	VoteService vote;

	@Autowired
	JodelService jodel;
	@Autowired
	CityService cityService;

	@Test

	void testVote() {

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote1 = vote.setVote(1, "UserId1", 1, voteType);
			System.out.println(vote1.toString());
			Vote vote2 = vote.setVote(1, "UserId1", 1, voteType);
		} catch (VoteIsAlreadySet e) {
			System.out.println(e.getMessage());

		}

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote3 = vote.setVote(1, "UserId1", 1, voteType);
			Vote vote4 = vote.setVote(1, "UserId2", 1, voteType);
			System.out.println(vote3.toString());
			System.out.println(vote4.toString());
		} catch (VoteIsAlreadySet e) {
			System.out.println(e.getMessage());
		}

		System.out.println(
				"Summe der Votes fuer den Jodel: " + vote.getSumVoteFromJodel(1));

	}

	@Test
	void testCity() {
		City city1 = new City();
		city1 = cityService.getCityById(1).orElseThrow();

		System.out.println("Distance " + cityService.getDistance(48.9396, 9.2646, city1));

		City city = new City();
		try {
			city = cityService.findByLatAndLon(48.9396, 9.266);
			System.out.println(city.getName());
		} catch (RecordNotFound e) {
			System.out.println(e.getMessage());
		}
	}
}
