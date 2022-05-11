package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import spdu2022.java.project.beutysalon.notification.models.mail.EMail;

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

    public boolean send(EMail eMail) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setSubject(eMail.getSubject());
            mimeMessageHelper.setTo(eMail.getToUser().getEmail());
            mimeMessageHelper.setText(getHtml(eMail), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
           return false;
        } catch (MessagingException e) {
            e.printStackTrace();
           return false;
        }
    }

    public String getHtml(EMail eMail) throws IOException, TemplateException {
        final String nameTemplate = ModelTypes.valueOf(eMail.getNameNotificationTypes()).getTemplate();
        final Map<String, Object> model = ModelTypes.valueOf(eMail.getNameNotificationTypes()).getModel(eMail);
        Template template = freeMarkerConfigurationFactoryBean.getObject().getTemplate(nameTemplate);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
