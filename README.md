How to deploy project on server:

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
