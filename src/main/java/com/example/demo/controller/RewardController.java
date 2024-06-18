package com.example.demo.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {
		@Autowired
		private RewardService rewardservice;
		
		@GetMapping
		public Map<Long, Map<Month, Integer>> getRewards(){
			return rewardservice.calculateRewarlds();
				
		}
		
} 
