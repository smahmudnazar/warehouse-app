package com.repository;

import com.entity.Attachment_content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentContentRepository extends JpaRepository<Attachment_content,Integer> {
}
