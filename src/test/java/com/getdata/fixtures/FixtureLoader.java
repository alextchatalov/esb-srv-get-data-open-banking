package com.getdata.fixtures;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class FixtureLoader {
    private static final String FIXTURE_PACKAGE = "com.getdata.fixtures.resource";

    private FixtureLoader() {

    }

    public static void loadAllFixtures() {
        FixtureFactoryLoader.loadTemplates(FIXTURE_PACKAGE);
    }
}
