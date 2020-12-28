package spring.examples.profiles.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static spring.examples.profiles.config.ProfileConsts.DEVELOPMENT_PROFILE;

@Component
@Profile(DEVELOPMENT_PROFILE)
public class DevDatasourceConfig implements MyDatasourceConfig {

    @Override
    public void setup() {
        System.out.println("Setting up datasource for DEV environment. ");
    }
}
