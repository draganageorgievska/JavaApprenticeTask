# JavaApprenticeTask
Download the zip or clone the Git repository.<br/>
Unzip the zip file (if you downloaded one)<br/>

For Eclipse
<ul>
<li>File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip</li>
	<li>Select the right project</li>
	<li>You might need to install lombok in eclipse by clicking lombok jar file and installing it where the eclipse IDE is</li>
	<li>Restart Eclipse -> Right Click on the project -> Maven -> Update Project</li>
	<li>Choose the Spring Boot Application file (search for @SpringBootApplication)</li>
	<li>Right Click on the file and Run as Java Application</li>
  </ul>
For IntelliJ
<ul>
	<li>File -> Open -> Navigate to the project folder -> Click on pom.xml file -> Click 'Open as Project'</li>
	<li>Find the Spring Boot Application file (search for @SpringBootApplication)</li>
	<li>Right Click on the file and select 'Run "JavaApprenticeTask"'</li>
  </ul>
  
Constaints: when adding a new book genre must be in all caps, when adding new author date of birth must be in format yyyy-mm-dd
