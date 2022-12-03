package com.musala.gateways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


@SpringBootApplication
public class GatewaysApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GatewaysApplication.class, args);
		DeviceRepository deviceRepository = context.getBean(DeviceRepository.class);
		GatewayRepository gatewayRepository = context.getBean(GatewayRepository.class);

//		Gateway gateway = new Gateway("serial", "gateway", new int[]{1,2,3,4});
//		gatewayRepository.save(gateway);
//		Device device = new Device(123456789L, "Alguien", "1/12/2022", true, gateway);
//		deviceRepository.save(device);

		System.out.println("find device");
		System.out.println(deviceRepository.count());
		System.out.println(deviceRepository.findAll());

		System.out.println("find gateway");
		System.out.println(gatewayRepository.count());
		System.out.println(gatewayRepository.findAll());
	}

}
