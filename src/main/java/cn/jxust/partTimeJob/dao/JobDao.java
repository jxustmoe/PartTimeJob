package cn.jxust.partTimeJob.dao;

import cn.jxust.partTimeJob.pojo.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface JobDao {

    /**
     * 添加职位，不必含有id和isShow
     *
     * @return 返回受影响的行数，默认返回1
     */
    @Insert("INSERT INTO job " +
            "(jobName,detail,address,postTime,jobType,name,phone,email) " +
            "VALUES (#{jobName},#{detail},#{address},#{postTime},#{jobType},#{name},#{phone},#{email})")
    @Options(useGeneratedKeys = true)
    int addJob(Job job);

    /**
     * 删除职位
     *
     * @param id id
     * @return 返回影响的行数，默认返回1
     */
    @Update("UPDATE job SET isShow=-1 WHERE id=#{id}")
    int deleteJob(int id);

    /**
     * @param job 兼职信息对象,需要包含id和isShow
     * @return 返回影响的行数，默认返回1
     */
    int updateJob(Job job);

    /**
     * 根据id获取详细的job
     *
     * @param id id
     * @return job
     */
    @Select("SELECT * FROM job WHERE id=#{id} AND isShow=1")
    Job getJob(int id);

    /**
     * 根据类型返回简要信息
     *
     * @param type 类型，如果类型为空返回所有类型
     * @return type类型的简要信息
     */
    List<Job> getAllBriefJobByType(@Param("type") String type);
}
