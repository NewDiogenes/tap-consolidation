Trip Consolidation Service
==========================

Description
-----------
A service for reading in public transport tap events and consolidating them into trip charges


Requirements
------------
This service required java 1.8+ in order to run

Running
-------
Service dependencies can be installed by running the following command in the root directory

    ./gradlew clean install
    
The application can be run by running the following command in the root directory

    ./gradlew clean run -Dfile=<filename>.csv

Where filename is the relative path of the file you intend to use for an input.

An example input file has been included and can be used as follows

    ./gradlew clean run -Dfile=testCase.csv
    
Limitations and Assumptions
---------------------------

In the interest of completing this task in a timely fashion, 
certain steps that would normally be part of the development process have been skipped and 
certain assumptions have been made about the test data.

### Test coverage
 The test coverage for this application is much lower that would normally be the case.
 The logic around determining the charge amount has been left un-tested and the coverage for many of 
 the other services are minimal
 
### Un-Implemented/Minimal Requirements
For the moment, the output is printed to system.out instead of being written to a csv file.
The logic for calculating the charge amount for a trip is also minimally implemented.
The test data supplied is also pretty basic.
 
###Assumptions
1: The input data will only contain data values, without a header line containing the column names

2: The input data is well formed (there are basically no input validations)

3: Cases that do not exactly match the scenarios described in the requirements document are 
allowed to be ignored (for example, an OFF tap followed by an OFF tap)

4: Trips that are not completed withing the input data are allowed to be ignored 
(for example, if the last tap read is an ON tap, no trip is recorded for that tap)

