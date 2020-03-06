

run: build/libs/ar-1.0-SNAPSHOT-all.jar 
	java -jar build/libs/ar-1.0-SNAPSHOT-all.jar 

test:
	./gradlew test

.PHONY: run test

all_files := $(shell find src/ -type f -name '*.kt')

build/libs/ar-1.0-SNAPSHOT-all.jar: $(all_files) src/main/java/dev/mee42/antlr4/ArParser.java
	./gradlew shadowJar

src/main/java/dev/mee42/antlr4/ArParser.java: src/main/antlr4/Ar.g4
	java -jar /usr/local/lib/antlr-4.8-complete.jar -o src/main/java/dev/mee42 src/main/antlr4/Ar.g4 -package dev.mee42.antlr4
	@mkdir -p src/main/java/dev/mee42/antlr4
	@mv src/main/java/dev/mee42/src/main/antlr4/* src/main/java/dev/mee42/antlr4
	@rm -r src/main/java/dev/mee42/src
