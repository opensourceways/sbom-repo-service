package org.opensourceways.sbom.repo.model.vo;

import java.io.Serializable;

public class OpenEulerSbomResponse implements Serializable {

    private String sbomContent;

    private Boolean success;

    private String errorInfo;

    public String getSbomContent() {
        return sbomContent;
    }

    public void setSbomContent(String sbomContent) {
        this.sbomContent = sbomContent;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
