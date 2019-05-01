package cn.jxust.partTimeJob;

import cn.jxust.partTimeJob.pojo.Job;
import cn.jxust.partTimeJob.pojo.StudentInfo;
import cn.jxust.partTimeJob.service.JobService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobServiceTest extends BaseTest {

    @Autowired
    JobService jobService;

    @Test
    public void getAllTest() {
        List<Job> jobs = jobService.getAll("2", 1, 3);
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    @Test
    public void getDetailTest() {
        Job job = jobService.getDetail(2);
        System.out.println(job);
    }

    @Test
    public void addJobTest() {
        Job job = new Job();
        job.setJobName("搬砖");
        job.setJobType("体力活");
        job.setDetail("在信息学院搬砖");
        job.setAddress("信息学院");
        job.setPhone("13488888888");
        job.setEmail("123456@qq.com");
        job.setName("胡老师");
        jobService.add(job);
    }

    @Test
    public void deleteJobTest() {
        jobService.delete(14);
    }

    @Test
    public void updateJobTest() {
        Job job = new Job();
        job.setId(13);
        job.setJobName("搬砖");
        job.setJobType("体力活");
        job.setDetail("在机电学院搬砖");
        job.setAddress("机电学院");
        job.setPhone("13488888888");
        job.setEmail("123456@qq.com");
        job.setName("王老师");
        job.setIsShow(1);
        jobService.update(job);
    }

    @Test
    public void sendMailTest() {
        StudentInfo info = new StudentInfo();
        info.setName("小伟");
        info.setClasses("网络161");
        info.setPhone("134888888888");
        info.setDesc("这里是简要描述");
        try {
            jobService.sendEmail(info, "信息学院码农", "543000463@qq.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
