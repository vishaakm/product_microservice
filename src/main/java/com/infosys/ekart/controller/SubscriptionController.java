package com.infosys.ekart.controller;

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

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping()
	public Iterable<Subscription> getSubscription(@RequestParam(name = "buyerid") Integer buyerId) {
		return subscriptionService.getSubscriptions(buyerId);
	}

	@PostMapping("/add")
	public Subscription addSubscription(@RequestBody SubscriptionDTO subscription) {
		return subscriptionService.saveSubscription(subscription.convertToEntity());
	}

	@DeleteMapping("/remove")
	public String removeSubscription(@RequestBody SubscriptionDTO subscription) {
		subscriptionService.deleteSubscription(subscription.convertToEntity());

		if (subscriptionService.getSubscriptionById(subscription.getSubId()) == null) {
			return "Success";
		}
		return "Failed";
	}

	@PutMapping("/update")
	public Subscription updateSubscription(@RequestBody SubscriptionDTO subscription) {
		return subscriptionService.updateSubscription(subscription.convertToEntity());
	}
}
