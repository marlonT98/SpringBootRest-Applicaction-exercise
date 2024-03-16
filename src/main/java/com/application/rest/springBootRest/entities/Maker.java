package com.application.rest.springBootRest.entities;
//maker = fabricante -genera productos

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fabricante")
public class Maker {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre")
    private String name;

    //uno a muchos <- un fabricante puede crear muchos productos
    @OneToMany(mappedBy = "maker" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    @JsonIgnore
    private List<Product> productlist = new ArrayList<>();


}
