package cn.itcast.bookStore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		
		Properties props = new Properties();
		//属性mail.transport.protocol设置要使用的邮件协议
		props.setProperty("mail.transport.protocol", "SMTP");
		//属性mail.host表示发送服务器的邮件服务器地址
		props.setProperty("mail.host", "smtp.souhu.com");
		//属性mail.smtp.auth设置发送时是否校验用户名和密码
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		
		// 创建验证器,返回发件人账号与密码信息
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//填写邮箱的登陆账号和授权密码
				return new PasswordAuthentication("itcast_duhong", "1234567890");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("itcast_duhong@sohu.com")); // 设置发送者
		//Message的setRecipients方法可以指定一个或多个收件人
		//这里我们指定多个收件人（即收件人参数数组）
		/*第一个参数：
		        RecipientType.TO    代表收件人 
		        RecipientType.CC    抄送
	            RecipientType.BCC    暗送
	                    第二个参数:
      	收件人的地址，或者是一个Address[]，用来装抄送或者暗送人的名单。或者用来群发。可以是相同邮箱服务器的，也可以是不同的 。     
        *
        *
        */
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
