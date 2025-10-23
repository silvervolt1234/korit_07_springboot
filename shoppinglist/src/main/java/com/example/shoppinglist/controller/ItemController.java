package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.Item;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:8080") // React와 연동
public class ItemController {

    private List<Item> items = new ArrayList<>();

    @GetMapping
    public List<Item> getItems() {
        return items;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        items.add(0, item);
        return item;
    }
}
