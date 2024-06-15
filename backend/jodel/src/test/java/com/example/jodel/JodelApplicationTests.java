package com.example.jodel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.jodel.vote.service.VoteService;
import com.example.jodel.city.model.City;
import com.example.jodel.city.service.CityService;
import com.example.jodel.exception.VoteIsAlreadySet;
import com.example.jodel.exception.RecordNotFound;
import com.example.jodel.jodel.service.JodelService;
import com.example.jodel.vote.model.Vote;
import com.example.jodel.vote.model.VoteType;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
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

	@Autowired
	private MockMvc mockMvc;

	@Test

	void testVote() {

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote1 = vote.setVote(1, "60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf", 1, voteType);
			System.out.println(vote1.toString());
			Vote vote2 = vote.setVote(1, "60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf", 1, voteType);
		} catch (VoteIsAlreadySet e) {
			System.out.println(e.getMessage());

		}

		try {
			VoteType voteType = VoteType.jodel;
			Vote vote3 = vote.setVote(1, "60a6cae6-5fb4-4d50-9d16-ced3b3cc82cf", 1, voteType);
			Vote vote4 = vote.setVote(1, "6b6dc989-8ade-493a-a459-eddc42fd4671", 1, voteType);
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

	@Test
	@WithMockUser(username = "nicolai", roles = { "USER" })
	void testJodelWithDistance() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/jodel/api/jodelwithdistance?lat=48.72&lon=9.3&maxdistance=50"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@WithMockUser(username = "nicolai", roles = { "USER" })
	void testJodel() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/jodel/api/jodel"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	@WithMockUser(username = "nicolai", roles = { "USER" })
	void City() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/jodel/api/city"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

}
