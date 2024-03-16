package com.application.rest.springBootRest.controllers.dto;
import com.application.rest.springBootRest.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDTO {

    private long id;

    private String name;

    private List<Product> productlist = new ArrayList<>();

}
