package selenium;


public enum TestResult {

    PASS("pass"),
    FAIL("fail"),
    EXCEPTION("exception");

    private String result;

    TestResult(String result) {
        this.result = result;
    }
}
