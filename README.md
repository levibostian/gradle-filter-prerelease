# filter-prerelease

Gradle plugin that filters pre-release versions of dependencies. This plugin is mostly useful for Gradle projects using version ranges. 

*Tip:* If you are using ranges for your versions, you should also use [Gradle's dependency locking](https://docs.gradle.org/current/userguide/dependency_locking.html) feature to avoid unexpected version updates.

# Getting started 

Apply the plugin to your project: 

Note: `<latest-version-here>` is the latest version of the plugin. This is: ![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/earth.levi.filter-prerelease)

```kotlin
plugins {
    id("earth.levi.filter-prerelease") version "<latest-version-here>"
}

// Optional: configure the plugin. 
// By default, all values are set to false to only allow stable releases. 
filterPrerelease {
    allowReleaseCandidates = true
    allowBeta = false 
    allowAlpha = false
}

dependencies {
    // Now, you can use version ranges in your project to automatically update to the latest versions!
    // 
    // Here, we always update to latest stable or release candidate version. Even major version bumps are allowed.
    implementation("app.cash.sqldelight:sqlite-driver:+")
    // 
    // Here, we only update to the latest stable version. Major version bumps are not allowed.
    implementation("com.squareup.okhttp3:okhttp:[4.0.0,5.0.0)")
}
```

Done! 

Optional step if using [Gradle's dependency locking](https://docs.gradle.org/current/userguide/dependency_locking.html), run `./gradlew dependencies --write-locks` to update your lock files with latest versions. 

# Configuration 

This plugin can be configured using the `filterPrerelease` extension. You enable or disable different types of pre-release versions. By default, all values are set to false to only allow stable releases. If you only want stable releases, you don't need to configure anything.

```kotlin
// If you were to, for example, allow beta versions to be installed, it's recommended that you also enable release candidates since those are designed to be more stable then beta. 
filterPrerelease {
    allowReleaseCandidates = true
    allowBeta = true 
    allowAlpha = false
}

// Same if you were to allow alpha versions to be installed, it's recommended that you also enable beta and release candidates since those are designed to be more stable then alpha.
filterPrerelease {
    allowReleaseCandidates = true
    allowBeta = true 
    allowAlpha = true
}
```

# Contributing

Glad you want to contribute to this project! We welcome contributions from anyone, as long as we all follow the [code of conduct](code_of_conduct.md).

### Building

* Open project in Intellij IDEA to modify source code. 

* Plugin has automated tests written in `plugin/src/functionalTest/` directory. Great way to change changes you make to the plugin. 

### Test plugin in a real-world project

* The easiest way is to install this plugin to your local maven repository and then use it in your project. Run `./gradlew :plugin:publishFilterPrereleasePluginPluginMarkerMavenPublicationToMavenLocal` to install a build of the plugin to the local Maven repository on your machine. By default, the version of the plugin is `local`. 

* Then, in your project, apply the plugin using `id("earth.levi.filter-prerelease") version "local"`. You will need to make sure and add the `mavenLocal()` repository to your project's `settings.gradle.kts` file.

```kotlin 
pluginManagement {
    repositories {
        gradlePluginPortal()        
        mavenLocal()        
    }
}
```

## Deployment 

We deploy to the [Gradle Plugin Portal](https://plugins.gradle.org/) to make using the plugin very easy. 

* [Register for an account](https://plugins.gradle.org/user/register) with the Gradle Plugin Portal to get your set of API keys. 

* Set some environment variable secrets for GitHub Actions:

1. `GRADLE_PUBLISH_KEY` API key you received after registering with Gradle Plugin Portal. 
2. `GRADLE_PUBLISH_SECRET` API key secret you received after registering with Gradle Plugin Portal. 

* Done! The semantic-release tool will now deploy your gradle plugin to the portal every time you deploy a new version of the project. 


