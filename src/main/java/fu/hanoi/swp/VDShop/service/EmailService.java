package fu.hanoi.swp.VDShop.service;


public interface EmailService {

    void sendVerificationCode(String toEmail, String code);

    void sendEmail(String to, String subject, String content);

    void sendVerificationEmail(String toEmail, String verifyLink, String type);
}
