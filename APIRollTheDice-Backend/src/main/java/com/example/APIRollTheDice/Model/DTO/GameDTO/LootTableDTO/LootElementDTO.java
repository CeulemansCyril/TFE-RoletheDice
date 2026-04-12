package com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO;

import com.example.APIRollTheDice.Enum.LootType;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LootElementDTO {
    @JsonProperty("Type")
    private LootType type;
    @JsonProperty("MinAmount")
    private int minAmount;
    @JsonProperty("MaxAmount")
    private int maxAmount;
    @JsonProperty("Weight")
    private int weight;
    @JsonProperty("DropChance")
    private double dropChance;
    @JsonProperty("IdDropObject")
    private Long idDropObject;
    @JsonProperty("IdDropMoney")
    private Long idDropMoney;
    @JsonProperty("Value")
    private Value value;

    public LootElementDTO() {
    }

    public LootElementDTO(LootType type, int minAmount, int maxAmount, int weight, double dropChance, Long idDropObject, Long idDropMoney, Value value) {
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.weight = weight;
        this.dropChance = dropChance;
        this.idDropObject = idDropObject;
        this.idDropMoney = idDropMoney;
        this.value =value;
    }

    public LootType getType() {
        return type;
    }

    public void setType(LootType type) {
        this.type = type;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getDropChance() {
        return dropChance;
    }

    public void setDropChance(double dropChance) {
        this.dropChance = dropChance;
    }

    public Long getIdDropMoney() {
        return idDropMoney;
    }

    public void setIdDropMoney(Long idDropMoney) {
        this.idDropMoney = idDropMoney;
    }

    public Long getIdDropObject() {
        return idDropObject;
    }

    public void setIdDropObject(Long idDropObject) {
        this.idDropObject = idDropObject;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
