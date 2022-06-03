package steps;

import base.model.UserAccount;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hook {


    private UserAccount userAccount;
    private static final Logger LOGGER = LogManager.getLogger(Hook.class);

    @BeforeScenario
    public void initDriver() {
        LOGGER.info("Open hook:"+userAccount.sizeOfList());


    }

}
