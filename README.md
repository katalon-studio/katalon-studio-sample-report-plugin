# katalon-studio-sample-report-plugin

A sample custom keyword plugin to demonstrate how to generate a customized report.

**This plugin provides**:
- A test listener that automatically generates a test suite report with a customized format as a text file under *Reports* folder.

## Better Alternative: Katalon TestOps

[Katalon TestOps](https://analytics.katalon.com) is a web-based application that provides dynamic perspectives and an insightful look at your automation testing data. You can leverage your automation testing data by transforming and visualizing your data; analyzing test results; seamlessly integrating with such tools as Katalon Studio and Jira; maximizing the testing capacity with remote execution.

* Read our [documentation](https://docs.katalon.com/katalon-analytics/docs/overview.html).
* Ask a question on [Forum](https://forum.katalon.com/categories/katalon-analytics).
* Request a new feature on [GitHub](CONTRIBUTING.md).
* Vote for [Popular Feature Requests](https://github.com/katalon-analytics/katalon-analytics/issues?q=is%3Aopen+is%3Aissue+label%3Afeature-request+sort%3Areactions-%2B1-desc).
* File a bug in [GitHub Issues](https://github.com/katalon-analytics/katalon-analytics/issues).

## Relevant project structure
```bash
katalon-studio-sample-report-plugin
├── Keywords
│   ├── sample.report
│   │   ├── SampleReportUtil.groovy
│   │   ├── TxtReportBuilder.groovy
├── Test Listeners
│   ├── SampleReportTSListener.groovy
```

## How to build
Open this project with Katalon Studio.

Run the following command at the root folder:

```sh
gradle katalonPluginPackage
```

The plugin will be located at *build/libs/katalon-studio-sample-report-plugin.jar* afterwards.

## How to use 

Copy the above jar file and place it under *Plugins* folder of your Katalon project.
