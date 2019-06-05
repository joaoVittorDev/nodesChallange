package com.test.repository;

import com.test.model.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface nodeRepository extends CrudRepository<Node, Long> {


    List<Node> findByParentId(final Long parentId);



}