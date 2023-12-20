package com.product.lifecycle.product.lifecycle.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {

    private ObjectId id;

    @BsonProperty(value = "product_id")
    private String productId;

    @BsonProperty(value = "product_name")
    private String name;

    @BsonProperty(value = "product_desc")
    private String description;

    @BsonProperty(value = "product_quantiry")
    private int quantity;

    private List<Features> features;


}
