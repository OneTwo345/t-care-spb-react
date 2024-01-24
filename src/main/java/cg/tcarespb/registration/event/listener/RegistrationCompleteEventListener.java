package cg.tcarespb.registration.event.listener;

import cg.tcarespb.models.Account;
import cg.tcarespb.registration.event.RegistrationCompleteEvent;
import cg.tcarespb.service.account.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final AccountService accountService;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;


    public void sendPasswordResetVerificationEmail(String url, Account account) throws MessagingException, UnsupportedEncodingException {
        String subject = "T-Care Password Reset Request Verification";
        String senderName = "User Registration Portal Service";
        String name = (account.getEmployee() == null ? account.getUser().getFirstName() :account.getEmployee().getFirstName());
        String mailContent = "<p> Hi " + name + ", </p>" +
                "<p>Bạn gần đây đã yêu cầu thay đổi mật khẩu,</p>" + "" +
                "<p>Hãy nhấn vào link bên dưới để hoàn thành yêu cầu.</p>" +
                "<a href=\"" + url + "\">Reset password</a>" ;
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(senderEmail);
        messageHelper.setTo(account.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

    }
//    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
//        String subject = "Email Verification";
//        String senderName = "User Registration Portal Service";
//        String mailContent = "<p> Hi, "+ theUser.getFirstName()+ ", </p>"+
//                "<p>Thank you for registering with us,"+"" +
//                "Please, follow the link below to complete your registration.</p>"+
//                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
//                "<p> Thank you <br> Users Registration Portal Service";
//        MimeMessage message = mailSender.createMimeMessage();
//        var messageHelper = new MimeMessageHelper(message);
//        messageHelper.setFrom("dailycodework@gmail.com", senderName);
//        messageHelper.setTo(theUser.getEmail());
//        messageHelper.setSubject(subject);
//        messageHelper.setText(mailContent, true);
//        mailSender.send(message);
//    }
}