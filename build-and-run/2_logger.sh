#2. Event Logger
cd ../app/logger;
echo Building Event Logger
mvn clean install > ../../logs/logger/build.log;
echo Running Event Logger
java -jar ./target/logger-0.1-SNAPSHOT.jar > ../../logs/logger/run.log &
echo Event logger started as process $!
cd -
echo
echo ====================================================
echo