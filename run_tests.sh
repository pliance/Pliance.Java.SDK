#!/bin/bash
set -uo pipefail

cd "$(dirname "$0")"

RESULTS_DIR="test-results"
rm -rf "$RESULTS_DIR"
mkdir -p "$RESULTS_DIR"

mvn -B test
STATUS=$?

if [ -d target/surefire-reports ]; then
  cp target/surefire-reports/TEST-*.xml "$RESULTS_DIR"/ 2>/dev/null
fi

echo "JUnit XML test reports written to $RESULTS_DIR/"
exit $STATUS
