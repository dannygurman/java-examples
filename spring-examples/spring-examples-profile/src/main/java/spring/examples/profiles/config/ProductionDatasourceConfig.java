package spring.examples.profiles.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static spring.examples.profiles.config.ProfileConsts.PRODUCTION_PROFILE;

@Component
@Profile(PRODUCTION_PROFILE)
public class ProductionDatasourceConfig implements MyDatasourceConfig {
    @Override
    public void setup() {
        System.out.println("Setting up datasource for PRODUCTION environment. ");
    }
}