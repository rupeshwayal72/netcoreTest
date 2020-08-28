package com.netcore.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.netcore.qa.base.TestBase;

public class TestUtil extends TestBase {

	static String defaultdir = System.getProperty("user.dir");

	public static void takeScreenshotAtEndOfTest() throws IOException {
		String dateName = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());

		File scrFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		String currentDir = (defaultdir + "/screenshots/" + dateName);
		FileUtils.copyFile(scrFile, new File(currentDir + System.currentTimeMillis() + ".PNG"));

	}
}
