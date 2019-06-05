package com.test.service;

import com.test.dto.NodeDTO;
import com.test.model.Node;
import com.test.repository.nodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private nodeRepository repository;


    public List<Node> listAllNodes(){



       List<Node> listNodes  = (List<Node>) repository.findByParentId(null);

        return listNodes;
    }


    public Node createNode(@Valid NodeDTO node) {

        Node nodeEntity = new Node();

        nodeEntity.setDetail(node.getDetail());
        nodeEntity.setCode(node.getCode());
        nodeEntity.setParent(node.getParentid() == null ? null : repository.findOne(node.getParentid()));


        return repository.save(nodeEntity);

    }

       public Node updateNode(Long nodeID, @Valid NodeDTO nodeDTO){


            Node node = repository.findOne(nodeID);
            

                node.setDetail(nodeDTO.getDetail() == null ? node.getDetail() : nodeDTO.getDetail());
                node.setCode(nodeDTO.getCode() == null ? node.getCode() : nodeDTO.getCode());
                node.setParent(nodeDTO.getParentid() == null ? node.getParent() : repository.findOne(nodeDTO.getParentid()));


                return repository.save(node);

        }


        public List<NodeDTO> searchNode (final Long nodeID){

            List<NodeDTO> node = convertToChildDTOList(repository.findByParentId(nodeID));

                return node;

        }


    public List<NodeDTO> convertToChildDTOList(final List<Node> returnedNodeList) {
        final List<NodeDTO> nodeChildDTOList = new ArrayList<>();

        for (final Node node : returnedNodeList) {
            final NodeDTO nodeChildDTO = new NodeDTO();
            nodeChildDTO.setId(node.getId());
            nodeChildDTO.setCode(node.getCode());
            nodeChildDTO.setDetail(node.getDetail());

            if (node.getChildren() == null) {
                nodeChildDTO.setHasChildren(false);
            } else {
                nodeChildDTO.setHasChildren(!node.getChildren().isEmpty());
            }

            nodeChildDTO.setParentid(node.getParent().getId());
            nodeChildDTOList.add(nodeChildDTO);
            }
            return nodeChildDTOList;

    }


    public Long deleteNodes(Long nodeId){

        Node node = repository.findOne(nodeId);

        repository.delete(node);

        return node.getId() ;
    }


}

