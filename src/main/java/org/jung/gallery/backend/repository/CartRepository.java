package org.jung.gallery.backend.repository;

import org.jung.gallery.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberId(int memberId);
    Cart findByMemberIdAndItemId(int memberId, int itemId);

    void deleteByMemberId(int memberId);
}
