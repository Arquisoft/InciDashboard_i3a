import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import classification.AllClassificationTest;
import entities.AllEntitiesTest;
import properties.PropertiesTest;

@RunWith(Suite.class)
@SuiteClasses({ AllEntitiesTest.class, PropertiesTest.class, AllClassificationTest.class })
public class AllTests {

}
