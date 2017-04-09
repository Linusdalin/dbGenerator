Creating a new project:

 - First create a new java project
 - add directories src/commons and scripts
 - copy files to commons


 - Setting up the project in dbGenerator

        In config:
                             new Project("myProject", "C:\\Users\\Linus\\Documents\\GitHub\\myproject\\src", "commons", "test")
                                     .withDatabase("C:\\Users\\Linus\\Documents\\GitHub\\myproject\\scripts", "myProject")



 - Create a build configuration and run it.

        Now there should be a generated script file in the new project

 - Add a table

                                     .withTable(new DataTable("Tablename", "dataDir", "This is a description")

                                             .withDataElement(new DataElement(DataType.TIMESTAMP, "date"))
                                             .withDataElement(new DataElement(DataType.STRING, "description"))
                                     )

 - Create the corresponding package in the new project (here dataDir)

 - Run the generation again.

        Now we should have two files in the package dataDir and a test file in the test directory

 - Add the appropriate libs
         junit4.10
         sql connector
         json lib

 - Now it should be possible to run the generated test case that will write a line to the database


Time consumptions:
    - setting up a project with database and a test table 2 hours