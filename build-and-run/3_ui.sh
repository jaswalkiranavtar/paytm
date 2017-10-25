#3. Resource App
cd ../app/ui;
echo Building UI App
mvn clean install > ../../logs/ui/build.log;
echo Running UI App
java -jar ./target/ui-0.0.1-SNAPSHOT.jar > ../../logs/ui/run.log &
echo UI App started as process $!
cd -
echo
echo ====================================================
echo