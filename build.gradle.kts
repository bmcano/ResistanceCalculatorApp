// Top-level build file where you can add configuration options common to all sub-projects/modules.
// https://stackoverflow.com/questions/67497019/failed-to-upgrade-agp-version
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.gradle.ktlint)
}
