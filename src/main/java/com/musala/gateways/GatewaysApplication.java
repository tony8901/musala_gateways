package com.musala.gateways;

import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.repositories.DeviceRepository;
import com.musala.gateways.repositories.GatewayRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;


@SpringBootApplication
public class GatewaysApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GatewaysApplication.class, args);
//		GatewayRepository gatewayRepository = context.getBean(GatewayRepository.class);
//		Gateway gateway = new Gateway("serial1", "gateway1","192.168.1.1");
//		Gateway gateway1 = new Gateway("serial12", "gateway1","192.168.1.1");
//		gatewayRepository.save(gateway);
//		gatewayRepository.save(gateway1);
//
//		DeviceRepository deviceRepository = context.getBean(DeviceRepository.class);
//		Device device1 = new Device(123L, "alguien", LocalDate.of(2022,12,3), true);
//		device1.setGateway(gateway);
//		deviceRepository.save(device1);
//		Device device2 = new Device(124L, "alguien", LocalDate.of(2022,12,3), true);
//		device2.setGateway(gateway1);
//		deviceRepository.save(device2);
//		System.out.println("gateway");
//		System.out.println(gatewayRepository.count());
//		System.out.println(gatewayRepository.findAll());
//
//		System.out.println("device");
//		System.out.println(deviceRepository.count());
//		System.out.println(deviceRepository.findAll());
//
//		ProofRepository proofRepository = context.getBean(ProofRepository.class);
//		Proof proof = new Proof(null, "name");
//		Proof proof1 = new Proof(null, "name1");
//		proofRepository.save(proof);
//		proofRepository.save(proof1);
//		System.out.println(proofRepository.count());
//		System.out.println(proofRepository.findAll());
	}

}
