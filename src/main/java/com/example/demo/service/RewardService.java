package com.example.demo.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;
@Service
public class RewardService {
		@Autowired
		private TransactionRepository transactionrepository;
		
		@Autowired
		private CustomerRepository customerrepository;
		
		public Map<Long, Map<Month, Integer>> calculateRewarlds(){
			List<Customer> customers = customerrepository.findAll();
			
			return customers.stream().collect(Collectors.toMap(
				Customer::getId,
				customer -> calculateRewardsForCustomer(customer)
					));
			
		}
		private Map<Month, Integer> calculateRewardsForCustomer(Customer customer){
			return customer.getTransactions().stream().collect(Collectors.groupingBy(
					transaction -> transaction.getDate().getMonth(),
					Collectors.summingInt(this::calculatePoints)
					));
		}
			private int calculatePoints(Transaction transaction) {
				double amount = transaction.getAmount();
				int points = 0;
			if (amount > 100) {
				points +=(amount - 100)*2;
				amount = 100;
			}
			if (amount>50) {
				points += (amount - 50);
			}
			return points;
		}
}
