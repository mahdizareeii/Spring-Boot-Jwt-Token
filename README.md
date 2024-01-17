How to deploy the project on a server:

Install Nginx : 
apt-get update
apt upgrade
apt-get install nginx
apt-get install systemd


Install Java :
apt-get update
apt-get install default-jre
apt-get install default-jdk

Check Java version to see if installation was successfull: java -version
be careful you should install the version of java that you used in your project for example if your project using java 17 you should install that version
sudo apt install openjdk-17-jdk

then make a directory in your server for example 'api'
mkdir api
cd api
then clone your jar file in the folder with git clone for example:
git clone https://github.com/mahdizareeii/TestJar
after clone the jar file go in that directory for example:
cd TestJar
and use the 'ls' command to see the files and write this command:
java -jar TestJwt-1.0.jar
if you didn't have any error your project will run you can see it by searching your ip in your browser for example:
http://192.141.168.245:8080/
