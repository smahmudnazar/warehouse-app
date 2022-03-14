package com.repository;

import com.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepositry extends JpaRepository<Attachment,Integer> {
}
