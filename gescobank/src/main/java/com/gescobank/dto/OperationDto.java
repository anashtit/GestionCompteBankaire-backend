package com.gescobank.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OperationDto {



    private Long compteID;
    private String numCompteSource;

    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getCompteID() {
        return compteID;
    }

    public void setCompteID(Long compteID) {
        this.compteID = compteID;
}

}
