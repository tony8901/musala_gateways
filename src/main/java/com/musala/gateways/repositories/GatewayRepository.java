package com.musala.gateways.repositories;

import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, String> {
}
