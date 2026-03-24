#!/bin/bash

# create bin folder if not exists
mkdir -p bin

# compile
javac -d bin src/main/*.java

# run
java -cp bin main.MainMenu