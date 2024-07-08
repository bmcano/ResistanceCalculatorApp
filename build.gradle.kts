// Top-level build file where you can add configuration options common to all sub-projects/modules.
// https://stackoverflow.com/questions/67497019/failed-to-upgrade-agp-version
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlin) apply false
}