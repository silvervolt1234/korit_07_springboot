package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // 1. Owner 전체 조회
    @GetMapping("/owners")
    public List<Owner> getOwner() {
        return ownerService.getOwner();
    }
    // 2. Owner id별 조회
    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<Owner> getByOwner(@PathVariable Long ownerId) {
        return ownerService.getOwnerById(ownerId)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElse(ResponseEntity.notFound().build());
    }
    // 3. Owner 객체 추가
    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner saveOwner = ownerService.addOwner(owner);

        return new ResponseEntity<>(saveOwner, HttpStatus.CREATED);
    }
    // 4. Owner 객체 삭제
    @DeleteMapping("/owners/{ownerId}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable Long ownerId) {
        if (ownerService.deleteOwner(ownerId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 5. Owner 객체 수정
    @PutMapping("/owners/{ownerId}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long ownerId, @RequestBody Owner ownerDetails) {
        return ownerService.updateOwner(ownerId, ownerDetails)
                .map(updateOwner -> ResponseEntity.ok().body(updateOwner))
                .orElse(ResponseEntity.notFound().build());
    }

}
