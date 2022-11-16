#!/usr/bin/env bash

set -Eeuo pipefail

main() {
  /opt/dukaanadmin/bin/dukaanadmin run-server &
  local server_pid=$!
  sleep 20
  wait $server_pid
}

main