FROM openjdk:17-jdk-slim-buster

RUN apt-get update && apt-get install -y wget curl postgresql-client inotify-tools && \
    apt install -y debian-keyring debian-archive-keyring apt-transport-https && \
    curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/gpg.key' | gpg --dearmor -o /usr/share/keyrings/caddy-stable-archive-keyring.gpg && \
    curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/debian.deb.txt' | tee -a /etc/apt/sources.list.d/caddy-stable.list && \
    apt update && \
    apt install caddy && \
    apt-get clean && \
    useradd -M dukaanuser

USER dukaanuser

WORKDIR /opt/dukaanuser

COPY --chown=dukaanuser:dukaanuser ["app/", "/opt/dukaanuser" ]

COPY --chown=dukaanuser:dukaanuser ["entrypoint.sh", "/"]

ENTRYPOINT ["/entrypoint.sh"]
