//package com.example.cardatabase.web;
//
//import com.example.cardatabase.domain.Car;
//import com.example.cardatabase.domain.CarRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CarController {
//    private final CarRepository carRepository;
//
//    public CarController(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }
//
//    @GetMapping("/cars")
//    public Iterable<Car> getCars() {
//        // 자동차들이 저장된 테이블에서 전체 명단 추출
//        return carRepository.findAll();
//    }
//}
