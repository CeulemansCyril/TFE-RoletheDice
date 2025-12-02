package com.example.APIRollTheDice.Model.Obj.Game.LootTable;

import com.example.APIRollTheDice.Enum.LootType;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import jakarta.persistence.*;

@Embeddable
public class LootElement {

    @Enumerated(EnumType.STRING)
    @Column(name = "loot_type", nullable = false)
    private LootType Type;
    @Column(name = "max_amount", nullable = false)
    private int MaxAmount;
    @Column(name = "min_amount", nullable = false)
    private int MinAmount;
    @Column(name = "weight", nullable = false)
    private int Weight;
    @Column(name = "drop_chance", nullable = false)
    private double DropChance;

    @Column(name = "custom_objects_id")
    private Long idDropObject;
    @Embedded
    private Value value;

    public LootElement() {
    }

    public LootElement(LootType type, int minAmount, int maxAmount, int weight, double dropChance,  Long dropObject,Value value) {
        Type = type;
        MinAmount = minAmount;
        MaxAmount = maxAmount;
        Weight = weight;
        DropChance = dropChance;

        idDropObject = dropObject;
        this.value=value;
    }

    public LootType getType() {
        return Type;
    }

    public void setType(LootType type) {
        Type = type;
    }

    public int getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        MaxAmount = maxAmount;
    }

    public int getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(int minAmount) {
        MinAmount = minAmount;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public double getDropChance() {
        return DropChance;
    }

    public void setDropChance(double dropChance) {
        DropChance = dropChance;
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
