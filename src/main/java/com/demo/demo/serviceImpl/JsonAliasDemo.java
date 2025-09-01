package com.demo.demo.serviceImpl;

import com.demo.demo.dto.JsonAliasDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonAliasDemo {
    @Autowired
    private ObjectMapper objectMapper;

    public JsonNode jsonAliasSerialization() throws JsonProcessingException {
        JsonAliasDTO aliasDTO=new JsonAliasDTO("naveen","123","12345");
        JsonNode jsonNode = objectMapper.valueToTree(aliasDTO);
        System.out.println(jsonNode);
        return jsonNode;
    }
    public void jsonAliasDeserialization(JsonNode jsonNode) throws JsonProcessingException {
            JsonAliasDTO data =objectMapper.treeToValue(jsonNode,JsonAliasDTO.class);
            System.out.println(data);
        JsonNode jsonNode1 = objectMapper.valueToTree(data);
        System.out.println(jsonNode1);
    }
}
