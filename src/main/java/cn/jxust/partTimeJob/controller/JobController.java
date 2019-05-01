package cn.jxust.partTimeJob.controller;

import cn.jxust.partTimeJob.pojo.Job;
import cn.jxust.partTimeJob.pojo.Message;
import cn.jxust.partTimeJob.pojo.StudentInfo;
import cn.jxust.partTimeJob.service.JobService;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobController {

    private final JobService jobService;

    private final Logger logger = LogManager.getLogger();

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * 根据要求获取兼职信息列表,但是不显示兼职详细信息
     *
     * @param type  兼职类型,空为显示全部
     * @param page  当前页数,默认为1
     * @param count 每页显示条数,默认为10
     */
    @RequestMapping("/all")
    public Message all(String type, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer count) {
        List<Job> jobs;
        try {
            jobs = jobService.getAll(type, page, count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(0, "获取列表失败");
        }
        //使用PageInfo处理返回的结果,使其带有分页相关信息
        PageInfo<Job> pageInfo = new PageInfo<>(jobs);
        //组装返回的页面信息
        Map<String, Object> result = new HashMap<>();
        result.put("data", jobs);
        result.put("hasPre", pageInfo.isHasPreviousPage());
        result.put("hasNext", pageInfo.isHasNextPage());
        result.put("currentPage", pageInfo.getPageNum());
        return new Message(0, result);
    }

    /**
     * 获取单条兼职信息
     *
     * @param id id号
     */
    @RequestMapping("/detail")
    public Message detail(Integer id) {
        if (id == null) {
            return new Message(500, "缺少参数");
        }
        try {
            Job job = jobService.getDetail(id);
            return new Message(0, job);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "获取详情失败");
        }
    }

    /**
     * 增加兼职信息
     *
     * @param job 兼职信息对象
     */
    @RequestMapping("/add")
    public Message add(Job job) {
        try {
            if (job.isEmpty()) {
                return new Message(500, "缺少参数");
            }
            jobService.add(job);
            return new Message(0, "添加信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "添加信息失败");
        }
    }

    /**
     * 修改单条兼职信息
     *
     * @param job 兼职信息对象
     */
    @RequestMapping("/update")
    public Message update(Job job) {
        if (job.getId() == 0 || job.isEmpty()) {
            return new Message(500, "缺少参数");
        }
        try {
            jobService.update(job);
            return new Message(0, "更新信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "更新信息失败");
        }
    }

    /**
     * 删除单条兼职信息
     *
     * @param id id号
     */
    @RequestMapping("/delete")
    public Message delete(Integer id) {
        if (id == null) {
            return new Message(500, "缺少参数");
        }
        try {
            jobService.delete(id);
            return new Message(0, "删除信息成功");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "删除信息失败");
        }
    }

    /**
     * 发送邮件
     *
     * @param info    用户填写的报名信息
     * @param jobName 职位名称
     * @param email   收件人
     */
    @RequestMapping("/sendEmail")
    public Message sendEmail(StudentInfo info, String jobName, String email) {
        try {
            jobService.sendEmail(info, jobName, email);
            return new Message(0, "发送邮件成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "发送邮件失败");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return new Message(500, "读取邮件模板错误");
        }
    }

    /**
     * 异常处理器
     */
    @ExceptionHandler(Exception.class)
    public Message exceptionHandler() {
        return new Message(500, "访问地址或参数格式错误");
    }
}