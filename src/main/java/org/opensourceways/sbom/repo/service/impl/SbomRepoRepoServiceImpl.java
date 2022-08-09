package org.opensourceways.sbom.repo.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.opensourceways.sbom.repo.constant.SbomRepoConstants;
import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomRequest;
import org.opensourceways.sbom.repo.model.vo.OpenEulerSbomResponse;
import org.opensourceways.sbom.repo.service.SbomRepoService;
import org.opensourceways.sbom.repo.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
public class SbomRepoRepoServiceImpl implements SbomRepoService {

    private static final Logger logger = LoggerFactory.getLogger(SbomRepoRepoServiceImpl.class);

    @Value("${openeuler.sbom.tools}")
    private String openeulerSbomToolsPath;

    @Override
    public OpenEulerSbomResponse generateOpenEulerSbom(OpenEulerSbomRequest request) {
        OpenEulerSbomResponse response = new OpenEulerSbomResponse();
        File tmpFile = null;
        FileReader sbomReader = null;

        try {
            if (!StringUtils.hasText(request.getArtifactPath())) {
                throw new RuntimeException("artifactPath in request is empty");
            }
            if (!FileUtils.getFile(request.getArtifactPath()).exists()) {
                throw new RuntimeException("artifact file is not exists");
            }

            Path tmpFilePath = Files.createTempFile(SbomRepoConstants.TMP_FILE_PREFIX, SbomRepoConstants.TMP_FILE_SUFFIX);
            tmpFile = tmpFilePath.toFile();
            tmpFile.deleteOnExit();

            int returnValue = runTools(request, tmpFilePath);
            if (returnValue != 0) {
                logger.error("process internal error!");
                response.setErrorInfo("process internal error!");
                response.setSuccess(false);
                return response;
            }

            sbomReader = new FileReader(tmpFile);
            String sbomStr = IOUtils.toString(sbomReader);
            logger.info("sbomStr length: {}", sbomStr.length());
            response.setSuccess(true);
            response.setSbomContent(sbomStr);

            logger.info("sbom tools command finish");

        } catch (IOException | InterruptedException | RuntimeException e) {
            logger.error("", e);
            response.setErrorInfo(e.getMessage());
            response.setSuccess(false);
            return response;
        } finally {
            IOUtils.closeQuietly(sbomReader);
            if (tmpFile != null) {
                boolean deleteResult = tmpFile.delete();
                logger.info("tmp file delete result: {}", deleteResult);
            }
        }

        return response;
    }

    private int runTools(OpenEulerSbomRequest request, Path tmpFilePath) throws IOException, InterruptedException {
        String sbomJsonPath = tmpFilePath.toAbsolutePath().toString();

        List<String> commandList = List.of(openeulerSbomToolsPath,
                request.getArtifactPath(),
                "-o",
                "spdx-json=" + sbomJsonPath);
        logger.info("sbom tools command:{}", commandList);

        ProcessBuilder pb = new ProcessBuilder(commandList);
        Process process = pb.start();

        LogUtils.printMessage(process.getInputStream());
        LogUtils.printMessage(process.getErrorStream());
        return process.waitFor();
    }
}
