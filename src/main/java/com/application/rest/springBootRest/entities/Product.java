package com.application.rest.springBootRest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "precio")
    private BigDecimal price;//bigdecimal = es igual a decimal , solo que este es mas apropiado cuando
    //trabajamos con dinero

    @ManyToOne
    @JoinColumn(name = "id_fabricante" , nullable = false)
    @JsonIgnore
    private Maker maker;
}
