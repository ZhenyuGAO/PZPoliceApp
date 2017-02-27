package com.pvirtech.pzpolice.file.upload;

/**
 * ================================================
 * 作    者：尤鹏达
 * 版    本：1.0
 * 创建日期：2016/11/11
 * 描    述：全局的下载监听
 * 修订历史：
 * ================================================
 */
public class UploadInfo {


    private String url;                         //文件URL
    private String targetFolder;                //保存文件夹
    private String targetPath;                  //保存文件地址
    private String fileName;                    //保存的文件名
    private long totalLength;                   //总大小
    private long uploadLength;                  //已上传大小
    private long networkSpeed;                  //上传速度
    private int state;                          //当前状态

    public UploadInfo(String url, String targetFolder, String targetPath, String fileName, long totalLength, long
            uploadLength, long networkSpeed, int state) {
        this.url = url;
        this.targetFolder = targetFolder;
        this.targetPath = targetPath;
        this.fileName = fileName;
        this.totalLength = totalLength;
        this.uploadLength = uploadLength;
        this.networkSpeed = networkSpeed;
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getUploadLength() {
        return uploadLength;
    }

    public void setUploadLength(long uploadLength) {
        this.uploadLength = uploadLength;
    }

    public long getNetworkSpeed() {
        return networkSpeed;
    }

    public void setNetworkSpeed(long networkSpeed) {
        this.networkSpeed = networkSpeed;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}