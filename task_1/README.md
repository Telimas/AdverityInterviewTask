## Task 1

Deserializing responses from [public API](https://jsonplaceholder.typicode.com/) into POJO classes with later
manipulations.

### Used Tools & Libraries

* [Cucumber](https://cucumber.io/)
* [Gherkin](https://cucumber.io/docs/gherkin/)
* [PicoContainer](https://cucumber.io/docs/cucumber/state/)
* [REST Assured](https://rest-assured.io/)
* [Gson](https://github.com/google/gson)
* [Allure](https://github.com/allure-framework)

### Run Tests

* Using Java
   ```sh
   java -jar tests-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
    * _For more info on how to get JAR with dependencies, please refer to
      the [README](https://github.com/Telimas/AdverityInterviewTask#readme)_
* Using Maven
   ```sh
   mvn test -Dtest=RunCucumberTests
   ```

### Reporting

As reporting plugin was used Allure. `allure-results` folder is created, after tests are finished.

To visualize these reports you have to:

1. Install Allure

#### _For more info, please refer to the [Installation Guide](https://docs.qameta.io/allure/#_manual_installation)_

2. Run command

```sh
allure serve allure-results
```

Note that after command run you'll be automatically redirected to the generated report.

### Report Example:

![allure-example](https://user-images.githubusercontent.com/32519338/138086745-ea5f9b49-cf40-4e55-9573-051efce7b14a.png)