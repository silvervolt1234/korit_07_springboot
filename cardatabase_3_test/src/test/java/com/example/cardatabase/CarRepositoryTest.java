package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량 저장 메서드 테스트")
    void saveCar() {
        // given - 제반 준비 사항
        // Car Entity를 확인했을 때 field로 Owner를 요구해서 먼저 생성 후 OwnerRepository에 저장
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        // Car 객체 생성
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);

        // when - 테스트 실행
        // 저장이 됬는가를 확인하는 부분
        carRepository.save(car);

        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();    // 결과값은 그저 하나
        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("자동차 정보 삭제 메서드 테스트")
    void deleteCar() {
        //given -> Owner 객체 생성 / 저장 -> Car 객체 생성 저장
        Owner owner2 = new Owner("Google", "Apple");
        ownerRepository.save(owner2);
        Car car2 = new Car("Audi", "R8", "White", "456894", 2022, 666666, owner2);
        carRepository.save(car2);
        // when -> 삭제
        carRepository.deleteById(car2.getId());
        // then -> 삭제 검증
        assertThat(carRepository.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("브랜드 검색 메서드 테스트")
    void findByBrandShouldReturnCar() {
        // given - Owner 하나 생성 및 저장 / Car 객체 3대 생성 및 저장
        Owner owner3 = new Owner("Google", "Apple");
        ownerRepository.save(owner3);
        Car car3 = new Car("Sheborae", "R7", "White", "456894", 2022, 666666, owner3);
        carRepository.save(car3);
        carRepository.save(new Car("GM", "R9", "Black", "644645", 2023, 777777, owner3));
        carRepository.save(new Car("GM", "R10", "Blue", "457664", 2019, 999999, owner3));

        // when - carRepository.findByBrand("브랜드명") -> 자료형 list
        List<Car> gms = carRepository.findByBrand("GM");
        
        // then - list 내부의 element의 자료형이 Car 객체니 그 객체의 getBrand() 결과값이 findByBrand()의 argument와 동일한지 체크
        assertThat(gms.get(0).getBrand()).isEqualTo("GM");
        // 혹은 GM이 두 대니 size의 값이 2이므로
        assertThat(gms.size()).isEqualTo(2);
    }
}
