package com.example.jodel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.jodel.vote.service.VoteService;
import com.example.jodel.exception.JodelException;
import com.example.jodel.jodel.service.JodelService;
import com.example.jodel.vote.model.Vote;

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

	@Test

	void testVote() {

		try {
			Vote vote1 = vote.setVote(0, 1, "UserId1", 1);
			System.out.println(vote1.toString());
			Vote vote2 = vote.setVote(0, 1, "UserId1", 1);
		} catch (JodelException e) {
			System.out.println(e.getMessage());

		}

		try {
			Vote vote3 = vote.setVote(0, 1, "UserId1", -1);
			Vote vote4 = vote.setVote(0, 1, "UserId2", 1);
			System.out.println(vote3.toString());
			System.out.println(vote4.toString());
		} catch (JodelException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(
				"Summe der Votes fuer den Jodel: " + vote.getSumVoteFromJodel(jodel.getJodelById(1).orElseThrow()));

	}
}
