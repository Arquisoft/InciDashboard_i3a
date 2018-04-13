import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classification.AllClassificationTest;
import controllers.AllControllerTest;
import entities.AllEntitiesTest;
import properties.PropertiesTest;
import services.AllServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ AllEntitiesTest.class, PropertiesTest.class, AllClassificationTest.class, AllControllerTest.class,
		AllServiceTest.class })
public class AllTests {

}
