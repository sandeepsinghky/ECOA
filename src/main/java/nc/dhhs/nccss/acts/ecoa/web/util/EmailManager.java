
package nc.dhhs.nccss.acts.ecoa.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Phani Konuru
 *
 */
public class EmailManager
{

	/**
	 * 
	 */
	public EmailManager()
	{
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private MailSender mailSender;

	public void sendEmail(String to, String from, String sub, String msgBody)
	{

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(sub);
		message.setText(msgBody);
		mailSender.send(message);
	}

}
