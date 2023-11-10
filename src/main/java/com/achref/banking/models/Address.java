package com.achref.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class Address extends AbstractEntity{

    private String street;
    private Integer houseNumber;
    private Integer zipcode;
    private String city;
    private String country;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
