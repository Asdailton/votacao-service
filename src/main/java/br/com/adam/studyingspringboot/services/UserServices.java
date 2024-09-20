package br.com.adam.studyingspringboot.services;
import br.com.adam.studyingspringboot.model.User;
import br.com.adam.studyingspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        List<User> users = repository.findAll();
        return users;
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
