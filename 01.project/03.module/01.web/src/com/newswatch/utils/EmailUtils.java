package com.newswatch.utils;

import org.apache.log4j.Logger;

import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.javamail.MailSenderInfo;
import com.newswatch.utils.javamail.SimpleMailSender;

/**
 * 邮件工具类
 * User: Gxx
 * Time: 2014-03-25 16:22
 */
public class EmailUtils implements BaseInterface
{
    /**
     * 日志处理器
     */
    public static Logger logger = Logger.getLogger(EmailUtils.class);

    /**
     * 发送邮件
     * @param title
     * @param content
     * @param emails 邮件逗号分隔
     * @return
     * @throws Exception
     */
    public static boolean sendEmail(String title, String content, String emails) throws Exception
    {
        String emailHost = PropertyUtil.getInstance().getProperty(EMAIL_HOST);
        String emailPort = PropertyUtil.getInstance().getProperty(EMAIL_PORT);
        String emailName = PropertyUtil.getInstance().getProperty(EMAIL_NAME);
        String emailPassword = PropertyUtil.getInstance().getProperty(EMAIL_PASSWORD);
        logger.info("emailHost=[" + emailHost + "],emailPort=[" + emailPort + "],emailName=[" + emailName +
                "],emailPassword=[" + emailPassword + "],title=[" + title + "],content=[" + content +
                "],emails=[" + emails + "]");
        try{
            /**
             * 邮件逗号分隔
             * 逐个邮件发送
             */
            for(int i=0;i<emails.split(",").length;i++){
                String[] toAddress = emails.split(",");
                //发送邮件需要使用的基本信息
                MailSenderInfo mailInfo = new MailSenderInfo();
                mailInfo.setMailServerHost(emailHost);//发送邮件服务器接受
                mailInfo.setMailServerPort(emailPort);//端口
                mailInfo.setValidate(true);//校验用户名
                mailInfo.setUserName(emailName);//邮箱用户名
                mailInfo.setPassword(emailPassword);//邮箱密码
                mailInfo.setFromAddress(emailName);//邮箱用户名
                mailInfo.setToAddress(toAddress[i]);//接收方邮箱
                mailInfo.setSubject(title);//邮件抬头
                mailInfo.setContent(content);//邮件内容
                //简单邮件（不带附件的邮件）发送器
                SimpleMailSender sms = new SimpleMailSender();
                //sms.sendTextMail(mailInfo);//发送文体格式
                sms.sendHtmlMail(mailInfo);//发送html格式
            }
        }catch (Exception e) {
            logger.error("异常发生", e);
        }catch (Error e) {
            logger.error("错误发生", e);
        }finally{

        }
        return true;
    }

    /**
     * main函数
     * @param param
     */
    public static void main(String[] param) throws Exception {
        EmailUtils.sendEmail("测试title", "<font color='green'>设置邮箱内容xxxxxxxxx</font>", "419066357@qq.com,419066357@163.com");
    }
}
