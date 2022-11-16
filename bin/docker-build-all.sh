#!/usr/bin/env bash

set -Eeuo pipefail

THIS_DIR=$(cd "$(dirname "$0")" && pwd)

"$THIS_DIR"/../docker/dukaan-admin/build.sh
"$THIS_DIR"/../docker/dukaan-user/build.sh