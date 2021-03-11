import XCTest
import shared




class IosTest : TestEnvironment{
    func assertNodeDisplayed(text: String) {
        XCUIApplication().tables.buttons[text]

    }
    
    func clickOnNodeWithText(text: String) {
        XCUIApplication().tables.buttons[text].tap()

    }
    
  
    
    func assertEquals(expected: String, actual: String) {
        XCTAssertEqual(expected, actual)
    }
    
    func assertTrue(assert: Bool) {
        XCTAssertTrue(assert)
    }
    
    let app = XCUIApplication()
    
    func launchApp() {
        app.launch()
    }
    
    
}


class appNameUITests: XCTestCase {

    
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = true

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func whenUserClicksOnListItemOpenDetailPage() {

        let test = IosTest()
         
        MyTest(testEnvironment: test).whenUserClicksOnListItem_OpenDetailPage()
               
       

        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }
    
  

    func testLaunchPerformance() {
        let test = IosTest()
         
        MyTest(testEnvironment: test).whenUserClicksOnListItem_OpenDetailPage()
       
    }
}
