This is a hello world app created with `gradle init`. It was created as something that I could push many versions of it to a maven repository to test the gradle plugin for. 

# How to use 

Edit `app/build.gradle`, publications `version` with version that you want to push. 

Then, run command: 

```
USERNAME="<github-username>" TOKEN="<github personal access token with write:permissions scope>" ./gradlew publishPluginPublicationToGitHubPackagesRepository
```


