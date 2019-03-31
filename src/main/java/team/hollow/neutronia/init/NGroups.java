package team.hollow.neutronia.init;

import team.hollow.neutronia.api.groups.Group;
import team.hollow.neutronia.groups.TestComponent;

public class NGroups {

    static {
        Group.builder()
                .name("Test")
                .description("This is a test")
                .addComponent(new TestComponent())
                .register();
    }

}
