# Clean up the previous build
rm build/libs/*
# Rebuild
./gradlew build
# Run the new artifact
java -jar build/libs/spring-boot-docker-nxcore*.jar -debug
