package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",//junitten gelio ve bize html formatta report verio sol altta "cucumber-report.html"
                "json:target/cucumber-report.json",//junitten gelio ve bize json formatta report verio sol altta "cucumber-report.json"
                "rerun:target/rerun.txt",// buda bize failed testlerin raporunu verio ve failed içinden tekrar çalıştırıp fail teestleri koşuoz
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",// bunu kendi dependency si var pom içinde "me.jvt.cucumber" die dependency farklı bir report bunun sayesinde sol attaki cucumber directory çıkıyo
        },
        features = "src/test/resources/features", // feature a sağ click ondan sonra yol olarak kopyalayı sec ve buranın içine yapıştır "content root"
        glue = "com/step_definitions",           // sağ click setep_definition yol olarak kopyaladan "sourceRoot" sec ve içine yapıştır
        dryRun = false,                         // eğer   seneryoların çıktısını almak istemiyorsak "true" ,açılıp sonuç almak senaryoların çıktısı için için "false" yaparız
        tags = "@netflix"// seneryoların birini çalıştırmak istiyorsak üzerine yazdığımız tagi "@librarin" burda tag içine yazdığımızda yalnız o senaryo çalışır çıktısını alırız Fakat Feature nin üst satırını yazınca tüm seneryolarıçalıştırır çıktı alırız  birden fazlada tags yapabiliriz  "@admin or @student"

)

public class CukesRunner {

}
/*
CLASS NOTES: DAY 15-2
-Today's schedule:

- Tell me about your framework?

2-3-4 Day's schedule:

#1- dryRun
#2- tags
#3- html reports
#4- Background
#5- Hooks
#6- Parameterization
#7- dataTables
#8- scenario outlines
#9- parallel testing
#10- report generation

------------------------------------------------------------------------------

What is a framework?

- Frameworks are basically commonly used, and proven approach of creating file and folder structure, creating smart logic (implementing design patterns, utilities) to be able to easily handle and maintain our project

Why do we create a framework?
- Frameworks are created to apply reusability, maintainability, scaleability, easy to use, easy to understand.


- Tell me about your framework?

    - Basically they want to hear how you created your framework, and what we used in it?

    - What tools we used in our project?
    - Which programming language we used in our project?
    - Which testing tools we used in our project, and why?
    - Which design patterns we used in our project, and why?
    - What kind of folder structure we created, and why?

- I used Java as programming language in my project
- Selenium
- Maven
- Cucumber
- TestNG/JUnit

Design patterns we used:
- Page Object Model Design Pattern
- Singleton Design Pattern (Driver)

Utility classes we created:
- Driver
- ConfigurationReader
- BrowserUtils

We implemented BDD approach

- We also implemented Data Driven Testing in our project
- .properties file
- parameterization (intro)
- Scenario Outline
- APACHE POI (dependency)--> read from and write to excel

----------------------------------------------------------------------

    - src
        - test
            - java
                - com.cydeo
                    - pages
                    - step_definitions
                        - GoogleStepDefinitions
                        -
                    - runners
                        - CukesRunner
                    - utilities
                        - Driver
                        - ConfigurationReader
                        - BrowserUtils
            - resources
                - features
                    - googleSearch.feature
                    - login.feature
                - testData (we don't have this one)
    - pom.xml
    - configuration.properties

----------------------------------------------------------------------

- WHAT IS TDD (Test Driven Development)?

    - Bug-free development

- How do we implement TDD?

#1- Write unit tests
#2- Run the tests
#3- Tests will fail
#4- Write just enough code to pass the currently written tests (MVP)
#5- Run the tests
#6- Tests will PASS
#7- REFACTOR & REPEAT: We write more tests, and repeat the TDD CYCLE.


- WHAT IS BDD (Behavior Driven Development)?

- BDD is extension of TDD.
    - What does this mean?
    - It means the overall implemented logic is very similar.

- How is BDD similar to TDD?
    - Instead of writing test, we write SCENARIOS.
    - We implement the logic that turns those scenarios into actions using JAVA+SELENIUM+JUNIT.

- What are the 2 different sides of BDD?

    #1- BUSINESS LAYER : feature files
    #2- IMPLEMENTATION LAYER : step definitions

#1- BUSINESS LAYER : feature files
    - Where we write our features and scenarios in Gherkin language
    - Gherkin is basically English.
    - It makes it very easy to understand for non-technical member of the team
    - We use certain keywords to implement Cucumber logic in feature files.
        - Feature:
        - Scenario:
        - Given, When, Then, And, But

#2- IMPLEMENTATION LAYER : step definitions
    - We generate snippets and implement them in "step_definitions" package

- What is a snippet?
    - Unimplemented step definition methods that are automatically generated by Cucumber.


@wip = work in progress


- Different ways of generatin snippet:

#1- Run the RunnerClass and generate the snippet in console
#2- Hover over the unimplemented step --> Create step definition --> Select class where we want it
    --> We can generate snippet for more steps:
        - Hover over one line --> More actions --> Create all step definitions
#3- Put your cursor on the unimplemented line:
    Windows: ALT + Enter --> Create step definition --> Select class where we want it
    Mac: Option + Enter --> Create step definition --> Select class where we want it

------------------------------------------------------

- We can re-use the step we generate in feature files, but we will have only 1 snippet and 1 implementation for that specific STEP.
- Every time we use the same step, our code will find the implementation and execute the same method regardless of which feature or sceario we use our step from.


-------------------------------------------------------

Background:
    - Background is very similar to @BeforeMethod in TestNG.
    - @BeforeMethod executes given code/method before each Test in that SPECIFIC CLASS.

    - Background runs before each SCENARIO in that SPECIFIC FEATURE FILE.
    - The step we pass under the "Background:" will execute only once before each step

    - Important thing to keep in mind is that "Background" will be applying to each and every scenario in the same feature file.
    - Therefore we must make sure every scenario is able to pick up and continue where the background is leaving the code.


-------------------------------------------------------


HOOKS CLASS:

    - Hooks class will allow us to pass pre and post conditions for each scenario and each step.
    - Hooks class is seperated from feature file
    - Therefore it will go into the implementation side (step_definitions)

- How does my project knows where to find the Hooks class and execute the annotations?
    - Basically logic is coming from the cucumber annotations and also glue path we provide in the runner class.


- Can we create more than more @Before, @After, @BeforeStep, @AfterStep
- Yes.

- If we have multiple versions of the same annotation, we can prioritize the running order using the "order" keyword.

- The lower the number passed in the order, earlier it will executed.
- The methods will be executed in the order it is specified with numbers.

    @Before (order = 1)
    public void setupScenario(){
        System.out.println("====Setting up browser using cucumber @Before");
    }

    @Before (value = "@login", order = 2)
    public void setupScenarioForLogins(){
        System.out.println("====this will only apply to scenarios with @login tag");
    }

- We can specify which annotation is running for which scenarios or features using @TAGS.
- If I want some scenario/feature to have pre- /post- conditions, I can use certain @TAG, and pass the same @TAG into the annotation in Hooks class.


@Before
    - comes from cucumber-java dependency
    - this will change the behavior of the method we use it.
    - this method will be running BEFORE each and every SCENARIO in our project (unless we specify with @TAGS)

@After
    - comes from cucumber-java dependency
    - this will change the behavior of the method we use it.
    - this method will be running AFTER each and every SCENARIO in our project (unless we specify with @TAGS)

@BeforeStep
    - comes from cucumber-java dependency
    - this will change the behavior of the method we use it.
    - this method will be running BEFORE each and every STEP in our project (unless we specify with @TAGS)

@AfterStep
    - comes from cucumber-java dependency
    - this will change the behavior of the method we use it.
    - this method will be running AFTER each and every STEP in our project (unless we specify with @TAGS)


 */