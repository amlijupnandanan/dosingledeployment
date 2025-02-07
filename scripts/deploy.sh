#!/bin/bash
set -e

STAGE=$1
echo "Deploying to $STAGE environment"

# Placeholder for deployment logic
if [[ "$STAGE" == "dev" ]]; then
    echo "Deploying to Dev..."
elif [[ "$STAGE" == "stg" ]]; then
    echo "Deploying to Staging..."
elif [[ "$STAGE" == "prod" ]]; then
    echo "Deploying to Production..."
fi
