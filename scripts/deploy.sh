#!/bin/bash
set -e

STAGE=$1
SKIP_NEXT_STAGE=${2:-"no"}

echo "Deploying to $STAGE environment..."

# Deployment logic per stage
case "$STAGE" in
  dev)
    echo "Deploying to Development environment..."
    ;;
  stg)
    echo "Deploying to Staging environment..."
    ;;
  perf)
    echo "Deploying to Performance environment..."
    ;;
  ppe)
    echo "Deploying to Pre-Production environment..."
    ;;
  promote-prod)
    echo "Promoting artifacts to Production..."
    ;;
  prod)
    echo "Deploying to Production environment..."
    ;;
  *)
    echo "Unknown environment: $STAGE"
    exit 1
    ;;
esac

# Check if the next stage should be skipped
if [[ "$SKIP_NEXT_STAGE" == "yes" ]]; then
    echo "Skipping next stage as per user input."
fi

echo "Deployment complete!"
