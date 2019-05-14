package sample.report

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.logging.model.ILogRecord
import com.kms.katalon.core.logging.model.TestCaseLogRecord
import com.kms.katalon.core.logging.model.TestStatus
import com.kms.katalon.core.logging.model.TestStepLogRecord
import com.kms.katalon.core.logging.model.TestSuiteLogRecord
import com.kms.katalon.core.logging.model.TestStatus.TestStatusValue
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.internal.DateUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class SampleReportUtil {

	public static void writeTxtReport(TestSuiteLogRecord testSuiteLogRecord, File reportFolderFile, String reportName) {
		try {
			TxtReportBuilder reportBuilder = new TxtReportBuilder();
			reportBuilder.newLineFrom("[Test Suite Name] " + testSuiteLogRecord.getName());
			reportBuilder.newLineFrom("[Browser] " + testSuiteLogRecord.getBrowser());

			for(ILogRecord childRecord : testSuiteLogRecord.getChildRecords()){
				TestCaseLogRecord testCaseLogRecord = (TestCaseLogRecord) childRecord;
				reportBuilder.newLineFrom("\t[Test Case Name] " + testCaseLogRecord.getName());
				reportBuilder.newLineFrom("\t[Test Case Status] " + testCaseLogRecord.getStatus().getStatusValue().toString());
				reportBuilder.newLineFrom("\t[Start At] " + DateUtil.getDateTimeFormatted(testCaseLogRecord.getStartTime()));
				for(ILogRecord testStepRecord : testCaseLogRecord.getChildRecords()) {
					TestStepLogRecord testStepLogRecord = (TestStepLogRecord) testStepRecord;
					reportBuilder.newLineFrom("\t\t[Test Step] " + testStepLogRecord.getName()
							+ " : " + testStepLogRecord.getStatus().getStatusValue().toString());
					if(testStepLogRecord.getStatus().getStatusValue().equals(TestStatusValue.FAILED)) {
						reportBuilder.newLineFrom("\t\t[Failure Reason] " + testStepLogRecord.getMessage());
					}
				}
				reportBuilder.newLineFrom("\t[Test Case End] -------");
			}
			reportBuilder.newLineFrom("[Test Suite End] -------");
			File reportFile = new File(reportFolderFile, reportName + ".txt");
			reportFile.createNewFile();
			FileUtils.writeStringToFile(reportFile, reportBuilder.build());
			System.out.println(reportBuilder.build());
			System.out.println("Report is available at: " + reportFile.getAbsolutePath());
		} catch(Exception e) {
			KeywordLogger.getInstance(this.class).logInfo(e.getMessage());
		}
	}
}
