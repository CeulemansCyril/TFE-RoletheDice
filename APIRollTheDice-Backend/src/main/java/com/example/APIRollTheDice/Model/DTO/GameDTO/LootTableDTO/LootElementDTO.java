package com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO;

import com.example.APIRollTheDice.Enum.LootType;

public class LootElementDTO {
    private LootType type;
    private int minAmount;
    private int maxAmount;
    private int weight;
    private double dropChance;

    private Long idDropObject;
    private Long idDropMoney;

    public LootElementDTO() {
    }

    public LootElementDTO(LootType type, int minAmount, int maxAmount, int weight, double dropChance, Long idDropObject, Long idDropMoney) {
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.weight = weight;
        this.dropChance = dropChance;
        this.idDropObject = idDropObject;
        this.idDropMoney = idDropMoney;
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
}
