package com.flaky.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class FlakyHistory implements Serializable {
    private Integer flakyHistoryId;

    private Integer flakyId;

    private Integer flakyStatus;

    private Date detectTime;

    private String sha1;

    private String className;

    private String unitTestName;

    private Integer detectCount;

    private String projectId;

    private String environmentDetail;

    private static final long serialVersionUID = 1L;

    public Integer getFlakyHistoryId() {
        return flakyHistoryId;
    }

    public void setFlakyHistoryId(Integer flakyHistoryId) {
        this.flakyHistoryId = flakyHistoryId;
    }

    public Integer getFlakyId() {
        return flakyId;
    }

    public void setFlakyId(Integer flakyId) {
        this.flakyId = flakyId;
    }

    public Integer getFlakyStatus() {
        return flakyStatus;
    }

    public void setFlakyStatus(Integer flakyStatus) {
        this.flakyStatus = flakyStatus;
    }

    public Date getDetectTime() {
        return detectTime;
    }

    public void setDetectTime(Date detectTime) {
        this.detectTime = detectTime;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUnitTestName() {
        return unitTestName;
    }

    public void setUnitTestName(String unitTestName) {
        this.unitTestName = unitTestName;
    }

    public Integer getDetectCount() {
        return detectCount;
    }

    public void setDetectCount(Integer detectCount) {
        this.detectCount = detectCount;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEnvironmentDetail() {
        return environmentDetail;
    }

    public void setEnvironmentDetail(String environmentDetail) {
        this.environmentDetail = environmentDetail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", flakyHistoryId=").append(flakyHistoryId);
        sb.append(", flakyId=").append(flakyId);
        sb.append(", flakyStatus=").append(flakyStatus);
        sb.append(", detectTime=").append(detectTime);
        sb.append(", sha1=").append(sha1);
        sb.append(", className=").append(className);
        sb.append(", unitTestName=").append(unitTestName);
        sb.append(", detectCount=").append(detectCount);
        sb.append(", projectId=").append(projectId);
        sb.append(", environmentDetail=").append(environmentDetail);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FlakyHistory other = (FlakyHistory) that;
        return (this.getFlakyHistoryId() == null ? other.getFlakyHistoryId() == null : this.getFlakyHistoryId().equals(other.getFlakyHistoryId()))
            && (this.getFlakyId() == null ? other.getFlakyId() == null : this.getFlakyId().equals(other.getFlakyId()))
            && (this.getFlakyStatus() == null ? other.getFlakyStatus() == null : this.getFlakyStatus().equals(other.getFlakyStatus()))
            && (this.getDetectTime() == null ? other.getDetectTime() == null : this.getDetectTime().equals(other.getDetectTime()))
            && (this.getSha1() == null ? other.getSha1() == null : this.getSha1().equals(other.getSha1()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getUnitTestName() == null ? other.getUnitTestName() == null : this.getUnitTestName().equals(other.getUnitTestName()))
            && (this.getDetectCount() == null ? other.getDetectCount() == null : this.getDetectCount().equals(other.getDetectCount()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getEnvironmentDetail() == null ? other.getEnvironmentDetail() == null : this.getEnvironmentDetail().equals(other.getEnvironmentDetail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFlakyHistoryId() == null) ? 0 : getFlakyHistoryId().hashCode());
        result = prime * result + ((getFlakyId() == null) ? 0 : getFlakyId().hashCode());
        result = prime * result + ((getFlakyStatus() == null) ? 0 : getFlakyStatus().hashCode());
        result = prime * result + ((getDetectTime() == null) ? 0 : getDetectTime().hashCode());
        result = prime * result + ((getSha1() == null) ? 0 : getSha1().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getUnitTestName() == null) ? 0 : getUnitTestName().hashCode());
        result = prime * result + ((getDetectCount() == null) ? 0 : getDetectCount().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getEnvironmentDetail() == null) ? 0 : getEnvironmentDetail().hashCode());
        return result;
    }
}