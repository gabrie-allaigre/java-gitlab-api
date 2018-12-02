package com.talanlabs.gitlab.api.v4.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabPosition {

    public enum PositionType {
        TEXT("text"), IMAGE("image");

        private String value;

        PositionType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @JsonProperty("base_sha")
    private String baseSha;

    @JsonProperty("start_sha")
    private String startSha;

    @JsonProperty("head_sha")
    private String headSha;

    @JsonProperty("position_type")
    private PositionType positionType;

    @JsonProperty("new_path")
    private String newPath;

    @JsonProperty("new_line")
    private Integer newLine;

    @JsonProperty("old_path")
    private String oldPath;

    @JsonProperty("old_line")
    private Integer oldLine;

    private Integer width;
    private Integer height;
    private Integer x;
    private Integer y;

    public String getBaseSha() {
        return baseSha;
    }

    public void setBaseSha(String baseSha) {
        this.baseSha = baseSha;
    }

    public String getStartSha() {
        return startSha;
    }

    public void setStartSha(String startSha) {
        this.startSha = startSha;
    }

    public String getHeadSha() {
        return headSha;
    }

    public void setHeadSha(String headSha) {
        this.headSha = headSha;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public Integer getNewLine() {
        return newLine;
    }

    public void setNewLine(Integer newLine) {
        this.newLine = newLine;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public Integer getOldLine() {
        return oldLine;
    }

    public void setOldLine(Integer oldLine) {
        this.oldLine = oldLine;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
