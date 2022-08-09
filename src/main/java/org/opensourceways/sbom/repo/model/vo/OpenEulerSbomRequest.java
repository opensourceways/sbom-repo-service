package org.opensourceways.sbom.repo.model.vo;

import java.io.Serializable;

public class OpenEulerSbomRequest implements Serializable {

    private String artifactPath;

    public String getArtifactPath() {
        return artifactPath;
    }

    public void setArtifactPath(String artifactPath) {
        this.artifactPath = artifactPath;
    }
}
