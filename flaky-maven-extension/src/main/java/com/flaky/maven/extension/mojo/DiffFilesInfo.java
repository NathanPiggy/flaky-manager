package com.flaky.maven.extension.mojo;

public class DiffFilesInfo {

    String fileName;

    String currentSha1;

    String lastSha1;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCurrentSha1() {
        return currentSha1;
    }

    public void setCurrentSha1(String currentSha1) {
        this.currentSha1 = currentSha1;
    }

    public String getLastSha1() {
        return lastSha1;
    }

    public void setLastSha1(String lastSha1) {
        this.lastSha1 = lastSha1;
    }
}
