#!/bin/bash

# TODO: Refactor change project root
cd ../..
pwd

# Android App

# Kotlin Multiplatform Mobile
./gradlew :kmm_shared:credential_managment:test
./gradlew :kmm_shared:domain:test
./gradlew :kmm_shared:feature_sign_up:test
./gradlew :kmm_shared:networking:test