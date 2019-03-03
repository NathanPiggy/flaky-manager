package com.flaky.persistence.mapper;

import com.flaky.persistence.model.Flaky;
import com.flaky.persistence.model.FlakyExample;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface FlakyMapper extends Serializable {
    @SelectProvider(type=FlakySqlProvider.class, method="countByExample")
    long countByExample(FlakyExample example);

    @DeleteProvider(type=FlakySqlProvider.class, method="deleteByExample")
    int deleteByExample(FlakyExample example);

    @Delete({
        "delete from flaky",
        "where flaky_id = #{flakyId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer flakyId);

    @Insert({
        "insert into flaky (flaky_id, flaky_status, ",
        "last_detect_time, last_sha_1, ",
        "class_name, unit_test_name, ",
        "detect_count, project_id)",
        "values (#{flakyId,jdbcType=INTEGER}, #{flakyStatus,jdbcType=INTEGER}, ",
        "#{lastDetectTime,jdbcType=DATE}, #{lastSha1,jdbcType=VARCHAR}, ",
        "#{className,jdbcType=VARCHAR}, #{unitTestName,jdbcType=VARCHAR}, ",
        "#{detectCount,jdbcType=INTEGER}, #{projectId,jdbcType=VARCHAR})"
    })
    int insert(Flaky record);

    @InsertProvider(type=FlakySqlProvider.class, method="insertSelective")
    int insertSelective(Flaky record);

    @SelectProvider(type=FlakySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="last_detect_time", property="lastDetectTime", jdbcType=JdbcType.DATE),
        @Result(column="last_sha_1", property="lastSha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR)
    })
    List<Flaky> selectByExampleWithRowbounds(FlakyExample example, RowBounds rowBounds);

    @SelectProvider(type=FlakySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="last_detect_time", property="lastDetectTime", jdbcType=JdbcType.DATE),
        @Result(column="last_sha_1", property="lastSha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR)
    })
    List<Flaky> selectByExample(FlakyExample example);

    @Select({
        "select",
        "flaky_id, flaky_status, last_detect_time, last_sha_1, class_name, unit_test_name, ",
        "detect_count, project_id",
        "from flaky",
        "where flaky_id = #{flakyId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="last_detect_time", property="lastDetectTime", jdbcType=JdbcType.DATE),
        @Result(column="last_sha_1", property="lastSha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR)
    })
    Flaky selectByPrimaryKey(Integer flakyId);

    @UpdateProvider(type=FlakySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Flaky record, @Param("example") FlakyExample example);

    @UpdateProvider(type=FlakySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Flaky record, @Param("example") FlakyExample example);

    @UpdateProvider(type=FlakySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Flaky record);

    @Update({
        "update flaky",
        "set flaky_status = #{flakyStatus,jdbcType=INTEGER},",
          "last_detect_time = #{lastDetectTime,jdbcType=DATE},",
          "last_sha_1 = #{lastSha1,jdbcType=VARCHAR},",
          "class_name = #{className,jdbcType=VARCHAR},",
          "unit_test_name = #{unitTestName,jdbcType=VARCHAR},",
          "detect_count = #{detectCount,jdbcType=INTEGER},",
          "project_id = #{projectId,jdbcType=VARCHAR}",
        "where flaky_id = #{flakyId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Flaky record);
}