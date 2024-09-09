package org.jung.gallery.backend.controller;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.jung.gallery.backend.dto.OrderDto;
import org.jung.gallery.backend.entity.Cart;
import org.jung.gallery.backend.entity.Item;
import org.jung.gallery.backend.entity.Order;
import org.jung.gallery.backend.repository.CartRepository;
import org.jung.gallery.backend.repository.ItemRepository;
import org.jung.gallery.backend.repository.OrderRepository;
import org.jung.gallery.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/api/orders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isvalid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {
        if (!jwtService.isvalid(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        int memberId = jwtService.getId(token);
        Order newOrder = new Order();

        newOrder.setMemberId(jwtService.getId(token));
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());

        orderRepository.save(newOrder);
        cartRepository.deleteByMemberId(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
