package org.jung.gallery.backend.repository;

import org.jung.gallery.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByIdIn(List<Integer> ids);
}
