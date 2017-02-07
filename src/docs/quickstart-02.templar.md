
## Step 4 - generate the recipe books{\n}

{\n}

Once you have set up your `recipease.json` files and all of your sections with 
the imported recipes, you are ready to generate the PDF files.{\n}
{\n}
Simply by{\n}
{\n}
`java -jar recipease-{version}-all.jar <input file>`{\n}

{\n}
{\n}
For example:{\n}{\n}
`java -jar recipease-{version}-all.jar src/test/resources/recipease.json`{\n}
{\n}
{\n}
This will then generate the PDF files in the current (i.e. `.`) directory:{\n}
{\n}
  - `a4.pdf` - for the desktop, and you can see the latest generation here: [a4.pdf](a4.pdf){\n}
  - `mobile.pdf` - for mobile devices, and you can see the latest generation here: [mobile.pdf](mobile.pdf){\n}
{\n}
{\n}