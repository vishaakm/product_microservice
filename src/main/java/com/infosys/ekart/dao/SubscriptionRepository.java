package com.infosys.ekart.dao;

import org.springframework.data.repository.CrudRepository;

import com.infosys.ekart.entity.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	Iterable<Subscription> findByBuyerid(Integer buyerid);
}
