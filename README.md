 <a name="#documentr_top"></a>

> **This project requires JVM version of at least 1.8**






<a name="documentr_heading_0"></a>

# recipease <sup><sup>[top](#documentr_top)</sup></sup>



> A recipe book creator from json -> PDF






<a name="documentr_heading_1"></a>

# Table of Contents <sup><sup>[top](#documentr_top)</sup></sup>



 - [recipease](#documentr_heading_0)
 - [Table of Contents](#documentr_heading_1)
 - [Building the Package](#documentr_heading_2)
   - [*NIX/Mac OS X](#documentr_heading_3)
   - [Windows](#documentr_heading_4)
 - [Running the Tests](#documentr_heading_5)
   - [*NIX/Mac OS X](#documentr_heading_6)
   - [Windows](#documentr_heading_7)
 - [All-In-One](#documentr_heading_8)
 - [Artefact Publishing - Bintray](#documentr_heading_9)
   - [maven setup](#documentr_heading_10)
   - [gradle setup](#documentr_heading_11)
 - [Artefact Publishing - Github](#documentr_heading_12)
   - [Dependencies - Gradle](#documentr_heading_13)
   - [Dependencies - Maven](#documentr_heading_14)
   - [Dependencies - Downloads](#documentr_heading_15)






<a name="documentr_heading_2"></a>

# Building the Package <sup><sup>[top](#documentr_top)</sup></sup>



<a name="documentr_heading_3"></a>

## *NIX/Mac OS X <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`./gradlew build`




<a name="documentr_heading_4"></a>

## Windows <sup><sup>[top](#documentr_top)</sup></sup>

`./gradlew.bat build`


This will compile and assemble the artefacts into the `build/libs/` directory.

Note that this may also run tests (if applicable see the Testing notes)



<a name="documentr_heading_5"></a>

# Running the Tests <sup><sup>[top](#documentr_top)</sup></sup>



<a name="documentr_heading_6"></a>

## *NIX/Mac OS X <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`gradlew --info test`



<a name="documentr_heading_7"></a>

## Windows <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`./gradlew.bat --info test`


The `--info` switch will also output logging for the tests




<a name="documentr_heading_8"></a>

# All-In-One <sup><sup>[top](#documentr_top)</sup></sup>

This project's artefact output is an 'all in one' jar which includes all runtime dependencies.

This should appear in the artefact repository along with the compiled code, as a convention, this is usually appended with an `-all` classifier

For example:

`recipease-1.0.0.jar -> recipease-1.0.0-all.jar`





<a name="documentr_heading_9"></a>

# Artefact Publishing - Bintray <sup><sup>[top](#documentr_top)</sup></sup>

This project publishes artefacts to [bintray](https://bintray.com/)

> Note that the latest version can be found [https://bintray.com/synapticloop/maven/recipease/view](https://bintray.com/synapticloop/maven/recipease/view)



<a name="documentr_heading_10"></a>

## maven setup <sup><sup>[top](#documentr_top)</sup></sup>

this comes from the jcenter bintray, to set up your repository:



```
<?xml version="1.0" encoding="UTF-8" ?>
<settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd' xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
  <profiles>
    <profile>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>bintray</name>
          <url>http://jcenter.bintray.com</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>central</id>
          <name>bintray-plugins</name>
          <url>http://jcenter.bintray.com</url>
        </pluginRepository>
      </pluginRepositories>
      <id>bintray</id>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>bintray</activeProfile>
  </activeProfiles>
</settings>
```





<a name="documentr_heading_11"></a>

## gradle setup <sup><sup>[top](#documentr_top)</sup></sup>

Repository



```
repositories {
	maven {
		url  "http://jcenter.bintray.com" 
	}
}
```



or just



```
repositories {
	jcenter()
}
```





<a name="documentr_heading_12"></a>

# Artefact Publishing - Github <sup><sup>[top](#documentr_top)</sup></sup>

This project publishes artefacts to [GitHub](https://github.com/)

> Note that the latest version can be found [https://github.com/synapticloopltd/recipease/releases](https://github.com/synapticloopltd/recipease/releases)

As such, this is not a repository, but a location to download files from.



<a name="documentr_heading_13"></a>

## Dependencies - Gradle <sup><sup>[top](#documentr_top)</sup></sup>



```
dependencies {
	runtime(group: 'synapticloop', name: 'recipease', version: '1.0.0', ext: 'jar')

	compile(group: 'synapticloop', name: 'recipease', version: '1.0.0', ext: 'jar')
}
```



or, more simply for versions of gradle greater than 2.1



```
dependencies {
	runtime 'synapticloop:recipease:1.0.0'

	compile 'synapticloop:recipease:1.0.0'
}
```





<a name="documentr_heading_14"></a>

## Dependencies - Maven <sup><sup>[top](#documentr_top)</sup></sup>



```
<dependency>
	<groupId>synapticloop</groupId>
	<artifactId>recipease</artifactId>
	<version>1.0.0</version>
	<type>jar</type>
</dependency>
```





<a name="documentr_heading_15"></a>

## Dependencies - Downloads <sup><sup>[top](#documentr_top)</sup></sup>


You will also need to download the following dependencies:



### compile dependencies

  - `org.json:json:20160810`: (It may be available on one of: [bintray](https://bintray.com/org.json/maven/json/20160810/view#files/org.json/json/20160810) [mvn central](http://search.maven.org/#artifactdetails|org.json|json|20160810|jar))
  - `synapticloop:simplelogger:1.1.1`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/simplelogger/1.1.1/view#files/synapticloop/simplelogger/1.1.1) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|simplelogger|1.1.1|jar))
  - `synapticloop:simpleusage:1.1.2`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/simpleusage/1.1.2/view#files/synapticloop/simpleusage/1.1.2) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|simpleusage|1.1.2|jar))
  - `org.apache.xmlgraphics:xmlgraphics-commons:2.1`: (It may be available on one of: [bintray](https://bintray.com/org.apache.xmlgraphics/maven/xmlgraphics-commons/2.1/view#files/org.apache.xmlgraphics/xmlgraphics-commons/2.1) [mvn central](http://search.maven.org/#artifactdetails|org.apache.xmlgraphics|xmlgraphics-commons|2.1|jar))
  - `org.apache.xmlgraphics:fop:2.1`: (It may be available on one of: [bintray](https://bintray.com/org.apache.xmlgraphics/maven/fop/2.1/view#files/org.apache.xmlgraphics/fop/2.1) [mvn central](http://search.maven.org/#artifactdetails|org.apache.xmlgraphics|fop|2.1|jar))
  - `synapticloop:templar:1.4.1`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/templar/1.4.1/view#files/synapticloop/templar/1.4.1) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|templar|1.4.1|jar))
  - `com.fasterxml.jackson.core:jackson-databind:2.8.5`: (It may be available on one of: [bintray](https://bintray.com/com.fasterxml.jackson.core/maven/jackson-databind/2.8.5/view#files/com.fasterxml.jackson.core/jackson-databind/2.8.5) [mvn central](http://search.maven.org/#artifactdetails|com.fasterxml.jackson.core|jackson-databind|2.8.5|jar))


### runtime dependencies

  - `org.json:json:20160810`: (It may be available on one of: [bintray](https://bintray.com/org.json/maven/json/20160810/view#files/org.json/json/20160810) [mvn central](http://search.maven.org/#artifactdetails|org.json|json|20160810|jar))
  - `synapticloop:simplelogger:1.1.1`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/simplelogger/1.1.1/view#files/synapticloop/simplelogger/1.1.1) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|simplelogger|1.1.1|jar))
  - `synapticloop:simpleusage:1.1.2`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/simpleusage/1.1.2/view#files/synapticloop/simpleusage/1.1.2) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|simpleusage|1.1.2|jar))
  - `org.apache.xmlgraphics:xmlgraphics-commons:2.1`: (It may be available on one of: [bintray](https://bintray.com/org.apache.xmlgraphics/maven/xmlgraphics-commons/2.1/view#files/org.apache.xmlgraphics/xmlgraphics-commons/2.1) [mvn central](http://search.maven.org/#artifactdetails|org.apache.xmlgraphics|xmlgraphics-commons|2.1|jar))
  - `org.apache.xmlgraphics:fop:2.1`: (It may be available on one of: [bintray](https://bintray.com/org.apache.xmlgraphics/maven/fop/2.1/view#files/org.apache.xmlgraphics/fop/2.1) [mvn central](http://search.maven.org/#artifactdetails|org.apache.xmlgraphics|fop|2.1|jar))
  - `synapticloop:templar:1.4.1`: (It may be available on one of: [bintray](https://bintray.com/synapticloop/maven/templar/1.4.1/view#files/synapticloop/templar/1.4.1) [mvn central](http://search.maven.org/#artifactdetails|synapticloop|templar|1.4.1|jar))
  - `com.fasterxml.jackson.core:jackson-databind:2.8.5`: (It may be available on one of: [bintray](https://bintray.com/com.fasterxml.jackson.core/maven/jackson-databind/2.8.5/view#files/com.fasterxml.jackson.core/jackson-databind/2.8.5) [mvn central](http://search.maven.org/#artifactdetails|com.fasterxml.jackson.core|jackson-databind|2.8.5|jar))


### testCompile dependencies

  - `junit:junit:4.12`: (It may be available on one of: [bintray](https://bintray.com/junit/maven/junit/4.12/view#files/junit/junit/4.12) [mvn central](http://search.maven.org/#artifactdetails|junit|junit|4.12|jar))


### testRuntime dependencies

  - `junit:junit:4.12`: (It may be available on one of: [bintray](https://bintray.com/junit/maven/junit/4.12/view#files/junit/junit/4.12) [mvn central](http://search.maven.org/#artifactdetails|junit|junit|4.12|jar))

**NOTE:** You may need to download any dependencies of the above dependencies in turn (i.e. the transitive dependencies)

--

> `This README.md file was hand-crafted with care utilising synapticloop`[`templar`](https://github.com/synapticloop/templar/)`->`[`documentr`](https://github.com/synapticloop/documentr/)

--
