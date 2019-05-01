package cn.jxust.partTimeJob;

import cn.jxust.partTimeJob.dao.JobDao;
import cn.jxust.partTimeJob.pojo.Job;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class JobDaoTest extends BaseTest {

    @Autowired
    JobDao jobDao;

    @Test
    public void getAllBriefJobByType() {
        List<Job> jobs = jobDao.getAllBriefJobByType("type1");
        for (Job job : jobs) System.out.println(job);
    }

    @Test
    public void TestGetJob() {
        Job job = jobDao.getJob(1);
        System.out.println(job);
    }

    @Test
    public void addJob() {
        Job job = new Job();
        job.setAddress("江西理工大学");
        job.setDetail("详细信息");
        job.setEmail("2231231@qq.com");
        job.setJobName("搬砖测试");
        job.setName("山水");
        job.setPostTime(new Date().toLocaleString());
        job.setJobType("type1");
        job.setPhone("15607003550");
        jobDao.addJob(job);
        jobDao.addJob(job);
        jobDao.addJob(job);
        jobDao.addJob(job);
    }

    @Test
    public void deleteJob() {
        int i = jobDao.deleteJob(1);
        System.out.println(i);
    }
}
