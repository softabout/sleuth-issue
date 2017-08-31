# Sleuth-Issue Demo
Demonstrates the loss of Sleuth-TraceId when calling external resources like MongoDB with the Reactive Streams API

### Requirements
* MongoDB
    * use local installation
        * change mongo uri to reflect your local mongo in application.yml 
    * alternatively: use Vagrant with contained Mongo-Docker container. 
        * Vagrant-Image defines private network with IP 192.168.20.10
        * MongoDB listens to default port 27017
        * preconfigured in applicaation.yml
        * install VirtualBox and Vagrant and call vagrant up in root folder of project
        

### Start Application
* ./gradlew bootRun
* curl -v -X POST http://localhost:44000/api (init db)
* curl -v http://localhost:44000/api/issue (show issue)
* curl -v http://localhost:44000/api/noissue (show without reactive mongo usage)

See output in console (System.out)   