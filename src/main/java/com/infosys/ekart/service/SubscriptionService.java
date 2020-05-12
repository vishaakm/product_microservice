package com.infosys.ekart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.dao.SubscriptionRepository;
import com.infosys.ekart.entity.Subscription;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public Subscription getSubscriptionById(Integer subid) {
		return subscriptionRepository.findById(subid).orElse(null);
	}

	public Subscription saveSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public Iterable<Subscription> getSubscriptions(Integer buyerid) {
		return subscriptionRepository.findByBuyerid(buyerid);
	}

	public void deleteSubscription(Subscription subscription) {
		subscriptionRepository.delete(subscription);
	}

	public Subscription updateSubscription(Subscription subscription) {
		Subscription existingSubscription = subscriptionRepository.findById(subscription.getSubId()).orElse(null);
		existingSubscription.setQuantity(subscription.getQuantity());
		
		return subscriptionRepository.save(existingSubscription);
	}
}
