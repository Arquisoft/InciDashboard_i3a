package entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AgentTest.class, CommentTest.class, IncidentTest.class, OperatorTest.class, UserInfoTest.class })
public class AllEntitiesTest {

}
