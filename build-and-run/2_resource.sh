#2. Config Server
cd ../resource;
echo Building resource app
mvn clean install > ../logs/resource/build.log;
echo Running resource app
java -jar ./target/resource-0.0.1-SNAPSHOT.jar > ../logs/resource/run.log &
echo Resource App started as process $!
cd -
echo
echo ====================================================
echo