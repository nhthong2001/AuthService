package com.example.authservice.service;

import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.util.BCryptEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BootstrappingData implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    private final Logger LOG = LoggerFactory.getLogger(BootstrappingData.class);

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");

        createSystemUser();

        LOG.info("...Bootstrapping completed");
    }

    private void createSystemUser() {
        if (userRepository.findByUsername("root") != null) {
            return;
        }

        BCryptEncoder encoder = new BCryptEncoder();

        LOG.info("... creating system user");

        User user = new User("root", encoder.passwordEncoder().encode("123456"));

        userRepository.save(user);
    }

}
