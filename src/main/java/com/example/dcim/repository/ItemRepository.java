package com.example.dcim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dcim.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
