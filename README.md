[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_i3a.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_i3a)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/27b04e16c41248d0abad6d5a4ce83911)](https://www.codacy.com/app/jelabra/InciDashboard_i3a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Loader_i3a&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_i3a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_i3a)

# InciDashboard_i3a

InciDashboard module

# Information

This module allows Operators to enter the application and manage all th eincidents they have assigned due to being dangerous. They can also add comments to them, and change the state of those incidents.
The application also allows them to monitorize and see statistics of all of them.


# Authors

* [Elena Allegue González](https://github.com/eleallegue)
* [Marcos Álvarez García](https://github.com/alvarezGarciaMarcos)
* [Anamaria Cotorei](https://github.com/UO251547)
* [Cristina Vena Naredo](https://github.com/cristinavn)

# Running the application 
There are two ways:

## All the modules running in the same instance

Still working on the platform to support it.

## Independent running
### Kafka
Download [Kafka](https://kafka.apache.org/downloads)

`>> tar -xzf kafka_2.11-1.0.1.tgz`

`>> cd kafka_2.11-1.0.1`

Once here, you have to start Zookeeper as it is needed for the correct functioning of Kafka. The one provided by the binary you have previously download is okay for starting it. Then, you can start kafka server.

`>> bin\windows\zookeeper-server-start.bat config/zookeeper.properties`

`>> bin\windows\kafka-server-start.bat config/server.properties`

### Mongo DB
You are not required to download MongoDB as we have it stored in [mLab](https://mlab.com). Though, if you have no internet conexion, you can download it from [here](https://www.mongodb.com/download-center), and run it by means of:

`>> bin/mongod —port 27017`

(Notice that the port 27017 is the default one, and you could have problems if it is being used by any other application. If so, you can change it)

### Maven
For starting the application from the command line using [maven](https://maven.apache.org/) you can use the command:

`>> mvn spring-boot:run`