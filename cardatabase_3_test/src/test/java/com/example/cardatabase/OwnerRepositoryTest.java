package com.example.cardatabase;

import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void saveOwner() {
        // H2 인메모리 상황이기에 DB에 아무런 Owner 없음
        ownerRepository.save(new Owner("칠백", "김"));
        assertThat(ownerRepository.findByFirstName("칠백").isPresent()).isTrue();
    }

    @Test
    @DisplayName("삭제 테스트 : ")
    void deleteOwners() {
        // 객체 생성을 하고 repository에 저장
        ownerRepository.save(new Owner("팔백", "박"));
        // 삭제 method 호출
        ownerRepository.deleteAll();
        // 삭제 완료를 체크하기 위해 assertThat()문 필수 요구
        assertThat(ownerRepository.count()).isEqualTo(0);
    }
}
