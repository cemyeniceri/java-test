package io.unicraft.exercises.models;

import java.math.BigDecimal;

import static java.math.RoundingMode.CEILING;

public class SalesSlip {
    private BigDecimal priceWithoutDiscount;
    private BigDecimal discountPrice;
    private BigDecimal totalPrice;

    public SalesSlip(BigDecimal priceWithoutDiscount, BigDecimal discountPrice, BigDecimal totalPrice) {
        this.priceWithoutDiscount = priceWithoutDiscount.setScale(2, CEILING);
        this.discountPrice = discountPrice.setScale(2, CEILING);
        this.totalPrice = totalPrice.setScale(2, CEILING);
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(BigDecimal priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void printSlip() {
        System.out.println("\n-------------------- ### --------------------");
        System.out.println("-------------   Sales Receipt   -------------");
        System.out.println("  Price Without Discount  :  " + priceWithoutDiscount);
        System.out.println("  Discount Price          :  " + discountPrice);
        System.out.println("  Total Price             :  " + totalPrice);
        System.out.println("-------------------- ### --------------------\n");
    }
}
