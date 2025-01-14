package com.gescobank.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class CompteDto {
    private String devise ;
    private Double balance;
    private Long clientId;
    private Double decouvert;
    private Double tauxInteret;

}
