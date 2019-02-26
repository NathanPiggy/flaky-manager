package com.flaky.persistence.mapper;

import com.flaky.persistence.model.FlakyHistory;
import com.flaky.persistence.model.FlakyHistoryExample;
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

public interface FlakyHistoryMapper extends Serializable {
    @SelectProvider(type=FlakyHistorySqlProvider.class, method="countByExample")
    long countByExample(FlakyHistoryExample example);

    @DeleteProvider(type=FlakyHistorySqlProvider.class, method="deleteByExample")
    int deleteByExample(FlakyHistoryExample example);

    @Delete({
        "delete from flaky_history",
        "where flaky_history_id = #{flakyHistoryId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer flakyHistoryId);

    @Insert({
        "insert into flaky_history (flaky_history_id, flaky_id, ",
        "flaky_status, detect_time, ",
        "sha_1, class_name, ",
        "unit_test_name, detect_count, ",
        "project_id, environment_detail)",
        "values (#{flakyHistoryId,jdbcType=INTEGER}, #{flakyId,jdbcType=INTEGER}, ",
        "#{flakyStatus,jdbcType=INTEGER}, #{detectTime,jdbcType=TIMESTAMP}, ",
        "#{sha1,jdbcType=VARCHAR}, #{className,jdbcType=VARCHAR}, ",
        "#{unitTestName,jdbcType=VARCHAR}, #{detectCount,jdbcType=INTEGER}, ",
        "#{projectId,jdbcType=VARCHAR}, #{environmentDetail,jdbcType=VARCHAR})"
    })
    int insert(FlakyHistory record);

    @InsertProvider(type=FlakyHistorySqlProvider.class, method="insertSelective")
    int insertSelective(FlakyHistory record);

    @SelectProvider(type=FlakyHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="flaky_history_id", property="flakyHistoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="detect_time", property="detectTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sha_1", property="sha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR),
        @Result(column="environment_detail", property="environmentDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<FlakyHistory> selectByExampleWithRowbounds(FlakyHistoryExample example, RowBounds rowBounds);

    @SelectProvider(type=FlakyHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="flaky_history_id", property="flakyHistoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="detect_time", property="detectTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sha_1", property="sha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR),
        @Result(column="environment_detail", property="environmentDetail", jdbcType=JdbcType.VARCHAR)
    })
    List<FlakyHistory> selectByExample(FlakyHistoryExample example);

    @Select({
        "select",
        "flaky_history_id, flaky_id, flaky_status, detect_time, sha_1, class_name, unit_test_name, ",
        "detect_count, project_id, environment_detail",
        "from flaky_history",
        "where flaky_history_id = #{flakyHistoryId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="flaky_history_id", property="flakyHistoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="flaky_id", property="flakyId", jdbcType=JdbcType.INTEGER),
        @Result(column="flaky_status", property="flakyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="detect_time", property="detectTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sha_1", property="sha1", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_name", property="className", jdbcType=JdbcType.VARCHAR),
        @Result(column="unit_test_name", property="unitTestName", jdbcType=JdbcType.VARCHAR),
        @Result(column="detect_count", property="detectCount", jdbcType=JdbcType.INTEGER),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR),
        @Result(column="environment_detail", property="environmentDetail", jdbcType=JdbcType.VARCHAR)
    })
    FlakyHistory selectByPrimaryKey(Integer flakyHistoryId);

    @UpdateProvider(type=FlakyHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") FlakyHistory record, @Param("example") FlakyHistoryExample example);

    @UpdateProvider(type=FlakyHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") FlakyHistory record, @Param("example") FlakyHistoryExample example);

    @UpdateProvider(type=FlakyHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FlakyHistory record);

    @Update({
        "update flaky_history",
        "set flaky_id = #{flakyId,jdbcType=INTEGER},",
          "flaky_status = #{flakyStatus,jdbcType=INTEGER},",
          "detect_time = #{detectTime,jdbcType=TIMESTAMP},",
          "sha_1 = #{sha1,jdbcType=VARCHAR},",
          "class_name = #{className,jdbcType=VARCHAR},",
          "unit_test_name = #{unitTestName,jdbcType=VARCHAR},",
          "detect_count = #{detectCount,jdbcType=INTEGER},",
          "project_id = #{projectId,jdbcType=VARCHAR},",
          "environment_detail = #{environmentDetail,jdbcType=VARCHAR}",
        "where flaky_history_id = #{flakyHistoryId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FlakyHistory record);
}