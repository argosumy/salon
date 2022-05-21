package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import spdu2022.java.project.beutysalon.notification.models.Notification;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MailSenderService {
    private final JavaMailSender emailSender;
    private final FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean;

    public MailSenderService(JavaMailSender emailSender, FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean) {
        this.emailSender = emailSender;
        this.freeMarkerConfigurationFactoryBean = freeMarkerConfigurationFactoryBean;
    }

    public boolean send(Notification notification) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setSubject(notification.getSubject());
            mimeMessageHelper.setTo(notification.getToUser().getEmail());
            mimeMessageHelper.setText(getHtml(notification), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (TemplateException | IOException | MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getHtml(Notification notification) throws IOException, TemplateException {
        final String nameTemplate = ModelTypes.valueOf(notification.getNameNotificationTypes()).getTemplate();
        final Map<String, Object> model = ModelTypes.valueOf(notification.getNameNotificationTypes()).getModel(notification);
        Template template = freeMarkerConfigurationFactoryBean.getObject().getTemplate(nameTemplate);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
