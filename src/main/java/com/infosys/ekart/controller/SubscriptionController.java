package com.infosys.ekart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.dto.SubscriptionDTO;
import com.infosys.ekart.entity.Subscription;
import com.infosys.ekart.service.SubscriptionService;

@RestController
@RequestMapping("api/subscriptions")
public class SubscriptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping()
	public Iterable<Subscription> getSubscription(@RequestParam(name = "buyerid") Integer buyerId) {
		LOGGER.info("Retrive Subscription For buyer " + buyerId);
		return subscriptionService.getSubscriptions(buyerId);
	}

	@PostMapping("/add")
	public Subscription addSubscription(@RequestBody SubscriptionDTO subscription) {
		LOGGER.info("Add Subscription For buyer " + subscription.getBuyerid());

		return subscriptionService.saveSubscription(subscription.convertToEntity());
	}

	@DeleteMapping("/remove")
	public String removeSubscription(@RequestBody SubscriptionDTO subscription) {
		subscriptionService.deleteSubscription(subscription.convertToEntity());

		if (subscriptionService.getSubscriptionById(subscription.getSubId()) == null) {
			LOGGER.info("Remove Subscription For buyer " + subscription.getBuyerid());

			return "Success";
		}
		return "Failed";
	}

	@PutMapping("/update")
	public Subscription updateSubscription(@RequestBody SubscriptionDTO subscription) {
		LOGGER.info("Update Subscription For buyer " + subscription.getBuyerid());
		return subscriptionService.updateSubscription(subscription.convertToEntity());
	}
}
