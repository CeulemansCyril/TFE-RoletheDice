package com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

import java.util.List;

public class CurrencyDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Symbol")
    private String symbol;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("BaseUnit")
    private int baseUnit;
    @JsonProperty("IdGameBundle")
    private Long idGameBundle;

    public CurrencyDTO() {
    }

    public CurrencyDTO(Long id, String name, String symbol, String code, int baseUnit,Long idGameBundle ) {
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

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }
}
