package com.company.verification;

import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationByEmail {
    private final Map<Integer, Integer> USERCODES = new HashMap<>();
    private static final Random RANDOM = new Random();
    private static final EmailMailing EMAIL_MAILING = new EmailMailing();
    private final UserService userService;


    public void generatedCodeByEmail(Integer userId, String email) {
        makeRandomCode(userId, email);
    }

    public void generatedCodeByUserId(Integer userId) {
        String email = userService.getEmailById(userId);
        makeRandomCode(userId, email);
    }

    private void makeRandomCode(Integer userId, String email) {
        int randomCode = RANDOM.nextInt(99999, 999999);
        USERCODES.put(userId, randomCode);
        Thread thread = new Thread(() -> {
            try {
                EMAIL_MAILING.sendEmail(email, randomCode);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public boolean verification(Integer userId, String enterCode) {
        Integer validCode = USERCODES.get(userId);
        return String.valueOf(validCode).equals(enterCode);
    }
}
