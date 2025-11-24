package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Money;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Value {
    @Column(name = "amount")
    private double amount;

    @Column(name = "currency_id")
    private Long idCurrency;

    public Value() {
    }

    public Value(double amount, Long idCurrency) {
        this.amount = amount;
        this.idCurrency = idCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Long currency) {
        this.idCurrency = currency;
    }
}
