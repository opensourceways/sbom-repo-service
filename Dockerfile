FROM sbom-tools-syft:latest AS builder

FROM openeuler/openeuler:22.03-lts AS final

COPY --from=builder /opt/openeuler-sbom-tools /opt/openeuler-sbom-tools

RUN yum update -y && yum install -y \
    git \
    java-18-openjdk \
    && rm -rf /var/cache/yum

# online
# WORKDIR /opt
# RUN git clone https://github.com/opensourceways/sbom-repo-service.git

# local
COPY ./ /opt/sbom-repo-service

EXPOSE 15551

ENTRYPOINT ["/bin/bash", "/opt/sbom-repo-service/docker-entrypoint.sh"]
