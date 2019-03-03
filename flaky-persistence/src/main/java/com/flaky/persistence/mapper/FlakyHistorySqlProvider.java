package com.flaky.persistence.mapper;

import com.flaky.persistence.model.FlakyHistory;
import com.flaky.persistence.model.FlakyHistoryExample.Criteria;
import com.flaky.persistence.model.FlakyHistoryExample.Criterion;
import com.flaky.persistence.model.FlakyHistoryExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class FlakyHistorySqlProvider {

    public String countByExample(FlakyHistoryExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("flaky_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(FlakyHistoryExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("flaky_history");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(FlakyHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("flaky_history");
        
        if (record.getFlakyHistoryId() != null) {
            sql.VALUES("flaky_history_id", "#{flakyHistoryId,jdbcType=INTEGER}");
        }
        
        if (record.getFlakyId() != null) {
            sql.VALUES("flaky_id", "#{flakyId,jdbcType=INTEGER}");
        }
        
        if (record.getFlakyStatus() != null) {
            sql.VALUES("flaky_status", "#{flakyStatus,jdbcType=INTEGER}");
        }
        
        if (record.getDetectTime() != null) {
            sql.VALUES("detect_time", "#{detectTime,jdbcType=DATE}");
        }
        
        if (record.getSha1() != null) {
            sql.VALUES("sha_1", "#{sha1,jdbcType=VARCHAR}");
        }
        
        if (record.getClassName() != null) {
            sql.VALUES("class_name", "#{className,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitTestName() != null) {
            sql.VALUES("unit_test_name", "#{unitTestName,jdbcType=VARCHAR}");
        }
        
        if (record.getDetectCount() != null) {
            sql.VALUES("detect_count", "#{detectCount,jdbcType=INTEGER}");
        }
        
        if (record.getProjectId() != null) {
            sql.VALUES("project_id", "#{projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getEnvironmentDetail() != null) {
            sql.VALUES("environment_detail", "#{environmentDetail,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(FlakyHistoryExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("flaky_history_id");
        } else {
            sql.SELECT("flaky_history_id");
        }
        sql.SELECT("flaky_id");
        sql.SELECT("flaky_status");
        sql.SELECT("detect_time");
        sql.SELECT("sha_1");
        sql.SELECT("class_name");
        sql.SELECT("unit_test_name");
        sql.SELECT("detect_count");
        sql.SELECT("project_id");
        sql.SELECT("environment_detail");
        sql.FROM("flaky_history");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        FlakyHistory record = (FlakyHistory) parameter.get("record");
        FlakyHistoryExample example = (FlakyHistoryExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("flaky_history");
        
        if (record.getFlakyHistoryId() != null) {
            sql.SET("flaky_history_id = #{record.flakyHistoryId,jdbcType=INTEGER}");
        }
        
        if (record.getFlakyId() != null) {
            sql.SET("flaky_id = #{record.flakyId,jdbcType=INTEGER}");
        }
        
        if (record.getFlakyStatus() != null) {
            sql.SET("flaky_status = #{record.flakyStatus,jdbcType=INTEGER}");
        }
        
        if (record.getDetectTime() != null) {
            sql.SET("detect_time = #{record.detectTime,jdbcType=DATE}");
        }
        
        if (record.getSha1() != null) {
            sql.SET("sha_1 = #{record.sha1,jdbcType=VARCHAR}");
        }
        
        if (record.getClassName() != null) {
            sql.SET("class_name = #{record.className,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitTestName() != null) {
            sql.SET("unit_test_name = #{record.unitTestName,jdbcType=VARCHAR}");
        }
        
        if (record.getDetectCount() != null) {
            sql.SET("detect_count = #{record.detectCount,jdbcType=INTEGER}");
        }
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{record.projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getEnvironmentDetail() != null) {
            sql.SET("environment_detail = #{record.environmentDetail,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("flaky_history");
        
        sql.SET("flaky_history_id = #{record.flakyHistoryId,jdbcType=INTEGER}");
        sql.SET("flaky_id = #{record.flakyId,jdbcType=INTEGER}");
        sql.SET("flaky_status = #{record.flakyStatus,jdbcType=INTEGER}");
        sql.SET("detect_time = #{record.detectTime,jdbcType=DATE}");
        sql.SET("sha_1 = #{record.sha1,jdbcType=VARCHAR}");
        sql.SET("class_name = #{record.className,jdbcType=VARCHAR}");
        sql.SET("unit_test_name = #{record.unitTestName,jdbcType=VARCHAR}");
        sql.SET("detect_count = #{record.detectCount,jdbcType=INTEGER}");
        sql.SET("project_id = #{record.projectId,jdbcType=VARCHAR}");
        sql.SET("environment_detail = #{record.environmentDetail,jdbcType=VARCHAR}");
        
        FlakyHistoryExample example = (FlakyHistoryExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FlakyHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("flaky_history");
        
        if (record.getFlakyId() != null) {
            sql.SET("flaky_id = #{flakyId,jdbcType=INTEGER}");
        }
        
        if (record.getFlakyStatus() != null) {
            sql.SET("flaky_status = #{flakyStatus,jdbcType=INTEGER}");
        }
        
        if (record.getDetectTime() != null) {
            sql.SET("detect_time = #{detectTime,jdbcType=DATE}");
        }
        
        if (record.getSha1() != null) {
            sql.SET("sha_1 = #{sha1,jdbcType=VARCHAR}");
        }
        
        if (record.getClassName() != null) {
            sql.SET("class_name = #{className,jdbcType=VARCHAR}");
        }
        
        if (record.getUnitTestName() != null) {
            sql.SET("unit_test_name = #{unitTestName,jdbcType=VARCHAR}");
        }
        
        if (record.getDetectCount() != null) {
            sql.SET("detect_count = #{detectCount,jdbcType=INTEGER}");
        }
        
        if (record.getProjectId() != null) {
            sql.SET("project_id = #{projectId,jdbcType=VARCHAR}");
        }
        
        if (record.getEnvironmentDetail() != null) {
            sql.SET("environment_detail = #{environmentDetail,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("flaky_history_id = #{flakyHistoryId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, FlakyHistoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}