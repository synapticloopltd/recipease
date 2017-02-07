 <a name="#documentr_top"></a>

> **This project requires JVM version of at least 1.8**






<a name="documentr_heading_0"></a>

# recipease <sup><sup>[top](#documentr_top)</sup></sup>



> A recipe book creator from json -> PDF






<a name="documentr_heading_1"></a>

# Table of Contents <sup><sup>[top](#documentr_top)</sup></sup>



 - [recipease](#documentr_heading_0)
 - [Table of Contents](#documentr_heading_1)
 - [Quickstart](#documentr_heading_2)
   - [Step 1 - Get the jar](#documentr_heading_3)
   - [Step 2 - Create the recipease.json file](#documentr_heading_4)
   - [Step 3 - create the recipes.json files](#documentr_heading_5)
   - [Step 4 - generate the recipe books](#documentr_heading_6)
 - [Building the Package](#documentr_heading_7)
   - [*NIX/Mac OS X](#documentr_heading_8)
   - [Windows](#documentr_heading_9)
 - [Running the Tests](#documentr_heading_10)
   - [*NIX/Mac OS X](#documentr_heading_11)
   - [Windows](#documentr_heading_12)
 - [All-In-One](#documentr_heading_13)
 - [Artefact Publishing - Bintray](#documentr_heading_14)
   - [maven setup](#documentr_heading_15)
   - [gradle setup](#documentr_heading_16)
 - [Artefact Publishing - Github](#documentr_heading_17)
   - [Dependencies - Gradle](#documentr_heading_18)
   - [Dependencies - Maven](#documentr_heading_19)
   - [Dependencies - Downloads](#documentr_heading_20)





<a name="documentr_heading_2"></a>

# Quickstart <sup><sup>[top](#documentr_top)</sup></sup>



<a name="documentr_heading_3"></a>

## Step 1 - Get the jar <sup><sup>[top](#documentr_top)</sup></sup>

Download a release from the github releases [https://github.com/synapticloopltd/recipease/releases](https://github.com/synapticloopltd/recipease/releases)

    Ensure that you retrieve the **-all** classifier



<a name="documentr_heading_4"></a>

## Step 2 - Create the `recipease.json` file  <sup><sup>[top](#documentr_top)</sup></sup>

    The `recipease.json` file can be named anything, although we do recommend leaving it as is

and example file can be found in the `src/test/recipease.json` file, which is included below:



```
{
	"title":"Synapticloop's recipe book",
	"image":"src/test/resources/images/recipes.jpg",
	"subTitle":"\\"Recipes that we have collected and tested from many of our favourite sources and make available to everybody\\"",
	"tocTitle":"Table of Contents",
	"ingredientsTitle": "Ingredients",
	"notesTitle": "Notes from the field...",
	"directionsTitle": "Directions",
	"sections": [
		{
			"title": "Breakfast",
			"image": "./src/test/resources/images/breakfast.jpg", 
			"recipes": [
				{ "import": "breakfast/pancakes.json" }
			]
		},

		{
			"title": "Morning Tea",
			"image": "./src/test/resources/images/morning-tea.jpg", 
			"recipes": [
				{ "import": "morning-tea/scones.json"},
				{ "import": "morning-tea/raspberry-muffins.json"},
				{ "import": "morning-tea/banana-bread.json"},
				{ "import": "morning-tea/anzac-biscuits.json"},
				{ "import": "morning-tea/anzac-biscuits-alternate.json"},
				{ "import": "morning-tea/financiers.json"}
			]
		},
		{
			"title": "Entrees",
			"image": "src/test/resources/images/entrees.jpg", 
			"recipes": [
				{ "import": "entrees/cheese-souffle.json"}
			]
		},
		{
			"title": "Sauces",
			"image": "src/test/resources/images/sauces.jpg", 
			"recipes": [
				{ "import": "sauces/applesauce.json" },
				{ "import": "sauces/teriyaki-sauce.json" },
				{ "import": "sauces/mint-jelly.json" },
				{ "import": "sauces/peanut-sauce.json" },
				{ "import": "sauces/bechamel-sauce.json" },
				{ "import": "sauces/white-sauce.json" }
			]
		},
		{
			"title": "Sides",
			"image": "src/test/resources/images/sides.jpg", 
			"recipes": [
				{ "import": "sides/puree-of-potato-and-celeriac.json" },
				{ "import": "sides/glazed-carrots-with-thyme.json" },
				{ "import": "sides/sweet-potato-gratin.json" },
				{ "import": "sides/aubergine-parmigiana.json" }
			]
		},
		{
			"title": "Hors D'Oeuvres",
			"image": "src/test/resources/images/hors-doeuvres.jpg", 
			"recipes": [
				{ "import": "hors-doeuvres/sausage-rolls.json" }
			]
		},
		{
			"title": "Main Courses",
			"image": "src/test/resources/images/mains.jpg", 
			"recipes": [
				{ "import": "main-courses/baked-ham.json" },
				{ "import": "main-courses/mushroom-risotto.json" },
				{ "import": "main-courses/curry-in-a-hurry.json" },
				{ "import": "main-courses/roast-turkey-with-pan-gravy.json" },
				{ "import": "main-courses/bolognese-sauce.json" },
				{ "import": "main-courses/lasagne.json" },
				{ "import": "main-courses/gnudi.json" }
			]
		},
		{
			"title": "Cakes",
			"image": "./src/test/resources/images/cakes.jpg", 
			"recipes": [
				{ "import": "cakes/lemon-polenta-cake.json" },
				{ "import": "cakes/torta-caprese.json" },
				{ "import": "cakes/carrot-cake.json" },
				{ "import": "cakes/chocolate-raspberry-loaf-cake.json" },
				{ "import": "cakes/greek-yogurt-chocolate-cake.json" },
				{ "import": "cakes/polenta-almond-and-orange-cake.json" }
				
			]
		}
	]
	
}
```





<a name="documentr_heading_5"></a>

## Step 3 - create the `recipes.json` files <sup><sup>[top](#documentr_top)</sup></sup>

For each of the recipes that we want to include, we separate them into individual 
directories and name them as per the recipe name.

You will notice that each of the items in the `recipes` array are just an import 
statement.  For example:



```
{ "import": "breakfast/pancakes.json" }
```



which is a **relative** directory to the `recipease.json` file. (i.e. 
`src/test/resources/breakfast/pancakes.json`, which is as follows:



```
{
	"title":"Pancakes",
	"information": [
		{"Course":"breakfast" },
		{"Time":"under 30 minutes" },
		{"Skill level":"easy" },
		{"Cost":"inexpensive" },
		{"Yield":"about 16 3-inch pancakes" }
	],
	"ingredients": [
		"½ stick (4 Tablespoons) butter 60 gram",
		"1 cup milk",
		"2 eggs",
		"1¼ cups flour",
		"1 tablespoon sugar",
		"2 teaspoons baking powder",
		"½ teaspoon salt",
		"2 tablespoons vegetable oil",
		"§ To go with the pancakes:",
		"Butter at Room Temperature",
		"Maple Syrup"
	],
	"directions": [
		"If your butter is very cold, cut it into 4 or 5 pieces so it will melt more quickly in the milk. Put the butter and 1 cup milk into a small saucepan and set the saucepan on a stove burner. Turn the heat on to medium. It will only take a minute or two for the milk to get hot enough to melt the butter, so stay by the stove, watch, and stir the milk mixture often. When the milk is hot and the butter is melted, take the saucepan off the burner and let the mixture cool a little.",
		"Crack the 2 eggs into a mixing bowl. Beat the eggs together with a fork until the yolks and whites are blended and the eggs become all yellow. Before you add the milk and butter to the eggs, be sure the mixture has cooled. Test it by poking your finger quickly in and out of the saucepan to check. If the mixture is barely warm, add it to the eggs. If it is still hot, let it cool a little longer and then add it to the eggs. Stir the eggs and milk mixture together.",
		"Put the 1¼ cups flour, 1 tablespoon sugar, 2 teaspoons baking powder, and ½ teaspoon salt into a mixing bowl. Stir the flour mixture with a fork, going round and round in the bowl so everything gets mixed together nicely.",
		"Add the flour mixture (the dry ingredients) to the egg mixture (the wet ingredients). Stir everything together with a large spoon, The minute you see that the batter (this uncooked mixture) looks moist with lots of lumps, kind of like soupy cottage cheese, stop stirring! If you stir too much, the pancakes will be tough.",
		"Spread 1 teaspoon of the vegetable oil all over the bottom of a large skillet, using your fingers or a piece of paper towel. Put the skillet on a burner and turn the heat to medium-high. In a few seconds, sprinkle a few drops of water on the bottom of the skillet, and if they dance, pop, and sputter, the skillet is hot. The first pancake you make is a test pancake to get the feel of how hot the skillet is, and how long it will take to cook the pancake on both sides. Drop one large spoonful of batter into the skillet.",
		"As the pancake cooks, watch for tiny bubbles forming on the top and sides, When you see the bubbles all over the topside, this usually means the pancake is ready to turn over. Slide a metal spatula under the pancake and gently turn it over. It should look golden on top. If it looks very brown, turn the heat down a little. Let it cook a few more seconds, then slide the metal spatula again under the pancake, and lift the edge up so you can look and see if the bottom is golden. If it is, remove the pancake from the skillet onto a plate and cut into the middle. If it isnʼt runny it is done.",
		"Now you are ready to get serious about making your pancakes. Have everything ready to go—butter, syrup, plates, knives, forks napkins—so you can make 3 or 4 pancakes at one time, which is a good serving for each person. Make the pancakes just as you did with the test pancake, this time dropping 3 or 4 large spoonfuls (one at a time) of the batter around the pan, not touching As you remove the pancakes from the skillet, serve them immediately and finally serve yourself. Then you can make more pancakes for those with hearty appetites. Or your friends can take a turn at being a cook and try their hand at making pancakes."
	],
	"notes": [
		"Springle brown sugar over the pancakes and then drizzle lemon juice over it for a sweet 'n' sour taste sensation!"
	]
	
}
```




<a name="documentr_heading_6"></a>

## Step 4 - generate the recipe books <sup><sup>[top](#documentr_top)</sup></sup>

Once you have set up your `recipease.json` files and all of your sections with the imported recipes, you are ready to generate the PDF files.

Simply by

`java -jar recipease-1.1.0-all.jar <input file>`


For example:

`java -jar recipease-1.1.0-all.jar src/test/resources/recipease.json`


This will then generate the PDF files in the current (i.e. `.`) directory:

  - `a4.pdf` - for the desktop, and you can see the latest generation here: [a4.pdf](a4.pdf)
  - `mobile.pdf` - for mobile devices, and you can see the latest generation here: [mobile.pdf](mobile.pdf)




```
Usage:

	java -jar recipease.jar <inputfile>

Where:
	<inputfile> is the JSON input file to parse

The output directory is __ALWAYS__ the current directory.  The following files 
will be output:

  a4.pdf - An A4 sized recipe book
  mobile.pdf - A recipe book sized for mobile devies


```






<a name="documentr_heading_7"></a>

# Building the Package <sup><sup>[top](#documentr_top)</sup></sup>



<a name="documentr_heading_8"></a>

## *NIX/Mac OS X <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`./gradlew build`




<a name="documentr_heading_9"></a>

## Windows <sup><sup>[top](#documentr_top)</sup></sup>

`./gradlew.bat build`


This will compile and assemble the artefacts into the `build/libs/` directory.

Note that this may also run tests (if applicable see the Testing notes)



<a name="documentr_heading_10"></a>

# Running the Tests <sup><sup>[top](#documentr_top)</sup></sup>



<a name="documentr_heading_11"></a>

## *NIX/Mac OS X <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`gradlew --info test`



<a name="documentr_heading_12"></a>

## Windows <sup><sup>[top](#documentr_top)</sup></sup>

From the root of the project, simply run

`gradle --info test`

if you do not have gradle installed, try:

`./gradlew.bat --info test`


The `--info` switch will also output logging for the tests




<a name="documentr_heading_13"></a>

# All-In-One <sup><sup>[top](#documentr_top)</sup></sup>

This project's artefact output is an 'all in one' jar which includes all runtime dependencies.

This should appear in the artefact repository along with the compiled code, as a convention, this is usually appended with an `-all` classifier

For example:

`recipease-1.1.0.jar -> recipease-1.1.0-all.jar`





<a name="documentr_heading_14"></a>

# Artefact Publishing - Bintray <sup><sup>[top](#documentr_top)</sup></sup>

This project publishes artefacts to [bintray](https://bintray.com/)

> Note that the latest version can be found [https://bintray.com/synapticloop/maven/recipease/view](https://bintray.com/synapticloop/maven/recipease/view)



<a name="documentr_heading_15"></a>

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





<a name="documentr_heading_16"></a>

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





<a name="documentr_heading_17"></a>

# Artefact Publishing - Github <sup><sup>[top](#documentr_top)</sup></sup>

This project publishes artefacts to [GitHub](https://github.com/)

> Note that the latest version can be found [https://github.com/synapticloopltd/recipease/releases](https://github.com/synapticloopltd/recipease/releases)

As such, this is not a repository, but a location to download files from.



<a name="documentr_heading_18"></a>

## Dependencies - Gradle <sup><sup>[top](#documentr_top)</sup></sup>



```
dependencies {
	runtime(group: 'synapticloop', name: 'recipease', version: '1.1.0', ext: 'jar')

	compile(group: 'synapticloop', name: 'recipease', version: '1.1.0', ext: 'jar')
}
```



or, more simply for versions of gradle greater than 2.1



```
dependencies {
	runtime 'synapticloop:recipease:1.1.0'

	compile 'synapticloop:recipease:1.1.0'
}
```





<a name="documentr_heading_19"></a>

## Dependencies - Maven <sup><sup>[top](#documentr_top)</sup></sup>



```
<dependency>
	<groupId>synapticloop</groupId>
	<artifactId>recipease</artifactId>
	<version>1.1.0</version>
	<type>jar</type>
</dependency>
```





<a name="documentr_heading_20"></a>

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
