package fu.hanoi.swp.VDShop.service.impl;


import fu.hanoi.swp.VDShop.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("teammatchsport@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Xác minh tài khoản của bạn");
        message.setText("Mã xác minh của bạn là: " + code + "\nLưu ý: Mã có hiệu lực trong 5 phút.");
        mailSender.send(message);
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("teammatchsport@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email", e);
        }
    }

    @Override
    public void sendVerificationEmail(String toEmail, String verifyLink, String type) {
        String title = "";
        switch(type) {
            case "register" -> title = "Quý khách vui lòng nhấn nút xác minh để xác minh email:";
            case "forgot" -> title = "Quý khách vui lòng nhấn nút xác minh để đặt lại mật khẩu:";
        }
        
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("VDShop");
            helper.setTo(toEmail);
            helper.setSubject("Xác minh tài khoản của bạn");


            mailSender.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email xác minh", e);
        }
    }
}
