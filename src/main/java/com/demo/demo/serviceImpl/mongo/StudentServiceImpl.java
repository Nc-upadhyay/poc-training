package com.demo.demo.serviceImpl.mongo;

import com.demo.demo.collections.Address;
import com.demo.demo.collections.StudentCollections;
import com.demo.demo.resository.mongo.AddressRepository;
import com.demo.demo.resository.mongo.StudentRepository;
import com.demo.demo.serviceImpl.redis.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisService redisService;


    public StudentCollections save(StudentCollections studentCollections) {
        List<Address> address = addressRepository.saveAll(studentCollections.getAddress());
        studentCollections.setAddress(address);
        return studentRepository.save(studentCollections);
    }


    public List<StudentCollections> saveAll(List<StudentCollections> list) {
        for (StudentCollections st : list) {
            List<Address> address = st.getAddress();
            addressRepository.saveAll(address);
            st.getAddress().clear();
            st.setAddress(address);
//            studentRepository.save(st);
        }
        return studentRepository.saveAll(list);
    }

    public List<StudentCollections> getAll() {
        List<StudentCollections> studentCollections =studentRepository.findAll();
        return studentCollections;
    }

    public StudentCollections findById(ObjectId id) throws JsonProcessingException {
        StudentCollections studentCollectionss = redisService.get(id.toString(),StudentCollections.class);
        if (studentCollectionss != null) {
            return studentCollectionss;
        } else {
            StudentCollections studentCollections = studentRepository.findById(id).orElse(null);
            if (studentCollections != null)
                redisService.set(id.toString(), new ObjectMapper().writeValueAsString(studentCollections));

            return studentCollections;
        }
    }

    public StudentCollections updateById(StudentCollections dto) {
        StudentCollections student = studentRepository.findById(dto.getId()).orElse(null);
        if (student != null) {
            if (dto.getName() != null && !dto.getName().isEmpty()) {
                student.setName(dto.getName());
            }

            if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
                student.setAddress(dto.getAddress());
            }
        }
        return student != null ? studentRepository.save(student) : null;

    }

    public String deleteById(ObjectId id) {
        StudentCollections studentCollections = studentRepository.findById(id).orElse(null);
        if (studentCollections != null) {
            studentRepository.deleteById(id);

            addressRepository.deleteAll(studentCollections.getAddress());
        }
        return "Deleted";
    }

    @Transactional(rollbackFor = Exception.class)
    public StudentCollections saveByAddress(Address address, ObjectId id) throws Exception {
        StudentCollections student = studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found with id: " + id));

        Address savedAddress = addressRepository.save(address);
        student.getAddress().add(savedAddress);

        student.setEmail(null); // only if required
        int as = 3 / 0;

        return studentRepository.save(student);
    }

    // Below is the example of Query & Criteria
    public List<StudentCollections> getAllStudentByAge(int age) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gt(age).andOperator(Criteria.where("id").is("12")));
        return mongoTemplate.find(query, StudentCollections.class);
    }

    public List<StudentCollections> getByName(String name, String name2) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().orOperator(
                        Criteria.where("name").is(name),
                        Criteria.where("name").is(name2)
                )
        );
        return mongoTemplate.find(query, StudentCollections.class);
    }

}
