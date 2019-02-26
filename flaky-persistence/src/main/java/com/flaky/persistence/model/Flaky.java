package com.flaky.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class Flaky implements Serializable {
    private Integer flakyId;

    private Integer flakyStatus;

    private Date lastDetectTime;

    private String lastSha1;

    private String className;

    private String unitTestName;

    private Integer detectCount;

    private String projectId;

    private static final long serialVersionUID = 1L;

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

    public Date getLastDetectTime() {
        return lastDetectTime;
    }

    public void setLastDetectTime(Date lastDetectTime) {
        this.lastDetectTime = lastDetectTime;
    }

    public String getLastSha1() {
        return lastSha1;
    }

    public void setLastSha1(String lastSha1) {
        this.lastSha1 = lastSha1;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", flakyId=").append(flakyId);
        sb.append(", flakyStatus=").append(flakyStatus);
        sb.append(", lastDetectTime=").append(lastDetectTime);
        sb.append(", lastSha1=").append(lastSha1);
        sb.append(", className=").append(className);
        sb.append(", unitTestName=").append(unitTestName);
        sb.append(", detectCount=").append(detectCount);
        sb.append(", projectId=").append(projectId);
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
        Flaky other = (Flaky) that;
        return (this.getFlakyId() == null ? other.getFlakyId() == null : this.getFlakyId().equals(other.getFlakyId()))
            && (this.getFlakyStatus() == null ? other.getFlakyStatus() == null : this.getFlakyStatus().equals(other.getFlakyStatus()))
            && (this.getLastDetectTime() == null ? other.getLastDetectTime() == null : this.getLastDetectTime().equals(other.getLastDetectTime()))
            && (this.getLastSha1() == null ? other.getLastSha1() == null : this.getLastSha1().equals(other.getLastSha1()))
            && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()))
            && (this.getUnitTestName() == null ? other.getUnitTestName() == null : this.getUnitTestName().equals(other.getUnitTestName()))
            && (this.getDetectCount() == null ? other.getDetectCount() == null : this.getDetectCount().equals(other.getDetectCount()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFlakyId() == null) ? 0 : getFlakyId().hashCode());
        result = prime * result + ((getFlakyStatus() == null) ? 0 : getFlakyStatus().hashCode());
        result = prime * result + ((getLastDetectTime() == null) ? 0 : getLastDetectTime().hashCode());
        result = prime * result + ((getLastSha1() == null) ? 0 : getLastSha1().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        result = prime * result + ((getUnitTestName() == null) ? 0 : getUnitTestName().hashCode());
        result = prime * result + ((getDetectCount() == null) ? 0 : getDetectCount().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        return result;
    }
}