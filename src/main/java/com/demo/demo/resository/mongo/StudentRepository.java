package com.demo.demo.resository.mongo;

import com.demo.demo.collections.StudentCollections;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentCollections, ObjectId> {
}
