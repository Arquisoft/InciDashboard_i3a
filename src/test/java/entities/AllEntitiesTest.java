package entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgentTest.class, IncidentTest.class, NotificationTest.class, OperatorTest.class })
public class AllEntitiesTest {

}
