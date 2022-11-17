#!/usr/bin/env bash

set -Eeuo pipefail

THIS_DIR=$(cd "$(dirname "$0")" && pwd)

cd "${THIS_DIR}" || exit
source "${THIS_DIR}"/../../build.properties

DOCKER_IMAGE_TAG=${APP_VERSION:=1.0.0}

docker run -it -d \
  -p 4343:4343 \
  -e POSTGRES_HOSTNAME="localhost" \
  -e POSTGRES_PORT=5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD="s3cr3t" \
  -e POSTGRES_DB="dukaandb" \
  -e POSTGRES_SCHEMA="public" \
  "dukaan-user:${DOCKER_IMAGE_TAG}"