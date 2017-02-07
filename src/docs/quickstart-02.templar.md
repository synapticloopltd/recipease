
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
This will then generate the PDF files in the `build/docs` directory:{\n}
{\n}
  - `build/docs/a4.pdf` - for the desktop, and{\n}
  - `build/docs/mobile.pdf` - for moblie devices{\n}
{\n}
{\n}