package com.infosys.ekart.dao;

import org.springframework.data.repository.CrudRepository;

import com.infosys.ekart.entity.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	Iterable<Subscription> findByBuyerid(Integer buyerid);
}
