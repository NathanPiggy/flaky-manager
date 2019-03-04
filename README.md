
# Flaky Manager

Flaky manager is a tool for the flaky tests life cycle, it provide the convenient way to detect, record and fix the flaky test during the daily development job,make the flaky tests visualization and easy to be managed.

* * *

# INFOMATION

You can get the latest progress and report of the project from **progress\PROJECT_PROGRESS.md**.
 and **report\PROJECT_REPORT.md**

* * *

# HAVE A TRY

You can easily have a try when you checked out the project.

**First**: Run the **initial** batch.
**Second**: Run the **flaky test** batch.
| Environment      | Scripts    | 
| --------- | -------- |
| Windows| initial.bat  |
| Windows| flaky test.bat  |
| Linux| initial.sh|
| Linux| flaky test.sh|

* * *
# GETTING START

After ran the **initial** batch, you can integrate the plugin into your project with adding below code in your project’s pom file.

```xml
<extensions>
	<extension>
		<groupId>com.flaky.maven.extension</groupId>
		<artifactId>flaky-maven-extension</artifactId>
		<version>1.0-SNAPSHOT</version>
	</extension>
</extensions>
```

Further more, you can use flaky-lifecycle-manager as your demo project to test the plugin, i have added the unit test the simulate the flaky test, the random program will genarate the integer randomly in range 0 ~ 10, and the test case will assert the random program will return a num greater than 5, that mean it will fail to pass the test sometime.

**Random program:**
```java
    public static int getRandomNumber() {
        int randomNum = (int)(Math.random()*10);
        System.out.println("random result:" + randomNum);
        return randomNum;
    }
```

**Test case:**

```java
    @Test
    public void getRandomNumber() {
        assertTrue(RandomUtil.getRandomNumber() > 5);
    }
```

* * *

# MORE INFOMATION

You Can Manage This Project's Flaky Test Here:
==http://134.175.194.57:10080/flaky/index?projectId=flaky-lifecycle-manager==

artifactId in pom.xml will be used for your projectId when integrate in your own project.

If you have any suggestions, please let me know.


