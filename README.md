How to build and run the project
--------------------------------

1.Run the maven command
    mvn clean install
this should run the associated tests for the project.

2. If successful you should get a jar file called sainsburysScraper.jar

3. To run this go to the target folder in the command line and use the command

java -jar sainsburysScraper.jar http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html

the url provided is the argument that is needed to gather the information

the final result should be json that is outputted to the system console.




