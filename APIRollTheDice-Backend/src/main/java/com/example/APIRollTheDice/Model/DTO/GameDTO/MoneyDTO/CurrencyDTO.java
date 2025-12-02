package com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO;

import jakarta.validation.constraints.Min;

import java.util.List;

public class CurrencyDTO {
    private Long id;
    private String name;
    private String symbol;
    private String code;
    private int baseUnit;
    private List<Long>idGameBundle;

    public CurrencyDTO() {
    }

    public CurrencyDTO(Long id, String name, String symbol, String code, int baseUnit,List<Long>idGameBundle ) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.code = code;
        this.baseUnit = baseUnit;
        this.idGameBundle=idGameBundle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Long> getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(List<Long> idGameBundle) {
        this.idGameBundle = idGameBundle;
    }
}
