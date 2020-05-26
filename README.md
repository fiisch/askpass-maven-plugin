# Askpass Maven Plugin

## Why
So you can put your credentials interactively on the command line instead of storing them in the *~/.m2/settings.xml*.

## What it does
This plugin looks into *settings.xml* on your configured servers. If you set up a placeholder password there, plugin prompts for the actual password and injects it into the Maven session. All subsequent phases and goals then use valid credentials.

## How to use it
Add your servers to the settings.xml as usual, but set the password to a placeholder string. The default placeholder string is `askpass-maven-plugin`. You can also override the placeholder value in your project's POM by setting `askpass.password-placeholder` property. This alllows you to have different placeholders for differrent project - so the plugin will prompt only for those repos you actually use.

Plugin does not care which repo you need to log to. It simply asks for all passwords you configured for prompting.

### Configuring
Right now, you have to build and install the plugin yourself.

1. Clone this repo.
1. Run `mvn clean install` to install the plugin locally.
1. Configure your placeholder password in *settings.xml*. Add something like this for every server you want to be prompted for:
```xml
...
<server>
        <id>server id</id>
        <username>your username</username>
        <password>password placeholder</password>
</server>
...
```

1. Add the plugin config to your project's POM:
```xml
...
<!-- If the property is not set, plugin will use default placeholder. -->
<properties>
  <askpass.password-placeholder>askpass-maven-plugin</askpass.password-placeholder>
</properties>
...
<build>
  <plugins>
    <plugin>
      <groupId>cz.fiisch.maven.plugin.askpass</groupId>
      <artifactId>askpass-maven-plugin</artifactId>
      <version>1.0-SNAPSHOT</version>
    </plugin>
  </plugins>
</build>
...
```

1. Use the plugin in the part of lifecycle where you need it. Something like: `mvn clean askpass:askpass validate` or before push `mvn ... askpass:askpass deploy` is pretty usual.
