package com.liuxueliang.measurementtoolboxes.application;

public class DataModel {

    private  int id;


    //预输入
    private Double startElevation,endElevation;
    private String recorder;



    //观测数据
    private String backName;
    private String frontName;
    private Double backTop;
    private Double backBottom;
    private Double backBlackMid;
    private Double frontBlackMid;
    private Double frontTop;
    private Double frontBottom;
    private Double frontRedMid;
    private Double backRedMid;
    private Double backSight;
    private Double frontSight;
    private Double sightDiff;
    private Double cumulativeSightDiff;
    private Double blackHeightDiff;
    private Double redHeightDiff;
    private Double backBlackPlusKMinusRed;
    private Double frontBlackPlusKMinusRed;
    private Double blackMinusRedHeightDiff;
    private Double average;





    public DataModel(Double startElevation, Double endElevation, String recorder) {

        this.startElevation = startElevation != null ? startElevation : 0.0; // 默认值为 0.0，可以根据实际情况修改
        this.endElevation = endElevation != null ? endElevation : 0.0; // 默认值为 0.0，可以根据实际情况修改
        this.recorder = recorder != null ? recorder : ""; // 默认值为空字符串，可以根据实际情况修改

        // 使用三元运算符确保变量不为空，再转换为 double 值
        this.backName = backName != null ? backName : "";
        this.frontName = frontName != null ? frontName : "";

        this.backTop = backTop != null ? backTop : 0.0;
        this.backBottom = backBottom != null ? Double.valueOf(backBottom) : 0.0;
        this.backBlackMid = backBlackMid != null ? Double.valueOf(backBlackMid) : 0.0;
        this.frontBlackMid = frontBlackMid != null ? Double.valueOf(frontBlackMid) : 0.0;
        this.frontTop = frontTop != null ? Double.valueOf(frontTop) : 0.0;
        this.frontBottom = frontBottom != null ? Double.valueOf(frontBottom) : 0.0;
        this.frontRedMid = frontRedMid != null ? Double.valueOf(frontRedMid) : 0.0;
        this.backRedMid = backRedMid != null ? Double.valueOf(backRedMid) : 0.0;
        this.backSight = backSight != null ? Double.valueOf(backSight) : 0.0;
        this.frontSight = frontSight != null ? Double.valueOf(frontSight) : 0.0;
        this.sightDiff = sightDiff != null ? Double.valueOf(sightDiff) : 0.0;
        this.cumulativeSightDiff = cumulativeSightDiff != null ? Double.valueOf(cumulativeSightDiff) : 0.0;
        this.blackHeightDiff = blackHeightDiff != null ? Double.valueOf(blackHeightDiff) : 0.0;
        this.redHeightDiff = redHeightDiff != null ? Double.valueOf(redHeightDiff) : 0.0;
        this.backBlackPlusKMinusRed = backBlackPlusKMinusRed != null ? Double.valueOf(backBlackPlusKMinusRed) : 0.0;
        this.frontBlackPlusKMinusRed = frontBlackPlusKMinusRed != null ? Double.valueOf(frontBlackPlusKMinusRed) : 0.0;
        this.blackMinusRedHeightDiff = blackMinusRedHeightDiff != null ? Double.valueOf(blackMinusRedHeightDiff) : 0.0;
        this.average = average != null ? Double.valueOf(average) : 0.0;



    }

    public DataModel(String backName, String frontName, Double backTop, Double backBottom, Double backBlackMid,
                     Double frontBlackMid, Double frontTop, Double frontBottom, Double frontRedMid, Double backRedMid,
                     Double backSight, Double frontSight, Double sightDiff, Double cumulativeSightDiff, Double blackHeightDiff,
                      Double redHeightDiff, Double backBlackPlusKMinusRed, Double frontBlackPlusKMinusRed, Double blackMinusRedHeightDiff,
                      Double average) {

        this.backName = backName != null ? backName : "";
        this.frontName = frontName != null ? frontName : "";

        this.backTop = backTop != 0.0 ? backTop : 0.0;
        this.backBottom = backBottom != 0.0 ? backBottom : 0.0;
        this.backBlackMid = backBlackMid != 0.0 ? backBlackMid : 0.0;
        this.frontBlackMid = frontBlackMid != 0.0 ? frontBlackMid : 0.0;
        this.frontTop = frontTop != 0.0 ? frontTop : 0.0;
        this.frontBottom = frontBottom != 0.0 ? frontBottom : 0.0;
        this.frontRedMid = frontRedMid != 0.0 ? frontRedMid : 0.0;
        this.backRedMid = backRedMid != 0.0 ? backRedMid : 0.0;
        this.backSight = backSight != null ? backSight : 0.0;
        this.frontSight = frontSight != null ? frontSight : 0.0;
        this.sightDiff = sightDiff != null ? sightDiff : 0.0;
        this.cumulativeSightDiff = cumulativeSightDiff;
        this.blackHeightDiff = blackHeightDiff != null ? blackHeightDiff : 0.0;
        this.redHeightDiff = redHeightDiff != null ? redHeightDiff : 0.0;
        this.backBlackPlusKMinusRed = backBlackPlusKMinusRed != null ? backBlackPlusKMinusRed : 0.0;
        this.frontBlackPlusKMinusRed = frontBlackPlusKMinusRed != null ? frontBlackPlusKMinusRed : 0.0;
        this.blackMinusRedHeightDiff = blackMinusRedHeightDiff != null ? blackMinusRedHeightDiff : 0.0;
        this.average = average != null ? average : 0.0;



    }







    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", recorder='" + recorder + '\'' +
                ", startElevation=" + startElevation +
                ", endElevation=" + endElevation +
                ", backName='" + backName + '\'' +
                ", frontName='" + frontName + '\'' +
                ", backTop=" + backTop +
                ", backBottom=" + backBottom +
                ", backBlackMid=" + backBlackMid +
                ", frontBlackMid=" + frontBlackMid +
                ", frontTop=" + frontTop +
                ", frontBottom=" + frontBottom +
                ", frontRedMid=" + frontRedMid +
                ", backRedMid=" + backRedMid +
                ", backSight=" + backSight +
                ", frontSight=" + frontSight +
                ", sightDiff=" + sightDiff +
                ", cumulativeSightDiff=" + cumulativeSightDiff +
                ", blackHeightDiff=" + blackHeightDiff +
                ", redHeightDiff=" + redHeightDiff +
                ", backBlackPlusKMinusRed=" + backBlackPlusKMinusRed +
                ", frontBlackPlusKMinusRed=" + frontBlackPlusKMinusRed +
                ", blackMinusRedHeightDiff=" + blackMinusRedHeightDiff +
                ", average=" + average +
                '}';
    }






    public int getId() {
        return id;
    }


    public Double getStartElevation() {
        return startElevation;
    }

    public Double getEndElevation() {
        return endElevation;
    }

    public String getRecorder() {
        return recorder;
    }



    public String getBackName() {
        return backName;
    }

    public String getFrontName() {
        return frontName;
    }

    public Double getBackTop() {
        return backTop;
    }

    public Double getBackBottom() {
        return backBottom;
    }

    public Double getBackBlackMid() {
        return backBlackMid;
    }

    public Double getFrontBlackMid() {
        return frontBlackMid;
    }

    public Double getFrontTop() {
        return frontTop;
    }

    public Double getFrontBottom() {
        return frontBottom;
    }

    public Double getFrontRedMid() {
        return frontRedMid;
    }

    public Double getBackRedMid() {
        return backRedMid;
    }

    public Double getBackSight() {
        return backSight;
    }

    public Double getFrontSight() {
        return frontSight;
    }

    public Double getSightDiff() {
        return sightDiff;
    }

    public Double getCumulativeSightDiff() {
        return cumulativeSightDiff;
    }

    public Double getBlackHeightDiff() {
        return blackHeightDiff;
    }

    public Double getRedHeightDiff() {
        return redHeightDiff;
    }

    public Double getBackBlackPlusKMinusRed() {
        return backBlackPlusKMinusRed;
    }

    public Double getFrontBlackPlusKMinusRed() {
        return frontBlackPlusKMinusRed;
    }

    public Double getBlackMinusRedHeightDiff() {
        return blackMinusRedHeightDiff;
    }

    public Double getAverageTV() {
        return average;
    }






    public void setId(int id) {
        this.id = id;
    }

    public void setRecorder(String recorder) { this.recorder = recorder;}

    public void setStartElevation(Double startElevation) {
        this.startElevation = startElevation;
    }

    public void setEndElevation(Double endElevation) {
        this.endElevation = endElevation;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }

    public void setBackTop(Double backTop) {
        this.backTop = backTop;
    }

    public void setBackBottom(Double backBottom) {
        this.backBottom = backBottom;
    }

    public void setBackBlackMid(Double backBlackMid) {
        this.backBlackMid = backBlackMid;
    }

    public void setFrontBlackMid(Double frontBlackMid) {
        this.frontBlackMid = frontBlackMid;
    }

    public void setFrontTop(Double frontTop) {
        this.frontTop = frontTop;
    }

    public void setFrontBottom(Double frontBottom) {
        this.frontBottom = frontBottom;
    }

    public void setFrontRedMid(Double frontRedMid) {
        this.frontRedMid = frontRedMid;
    }

    public void setBackRedMid(Double backRedMid) {
        this.backRedMid = backRedMid;
    }

    public void setBackSight(Double backSight) {
        this.backSight = backSight;
    }

    public void setFrontSight(Double frontSight) {
        this.frontSight = frontSight;
    }

    public void setSightDiff(Double sightDiff) {
        this.sightDiff = sightDiff;
    }

    public void setCumulativeSightDiff(Double cumulativeSightDiff) {
        this.cumulativeSightDiff = cumulativeSightDiff;
    }

    public void setBlackHeightDiff(Double blackHeightDiff) {
        this.blackHeightDiff = blackHeightDiff;
    }

    public void setRedHeightDiff(Double redHeightDiff) {
        this.redHeightDiff = redHeightDiff;
    }

    public void setBackBlackPlusKMinusRed(Double backBlackPlusKMinusRed) {
        this.backBlackPlusKMinusRed = backBlackPlusKMinusRed;
    }

    public void setFrontBlackPlusKMinusRed(Double frontBlackPlusKMinusRed) {
        this.frontBlackPlusKMinusRed = frontBlackPlusKMinusRed;
    }

    public void setBlackMinusRedHeightDiff(Double blackMinusRedHeightDiff) {
        this.blackMinusRedHeightDiff = blackMinusRedHeightDiff;
    }

    public void setAverageTV(Double average) {
        this.average = average;
    }


}


