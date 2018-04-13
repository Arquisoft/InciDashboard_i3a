package cucumber;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ cancelIncident.class, closeIndicent.class, inProcessIncident.class, LoginCuc.class,
		openIncident.class })
public class AllCucumberTest {

}
