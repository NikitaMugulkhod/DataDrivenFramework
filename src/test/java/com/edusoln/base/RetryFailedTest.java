 package com.edusoln.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

	int count = 0;
	int retrylimit = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (count < retrylimit) {
			count++;  //2
			System.out.println("Retrying with attemt-"+count);
			return true;   //retry
		}
		return false;
	}

}
