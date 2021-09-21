package com.sapient.shipping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Entity(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = -558043294043707772L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String house;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String zip;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;

}
