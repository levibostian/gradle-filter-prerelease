/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details on writing Custom Plugins, please refer to https://docs.gradle.org/8.3/userguide/custom_plugins.html in the Gradle documentation.
 */

plugins {    
    `java-gradle-plugin` // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    id("org.jetbrains.kotlin.jvm") version "1.9.0" // enables kotlin lang 
    id("com.gradle.plugin-publish") version "1.2.1" // enables publishing to gradle plugin portal
}

repositories {
    mavenCentral()
}

dependencies {    
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// used by gradle plugin publish plugin 
group = "earth.levi"
version = System.getenv("PLUGIN_VERSION") ?: "local"

// configure 'com.gradle.plugin-publish'. from: https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html#configure_the_plugin_publishing_plugin
gradlePlugin {
    website.set("https://github.com/levibostian/gradle-filter-prerelease") 
    vcsUrl.set("https://github.com/levibostian/gradle-filter-prerelease")

    plugins {
        create("filterPrereleasePlugin") {
            id = "earth.levi.filter-prerelease"
            implementationClass = "earth.levi.FilterPrereleasePlugin"
            displayName = "filter pre-release versions" 
            description = "Gradle plugin that filters pre-release versions of dependencies. This plugin is mostly useful for Gradle projects using version ranges." 
            tags.set(listOf("dependencies", "pre-release", "versions", "prerelease", "filter")) 
        }
    }
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {}

configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
configurations["functionalTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
    testClassesDirs = functionalTestSourceSet.output.classesDirs
    classpath = functionalTestSourceSet.runtimeClasspath
    useJUnitPlatform()
}

gradlePlugin.testSourceSets.add(functionalTestSourceSet)

tasks.named<Task>("check") {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}

tasks.named<Test>("test") {
    // Use JUnit Jupiter for unit tests.
    useJUnitPlatform()
}
