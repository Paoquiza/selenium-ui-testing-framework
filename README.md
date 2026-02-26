# Selenium UI Testing Framework

A comprehensive Selenium WebDriver UI testing framework for the [Restful-Booker Platform](https://automationintesting.online/), built with Java, TestNG, and the Page Object Model pattern.

## Tech Stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 11 | Programming language |
| Selenium WebDriver | 4.27.0 | Browser automation |
| TestNG | 7.10.2 | Test framework |
| WebDriverManager | 5.9.2 | Driver management |
| ExtentReports | 5.1.2 | HTML reporting |
| Maven | 3.x | Build & dependency management |

## Project Structure

```
src/
├── main/java/com/restfulbooker/
│   ├── constants/          # Application constants
│   ├── utils/              # Driver factory, config reader, report manager
│   ├── pages/              # Page Object classes
│   └── components/         # Reusable UI components
└── test/
    ├── java/com/restfulbooker/tests/   # Test classes
    └── resources/                       # Config & TestNG suites
```

## Test Coverage

| Test Class | Tests | Groups | Description |
|------------|-------|--------|-------------|
| HomePageTest | 6 | smoke, regression | Home page elements and layout |
| ContactFormTest | 6 | smoke, regression | Contact form validation and submission |
| AdminLoginTest | 5 | smoke, regression | Admin authentication flows |
| AdminRoomTest | 5 | regression | Room CRUD operations |
| NavigationTest | 5 | smoke, regression | Header, footer, and navigation |
| **Total** | **27** | | |

## Prerequisites

- Java 11 or higher
- Maven 3.x
- Chrome or Firefox browser

## Running Tests

### Smoke Tests (default)
```bash
mvn test -P smoke -Dbrowser=chrome -Dheadless=true
```

### Full Regression Suite
```bash
mvn test -P regression -Dbrowser=chrome -Dheadless=true
```

### Firefox Browser
```bash
mvn test -P smoke -Dbrowser=firefox -Dheadless=true
```

### Headed Mode (visible browser)
```bash
mvn test -P smoke -Dbrowser=chrome -Dheadless=false
```

## Configuration

Edit `src/test/resources/config.properties`:

```properties
base.url=https://automationintesting.online/
admin.username=admin
admin.password=password
browser=chrome
headless=true
```

All properties can be overridden via system properties (`-Dproperty=value`).

## Reports

After test execution, HTML reports are generated in the `reports/` directory:

- **ExtentReports**: `reports/TestReport.html` - Detailed HTML report with screenshots on failure
- **Surefire Reports**: `target/surefire-reports/` - Standard Maven test reports

## Design Patterns

- **Page Object Model (POM)**: Each page has a dedicated class encapsulating selectors and actions
- **Component Pattern**: Reusable UI components (Header, Footer) as separate classes
- **Factory Pattern**: `DriverFactory` manages WebDriver lifecycle with ThreadLocal support
- **Configuration Pattern**: `ConfigReader` with System property fallback for flexible configuration

## CI/CD

GitHub Actions workflow runs automatically:
- **On push/PR to main**: Smoke tests with Chrome
- **Manual dispatch**: Select suite (smoke/regression) and browser (Chrome/Firefox)

## Related Projects

This project is part of a complete testing portfolio:

1. [Playwright E2E Testing Framework](https://github.com/paoquiroz/playwright-e2e-testing-framework) - End-to-end tests
2. [Playwright API Testing Framework](https://github.com/paoquiroz/playwright-api-testing-framework) - API tests
3. [k6 Performance Testing Framework](https://github.com/paoquiroz/k6-performance-testing-framework) - Performance tests
4. **Selenium UI Testing Framework** (this project) - Cross-browser UI tests
5. [JMeter Performance Testing Framework](https://github.com/paoquiroz/jmeter-performance-testing-framework) - Load & stress tests

## License

MIT License - see [LICENSE](LICENSE) for details.
