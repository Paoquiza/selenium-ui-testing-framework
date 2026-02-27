package com.restfulbooker.constants;

public final class AppConstants {

    private AppConstants() {
    }

    public static final String BASE_URL = "https://automationintesting.online/";
    public static final String ADMIN_URL = "https://automationintesting.online/admin";

    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "password";

    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 30;

    public static final String REPORTS_PATH = System.getProperty("user.dir") + "/reports/";
    public static final String REPORT_TITLE = "Restful-Booker UI Test Report";
    public static final String REPORT_NAME = "Selenium Test Execution Report";
}
