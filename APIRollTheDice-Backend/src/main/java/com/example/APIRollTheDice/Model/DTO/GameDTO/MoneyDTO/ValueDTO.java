package com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueDTO {
    @JsonProperty("Amount")
    private double amount;
    @JsonProperty("CurrencyId")
    private Long currencyId;

    public ValueDTO() {
    }

    public ValueDTO(double amount, Long currencyId) {
        this.amount = amount;
        this.currencyId = currencyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
