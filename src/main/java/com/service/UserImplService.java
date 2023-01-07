package com.service;

import com.domain.Adminuser;
import com.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserImplService implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserImplService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Adminuser insert(Adminuser adminUser) {
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        return userRepository.create(adminUser);
    }

    @Transactional(readOnly = true)
    public Adminuser get(Long id) {
        return userRepository.get(id);
    }

    @Transactional(readOnly = true)
    public List<Adminuser> getAll() {
        return userRepository.getAll();
    }

    @Transactional
    public Adminuser update(Adminuser adminUser) {

        return userRepository.update(adminUser);
    }
    @Transactional
    public Adminuser updatepassword(Adminuser adminUser) {
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        return userRepository.updatepassword(adminUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Adminuser getByUsername(String username) { return userRepository.getByUsername(username); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Adminuser adminUser = getByUsername(username);
        if (adminUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(adminUser.getPassword());
        return new org.springframework.security.core.userdetails.User(adminUser.getUsername(), adminUser.getPassword(), adminUser.getAuthorities());

    }
}
