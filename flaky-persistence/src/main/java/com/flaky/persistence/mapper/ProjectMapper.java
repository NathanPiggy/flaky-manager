package com.flaky.persistence.mapper;

import com.flaky.persistence.model.Project;
import com.flaky.persistence.model.ProjectExample;
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

public interface ProjectMapper extends Serializable {
    @SelectProvider(type=ProjectSqlProvider.class, method="countByExample")
    long countByExample(ProjectExample example);

    @DeleteProvider(type=ProjectSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProjectExample example);

    @Delete({
        "delete from project",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String projectId);

    @Insert({
        "insert into project (project_id, project_name)",
        "values (#{projectId,jdbcType=VARCHAR}, #{projectName,jdbcType=VARCHAR})"
    })
    int insert(Project record);

    @InsertProvider(type=ProjectSqlProvider.class, method="insertSelective")
    int insertSelective(Project record);

    @SelectProvider(type=ProjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR)
    })
    List<Project> selectByExampleWithRowbounds(ProjectExample example, RowBounds rowBounds);

    @SelectProvider(type=ProjectSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR)
    })
    List<Project> selectByExample(ProjectExample example);

    @Select({
        "select",
        "project_id, project_name",
        "from project",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR)
    })
    Project selectByPrimaryKey(String projectId);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    @UpdateProvider(type=ProjectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Project record);

    @Update({
        "update project",
        "set project_name = #{projectName,jdbcType=VARCHAR}",
        "where project_id = #{projectId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Project record);
}