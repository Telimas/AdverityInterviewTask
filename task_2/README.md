## Task 2

Automatic email sender in case of any weather anomalies using data from
local [meteorology website](http://old.meteo.md/newen/avcodhiden.htm).

### Used Tools & Libraries

* [REST Assured](https://rest-assured.io/)
* [Jsoup](https://jsoup.org/)
* [JavaxMail](https://docs.oracle.com/javaee/7/api/javax/mail/package-summary.html)
* [Gson](https://github.com/google/gson)

### Prerequisites

There are two things which should be configured before running test:

1. Create environment variables for storing email and password data as a sender;
    * For email environment variable should be named: `INTERVIEW_USER`
    * For password environment variable should be named: `INTERVIEW_PASS`
   #### _In case you miss email and password data, please contact me via email or messenger(s)._
2. Come up with an email address to whom you want to send the letter

### Run Tests

* Using Java
   ```sh
   java -Dtask2=true -Dto={destination_email} -jar tests-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
    * _For more info on how to get JAR with dependencies, please refer to
      the [Build JAR Guide](https://github.com/Telimas/AdverityInterviewTask#building-jar)_
* Using Maven
   ```sh
   mvn test -Dtest=HydroMeteorologicChecker -Dto={destination_email}
   ```

### Email Example

![email-example](https://user-images.githubusercontent.com/32519338/138098070-664619f7-5457-4d0d-a85a-8c4efaf79629.png)