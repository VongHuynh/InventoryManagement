package com.management.InventoryManagement.Service;

import com.management.InventoryManagement.Entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
