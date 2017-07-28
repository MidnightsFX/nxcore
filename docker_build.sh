rm build/libs/* # Clean up the previous build
./gradlew build  # Rebuild

# Run the new artifact
docker-compose rm -f # Remove stopped containers, don't ask y/n (-f)
docker-compose build
docker-compose up # Build and Launch the new container