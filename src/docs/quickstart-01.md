
## Step 3 - create the `recipes.json` files

For each of the recipes that we want to include, we separate them into individual 
directories and name them as per the recipe name.

You will notice that each of the items in the `recipes` array are just an import 
statement.  For example:

```
{ "import": "breakfast/pancakes.json" }
```

which is a **relative** directory to the `recipease.json` file. (i.e. 
`src/test/resources/breakfast/pancakes.json`, which is as follows:

