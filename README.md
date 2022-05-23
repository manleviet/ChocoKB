# ChocoKB - at.tugraz.ist.ase.choco-kb

*Migrated to [https://github.com/manleviet/CA-CDR-V2](https://github.com/manleviet/CA-CDR-V2)*

A Maven package for Configuration Knowledge Bases in Choco Solver

Knowledge bases:

1. [PC](https://www.itu.dk/research/cla/externals/clib/)
2. [Renault](https://www.itu.dk/research/cla/externals/clib/)
3. [Feature Models](http://www.splot-research.org)

## How to use

Add the below script in your pom file:

```
<dependency>
  <groupId>at.tugraz.ist.ase</groupId>
  <artifactId>choco-kb</artifactId>
  <version>1.2</version>
</dependency>
```

And the below script in the settings.xml file:

```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>

    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                <repository>
                    <id>github</id>
                    <url>https://maven.pkg.github.com/manleviet/*</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
    
    <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```
Replacing USERNAME with your GitHub username, and TOKEN with your personal access token (see [Creating a personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)).
