package com.dto;

import com.entity.Attachment;

import javax.persistence.OneToOne;

public class AttachmentContentDTO {

    private AttachmentDTO attachmentDTO;

    private byte[] bytes;
}
