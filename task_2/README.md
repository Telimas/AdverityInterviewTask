## Task 2

Automatic email sender in case of any weather anomalies using data from
local [meteorology website](http://old.meteo.md/newen/avcodhiden.htm).

### Why Meteorology Notification Service?

After doing a quick research of local meteorology websites I have realised none of them have a notification mechanism. I
believe my implementation will help me know about local weather anomalies published by experts.

### Used Tools & Libraries

* [REST Assured](https://rest-assured.io/)
* [Jsoup](https://jsoup.org/)
* [JavaxMail](https://docs.oracle.com/javaee/7/api/javax/mail/package-summary.html)
* [Gson](https://github.com/google/gson)

### Prerequisites

There are two things which should be configured before running test:

1. Find user and password data as email sender

* This data will be attached to the email after all work on this project is done
  #### _In case you miss email and password data, please contact me via email or messenger(s)._

3. Come up with an email address to whom you want to send meteorology notification(s)

### Run Tests

* Using Java
   ```sh
   java -Dtask2=true -Duser={sender_email_user} -Dpass={sender_email_password} -Dto={destination_email} -jar tests\target\tests-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
    * _For more info on how to get JAR with dependencies, please refer to
      the [Building JAR Guide](https://github.com/Telimas/AdverityInterviewTask#building-jar)_
* Using Maven
   ```sh
   mvn test -Dtest=HydroMeteorologicChecker -Duser={sender_email_user} -Dpass={sender_email_password} -Dto={destination_email}
   ```

### Email Example

![email-example](https://user-images.githubusercontent.com/32519338/138098070-664619f7-5457-4d0d-a85a-8c4efaf79629.png)
