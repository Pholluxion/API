package com.asdtest.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdtest.api.entitys.Receiver;

public interface ReceiverDAO extends JpaRepository<Receiver, Long> {

}
