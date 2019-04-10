package team.hollow.neutronia.event_system;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

public class DebugTextEventHandler {
    static public ArrayList<String> leftLines = new ArrayList<String>();
    static public ArrayList<String> rightLines = new ArrayList<String>();
    public EventCore.EventSubscriptionObject DebugTextLeft = EventCore.EventSubscriptionObject.getEvent("DebugTextLeft");
    public EventCore.EventSubscriptionObject DebugTextRight = EventCore.EventSubscriptionObject.getEvent("DebugTextRight");

    DebugTextEventHandler() {
        EventCore.instance.loadEventSubscribers(this);
    }

    @Subscribe(event = "ClientTick")
    public void onClientTick(MinecraftClient client) {
        leftLines.clear();
        DebugTextLeft.invoke(leftLines);
        rightLines.clear();
        //DebugTextRight.invoke(rightLines);
    }
}
