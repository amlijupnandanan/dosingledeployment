#!/bin/bash
set -e

STAGE=$1

echo "🚀 Deploying to $STAGE environment..."

case "$STAGE" in
  dev) echo "📦 Deploying to Development environment...";;
  stg) echo "📦 Deploying to Staging environment...";;
  perf) echo "📦 Deploying to Performance environment...";;
  ppe) echo "📦 Deploying to Pre-Production environment...";;
  prod) echo "📦 Deploying to Production environment...";;
  *) echo "❌ Unknown environment: $STAGE"; exit 1;;
esac

echo "✅ Deployment complete!"
