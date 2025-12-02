package com.example.APIRollTheDice.Model.Obj.Game.Money;

import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name = "currency")
public class Currency{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    private String code;
    @Min(1)
    private int baseUnit;

    @ManyToMany(mappedBy = "currencies")
    private List<GameBundle> gameBundles;

    public Currency() {
    }

    public Currency(Long id, String name, String symbol, String code, int baseUnit,List<GameBundle> gameBundle) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.code = code;
        this.baseUnit = baseUnit;
        this.gameBundles=gameBundle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(int baseUnit) {
        this.baseUnit = baseUnit;
    }

    public List<GameBundle> getGameBundles() {
        return gameBundles;
    }

    public void setGameBundles(List<GameBundle> gameBundles) {
        this.gameBundles = gameBundles;
    }
}
