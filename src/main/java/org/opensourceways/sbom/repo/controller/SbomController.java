package org.opensourceways.sbom.repo.controller;

import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomRequest;
import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomResponse;
import org.opensourceways.sbom.repo.service.SbomRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(path = "/sbom-repo-api")
public class SbomController {

    private static final Logger logger = LoggerFactory.getLogger(SbomController.class);

    @Autowired
    private SbomRepoService sbomRepoService;

    @PostMapping("/generateOpenEulerSbom")
    public @ResponseBody ResponseEntity generateOpenEulerSbom(HttpServletRequest request, @RequestBody OpenEulerSbomRequest sbomRequest) {
        logger.info("generate openEuler sbom, artifact path: {}", sbomRequest.getArtifactPath());

        OpenEulerSbomResponse sbomResponse = sbomRepoService.generateOpenEulerSbom(sbomRequest);
        return ResponseEntity.status(HttpStatus.OK).body(sbomResponse);
    }

}
