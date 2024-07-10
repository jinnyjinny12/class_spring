package com.ohgiraffers.fileupload;

public class FileDTO {
    // 원본이름은 데이터베이스에 저장
    private String originFileName;

    // save이름도 데이터 베이스에 저장!
    // 저장할 때 확장자의 이름을 바꾼다 - 볼 수 없다
    private String saveName;

    // original name + path 를 붙여서 다시 사용자에게 보여준다
    private String filePath;
    private String fileDescription;


    // DB
    // 원본, 변경이름, 저장경로, 설명



    public FileDTO() {
    }

    public FileDTO(String originFileName, String saveName, String filePath, String fileDescription) {
        this.originFileName = originFileName;
        this.saveName = saveName;
        this.filePath = filePath;
        this.fileDescription = fileDescription;
    }


    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    @Override
    public String toString() {
        return "fileDTO{" +
                "originFileName='" + originFileName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                '}';
    }
}
