package com.swsisolutions.realtimeRestApi.repository;

import com.swsisolutions.realtimeRestApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
