package com.infosys.ekart.dto;

import com.infosys.ekart.entity.Subscription;

public class SubscriptionDTO {

    private Integer subId;
    private Integer buyerid;
    private Integer prodid;
    private Integer quantity;

    public Integer getBuyerid() {
        return buyerid;
    }

    public Integer getProdid() {
        return prodid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setBuyerid(Integer buyerId) {
        this.buyerid = buyerId;
    }

    public void setProdid(Integer prodId) {
        this.prodid = prodId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Subscription convertToEntity(){
        Subscription subscription = new Subscription();

        subscription.setBuyerid(this.getBuyerid());
        subscription.setProdid(this.getProdid());
        subscription.setQuantity(this.getQuantity());

        return subscription;
    }
}
