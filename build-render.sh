#!/bin/bash
# Build script for Render deployment
# This script ensures we're in the correct directory

cd client || exit 1
npm install
npm run build

