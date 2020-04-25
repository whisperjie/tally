package com.whisper.tally.util.pie;


/*
被坑了,调个球接口不就行了.
老子得敲.
 */
public class PieData {
    public String name; //名称
    public float value; //数值
    public float percentage; //百分比
    public  int color; //颜色
    public float angle; //角度

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", color=" + color +
                ", angle=" + angle +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
