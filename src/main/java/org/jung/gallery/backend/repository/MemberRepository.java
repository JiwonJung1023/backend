package org.jung.gallery.backend.repository;

import org.jung.gallery.backend.entity.Item;
import org.jung.gallery.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
}
