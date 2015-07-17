# leetcode-upload

Auto commit on project leetcode.

## Build

Execute command `mvn package -Dmaven.test.skip=true` .

## Config

Add config file "config/run.properties"

```properties
# leetcode username
username = your_leetcode_username

# leetcode password
password = your_password

# github username or organization
space = your_github_username

# github repository
repository = your_repository
```

## Run

1. Create an empty repository in your github space.

1. Execute command `java -jar leetcode-upload-0.0.1-jar-with-dependencies.jar` .

1. type `init` .

1. type `autorun` .

1. type `merge` .
