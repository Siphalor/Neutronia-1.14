package team.hollow.neutronia.event_system;

import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Q: BUT KLASS FORGE/FABRIC/RIFT/WHATEVER HAS THESE EVENTS WHY ARE YOU WRAPPING THEM FOR NO REASON
//A: abstraction is love, abstraction is life, especially if you randomly decide to use a different modloader or some shit.
//put simply, there is never a good reason not to wrapper your junk, okay, then when your chosen platform FAILS YOU FOR THE LAST TIME
//you can just re-implement the shit in those one file(s) mostly.

public class EventCore {
    public static EventCore instance;

    public static DebugTextEventHandler debugHandler;
    //ChunkLoad triggers AFTER load, more or less
    public EventSubscriptionObject ChunkLoad = EventSubscriptionObject.getEvent("ChunkLoad");

    //while not strictly necessary,
    //routing all event generation through functions up here
    //serves as an implicit index of implemented events.
    //the functions also serve as examples of the argument-signature required to recieve invocations of the event.
    //triggers BEFORE unload, more or less
    public EventSubscriptionObject ChunkUnload = EventSubscriptionObject.getEvent("ChunkUnload");
    //triggers AFTER vanilla tick.
    public EventSubscriptionObject ClientTick = EventSubscriptionObject.getEvent("ClientTick");
    //triggers within renderCenter of GameRenderer, just before rendering the player hand.
    public EventSubscriptionObject RenderWorldLast = EventSubscriptionObject.getEvent("RenderWorldLast");
    public EventSubscriptionObject BlockUpdate = EventSubscriptionObject.getEvent("BlockUpdate");
    public EventSubscriptionObject ClientWorldLoad = EventSubscriptionObject.getEvent("ClientWorldLoad");
    public EventSubscriptionObject MinecraftClientPostStartupResourceHook = EventSubscriptionObject.getEvent("MinecraftClientPostStartupResourceHook");
    public EventSubscriptionObject InputEvent = EventSubscriptionObject.getEvent("InputEvent");

    public EventCore() {
        instance = this;


        ClientTickCallback.EVENT.register((MinecraftClient client) -> {
            EventCore.instance.onClientTick(client);
        });
        debugHandler = new DebugTextEventHandler();
    }

    public void onChunkLoad(boolean clientside, WorldChunk chunk) {
        ChunkLoad.invoke(clientside, chunk);
    }

    public void onChunkUnload(boolean clientside, WorldChunk chunk) {
        ChunkUnload.invoke(clientside, chunk);
    }

    public void onClientTick(MinecraftClient client) {
        ClientTick.invoke(client);
    }

    public void onRenderWorldLast(float partialTicks) {
        GL11.glPushMatrix();
        {
            GL11.glTranslated(-BlockEntityRenderDispatcher.renderOffsetX,
                    -BlockEntityRenderDispatcher.renderOffsetY,
                    -BlockEntityRenderDispatcher.renderOffsetZ);
            RenderWorldLast.invoke(partialTicks);
            GL11.glTranslated(BlockEntityRenderDispatcher.renderOffsetX,
                    BlockEntityRenderDispatcher.renderOffsetY,
                    BlockEntityRenderDispatcher.renderOffsetZ);
        }
        GL11.glPopMatrix();
    }

    public void onBlockUpdate(World worldIn, BlockPos pos, BlockState oldState, BlockState newState, int flags, boolean client) {
        BlockUpdate.invoke(worldIn, pos, oldState, newState, flags, client);
    }

    public void onClientWorldLoad(ClientWorld client) {
        ClientWorldLoad.invoke(client);
    }

    public void onMinecraftClientPostStartupResourceHook() {
        MinecraftClientPostStartupResourceHook.invoke();
    }

    public void onInputEvent() {
        InputEvent.invoke();
    }

    ///////////////////////////

    //...so as i pray, unlimited boilerplates


    public void loadEventSubscribers(Object object) {
        Method[] methods = object.getClass().getMethods();

        for (Method method : methods) {
            Subscribe annotation = method.getAnnotation(Subscribe.class);
            if (annotation != null) {
                String event = annotation.event();//get event described by event="blah" in annotation
                if (event.equals("")) event = method.getName();//lazy shortcut for laziness beyond imagination
                if (!event.equals("")) {
                    System.out.println("Subscribed " + event + " for " + method.getName() + " for " + object.getClass().getSimpleName());
                    EventSubscriptionObject.getEvent(event).addSubscriber(new EventSubscriber(object, method));
                }
                //we basically construct shit so the event provider doesn't even have to exist at the time we subscribe
                //maximum laziness support!
            }

        }
    }

    public void unloadEventSubscribers(Object object) {
        EventSubscriptionObject.removeSubscriberFromEvents(object);
    }

    static public class EventSubscriber//a container for the object and its method that is subscribed
    {
        Object object;
        Method method;

        public EventSubscriber(Object object, Method method) {
            this.object = object;
            this.method = method;
        }
    }

    static public class EventSubscriptionObject//most descriptive name every
    {
        static HashMap<String, EventSubscriptionObject> eventMap = new HashMap<String, EventSubscriptionObject>();
        ArrayList<EventSubscriber> eventSubscribers = new ArrayList<EventSubscriber>();
        String eventName = "";

        EventSubscriptionObject(String event) {
            eventName = event;
            eventMap.put(eventName, this);
        }

        static public EventSubscriptionObject getEvent(String eventn) {
            EventSubscriptionObject revent = eventMap.get(eventn);
            if (revent == null) revent = new EventSubscriptionObject(eventn);
            return revent;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        static public void removeSubscriberFromEvents(Object obj) {
            Iterator it = eventMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, EventSubscriptionObject> pair = (Map.Entry) it.next();
                pair.getValue().removeSubscriber(obj);
            }
        }

        public void addSubscriber(EventSubscriber s) {
            eventSubscribers.add(s);
        }

        @SuppressWarnings("unchecked")
        public void removeSubscriber(Object obj) {
            ArrayList<EventSubscriber> tmp;
            tmp = (ArrayList<EventSubscriber>) eventSubscribers.clone();
            for (EventSubscriber s : tmp) {
                if (s.object == obj) eventSubscribers.remove(s);
            }
        }

        //ultra-lazy invocation mechanism.
        //basically we just hamfist whatever we want at invoke and it shoves it at anything subscribed and crosses fingers
        //because fuck it, support all the arguments, you can do it, when you java, you can do anything, just do it, you can do anything
        //screams loudly if arguments are wrong because that lets the poor bastard subscribing know he fucked up k
        public void invoke(Object... arg) {
            for (int i = 0; i < eventSubscribers.size(); i++) {
                EventSubscriber s = eventSubscribers.get(i);
                try {
                    s.method.invoke(s.object, arg);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
