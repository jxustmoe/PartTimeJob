package cn.jxust.partTimeJob.service.serviceImpl;

import cn.jxust.partTimeJob.dao.JobDao;
import cn.jxust.partTimeJob.pojo.Job;
import cn.jxust.partTimeJob.pojo.StudentInfo;
import cn.jxust.partTimeJob.service.JobService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;

    private final JavaMailSender mailSender;

    //发件人昵称
    @Value("${mail.nickname}")
    private String nickName;

    //发件人邮箱
    @Value("${mail.username}")
    private String sender;

    //邮件主题
    @Value("${mail.subject}")
    private String subject;

    @Autowired
    public JobServiceImpl(JobDao jobDao, JavaMailSender mailSender) {
        this.jobDao = jobDao;
        this.mailSender = mailSender;
    }

    private String getTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }

    @Override
    public List<Job> getAll(String type, int page, int count) {

        PageHelper.startPage(page, count);
        return jobDao.getAllBriefJobByType(type);

    }

    @Override
    public Job getDetail(int id) {
        return jobDao.getJob(id);
    }

    @Override
    public boolean add(Job job) {
        job.setPostTime(getTimeString());
        return jobDao.addJob(job) == 1;
    }

    @Override
    public boolean update(Job job) {
        job.setPostTime(getTimeString());
        return jobDao.updateJob(job) == 1;
    }

    @Override
    public boolean delete(int id) {
        return jobDao.deleteJob(id) == 1;
    }

    @Override
    public boolean sendEmail(StudentInfo info, String jobName, String email) throws MessagingException, IOException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        //收件人
        helper.setTo(email);
        //发送人
        helper.setFrom(nickName + "<" + sender + ">");
        //邮件主题
        helper.setSubject(subject);

        //获取邮件模板
        InputStream is = Resources.getResourceAsStream("MailTemplate.html");
        String mailHtml = IOUtils.toString(is, "UTF-8");

        //替换正文相关信息
        mailHtml = mailHtml.replace("${name}", info.getName());
        mailHtml = mailHtml.replace("${class}", info.getClasses());
        mailHtml = mailHtml.replace("${phone}", info.getPhone());
        mailHtml = mailHtml.replace("${jobName}", jobName);
        mailHtml = mailHtml.replace("${desc}", info.getDesc() == null ? "无" : info.getDesc());
        message.setContent(mailHtml, "text/html;charset=utf8");

        mailSender.send(message);

        return true;
    }
}
