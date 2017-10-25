#2. Config Server
cd ../app/oauth2;
echo Building OAuth2 Server
mvn clean install > ../../logs/oauth2/build.log;
echo Running OAuth2 Server
java -jar ./target/oauth2-0.0.1-SNAPSHOT.jar > ../../logs/oauth2/run.log &
echo OAuth2 Server started as process $!
cd -
echo
echo ====================================================
echo