package com.demo.demo.collections;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "address1")
public class Address {
    @Id
    private ObjectId id;
    private String local;
    private String city;
    private String country;
}
