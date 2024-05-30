package com.example.jodel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.jodel.vote.service.VoteService;
import com.example.jodel.exception.JodelException;
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

	@Test

	void testVote() {

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote1 = vote.setVote(1, "UserId1", 1, voteType);
			System.out.println(vote1.toString());
			Vote vote2 = vote.setVote(1, "UserId1", 1, voteType);
		} catch (JodelException e) {
			System.out.println(e.getMessage());

		}

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote3 = vote.setVote(1, "UserId1", 1, voteType);
			Vote vote4 = vote.setVote(1, "UserId2", 1, voteType);
			System.out.println(vote3.toString());
			System.out.println(vote4.toString());
		} catch (JodelException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(
				"Summe der Votes fuer den Jodel: " + vote.getSumVoteFromJodel(1));

	}
}
