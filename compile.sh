#!/bin/bash

javac -d bin src/cs2720/p2/Sorting.java
javac -cp bin -d bin src/cs2720/p2/SortingDriver.java

java -cp bin cs2720.p2.SortingDriver
