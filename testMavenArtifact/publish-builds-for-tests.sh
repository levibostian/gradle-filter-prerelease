#!/bin/sh 

set -e 

VERSION="1.0.0" ./gradlew :app:publishPluginPublicationToMavenLocal

VERSION="2.0.0-alpha.1" ./gradlew :app:publishPluginPublicationToMavenLocal
VERSION="2.0.0-beta.1" ./gradlew :app:publishPluginPublicationToMavenLocal
VERSION="2.0.0-rc.1" ./gradlew :app:publishPluginPublicationToMavenLocal

VERSION="3.0.0-alpha.1" ./gradlew :app:publishPluginPublicationToMavenLocal
VERSION="3.0.0-beta.1" ./gradlew :app:publishPluginPublicationToMavenLocal

VERSION="4.0.0-alpha.1" ./gradlew :app:publishPluginPublicationToMavenLocal