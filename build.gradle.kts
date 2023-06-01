// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(deps.Dependencies.Plugins.android) version "7.4.2" apply false
    id(deps.Dependencies.Plugins.androidLibrary) version "7.4.2" apply false
    id(deps.Dependencies.Plugins.jetbrainsKotlinAndroid) version "1.8.0" apply false
    id(deps.Dependencies.Plugins.hiltAndroid) version "2.44" apply false
    id(deps.Dependencies.Plugins.navigationSafArgs) version "2.5.3" apply false
    id("movies.ci.plugin")
}
