package com.musala.gateways.security.service;


import com.musala.gateways.security.entities.User;
import com.musala.gateways.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * Autentica un usuario de la base de datos
 * Authentication Manager llama al método loadUserByUsername de esta clase
 * para obtener los detalles del usuario de la base de datos cuando
 * se intente autenticar un usuario
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRespository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRespository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //buscar en la base de datos los detalles del usuario
        User user = userRespository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username" + username));

        //devolver el usuario de tu base de datos en un objeto UserDetails de spring
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}
