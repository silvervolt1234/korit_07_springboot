package com.example.shoppinglist;

import com.example.shoppinglist.domain.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ShoppinglistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 소유자 객체를 추가
		Owner owner1 = new Owner("일", "김");
		Owner owner2 = new Owner("이", "강");
		// 다수의 객체를 한 번에 저장하는 메서드 처음 사용
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		// 그리고 Car의 생성자에 field를 추가했기에 오류 방지를 위해 owner 추가
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000, owner1));
		repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000, owner2));
		repository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000, owner2));
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690A1B2C3D4", 2020, 30000000, owner1));
		repository.save(new Car("Hyundai", "Sonata", "White", "1234567890ABCDEFG", 2025, 25000000, owner2));
		repository.save(new Car("Honda", "CR-V", "Black-White", "987654321ZYXWVUTS", 2024, 45000000, owner2));

		for (Car car : repository.findAll()) {
			logger.info("brand : {}, model : {}", car.getBrand(), car.getModel());
		}

		// AppUser 더미 데이터 추가
		// 위의 Owner의 경우 owner1 / owner2 만들고 ownerRepository에 저장
		userRepository.save(new AppUser("user", "$2a$12$uQREMDkMC/AbhWCextHeQ.zJn90ux9kZXrXv1cPr78uZkHYFVz8gu", "USER"));
		userRepository.save(new AppUser("admin", "$2a$12$7OSM3F78plNmkVb.k1/bN.VJPWsVN5395iopjV7bl5896xtwfHcoS", "ADMIN"));
	}
}
