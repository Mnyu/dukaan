#!/usr/bin/env bash

set -Eeuo pipefail

THIS_DIR=$(cd "$(dirname "$0")" && pwd)

source "${THIS_DIR}"/../../build.properties

cd "${THIS_DIR}" || exit
DUKAAN_ADMIN_DIST_DIR="${THIS_DIR}/../../dukaan-admin-distribution/target/dukaan-admin-distribution-${APP_VERSION}"
if [[ ! -d "${DUKAAN_ADMIN_DIST_DIR}" ]]; then
  echo "${DUKAAN_ADMIN_DIST_DIR} does not exist."
  echo "dukaan-admin-distribution build must be executed before building this."
  echo "Failed."
  exit 1
fi

cleanup() {
  rm -rf "${THIS_DIR}"/context/app
}

trap cleanup SIGINT SIGTERM ERR

cp -r "${DUKAAN_ADMIN_DIST_DIR}" context/app

cd "${THIS_DIR}/context" || exit
DOCKER_IMAGE_TAG=${APP_VERSION:=1.0.0}

docker build . -t "dukaan-admin:${DOCKER_IMAGE_TAG}"

cleanup
