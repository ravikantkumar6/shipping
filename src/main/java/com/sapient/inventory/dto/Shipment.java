package com.sapient.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@Entity(name = "Shipment")
@NoArgsConstructor
@AllArgsConstructor
public class Shipment implements Serializable {
    private static final long serialVersionUID = -558043294043707772L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private LocalDate shippingDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;

}
