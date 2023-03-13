package com.nnenna.receiver.repository;

import com.nnenna.receiver.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
