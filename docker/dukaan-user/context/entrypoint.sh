#!/usr/bin/env bash

set -Eeuo pipefail

main() {
  /opt/dukaanuser/bin/dukaanuser run-server &
  local server_pid=$!
  sleep 20
  wait $server_pid
}

main