package org.opensourceways.sbom.repo.service;

import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomRequest;
import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomResponse;

public interface SbomRepoService {

    OpenEulerSbomResponse generateOpenEulerSbom(OpenEulerSbomRequest request);
}
