package com.test.controller;

import com.test.dto.NodeDTO;
import com.test.model.Node;
import com.test.repository.nodeRepository;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class nodeController {

    @Autowired
    nodeRepository noderepository;
    @Autowired
    TestService service;



    @GetMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Node> getAllNodes() {


        List<Node> nodeList = service.listAllNodes();
        return nodeList;

    }


    @PostMapping("/nodes")
    public Long getNodes(@Valid @RequestBody NodeDTO node){

                return service.createNode(node).getId();

    }


    @GetMapping(value = "/nodes/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<NodeDTO> getNodeById(@PathVariable(value = "id") Long nodeId) {

                return service.searchNode(nodeId);

    }


    @PutMapping("/nodes/{id}")
    public Node updateNode(@PathVariable(value = "id") Long nodeId, @Valid @RequestBody NodeDTO nodeDetails){
                return service.updateNode(nodeId, nodeDetails);

    }

    @DeleteMapping("/nodes/{id}")
    public Long deleteNode(@PathVariable(value = "id") Long nodeId){

                return service.deleteNodes(nodeId) ;
    }

}