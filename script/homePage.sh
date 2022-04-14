export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home 
source ~/.bash_profile ~/.zsh_profile
cd ../src/
javac -d ../bin/ test/acceptance/Homepage/Homepage*.java
cd ../bin/
java org.junit.runner.JUnitCore test.acceptance.Homepage.HomepageTest

