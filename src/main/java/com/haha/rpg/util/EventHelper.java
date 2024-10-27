package com.haha.rpg.util;

import com.haha.rpg.Main;
import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;
import org.reflections.Reflections;

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class EventHelper implements Basics {
    private static final Map<Class<?>, Consumer<Object>> listeners = new HashMap<>();

    static {
        listeners.put(MouseAdapter.class, instance -> {
            panel.addMouseListener((MouseListener) instance);
            panel.addMouseMotionListener((MouseMotionListener) instance);
            panel.addMouseWheelListener((MouseWheelListener) instance);
        });
        listeners.put(ContainerAdapter.class, instance -> panel.addContainerListener((ContainerListener) instance));
        listeners.put(KeyAdapter.class, instance -> panel.addKeyListener((KeyListener) instance));
        listeners.put(ComponentAdapter.class, instance -> panel.addComponentListener((ComponentListener) instance));
        listeners.put(FocusAdapter.class, instance -> panel.addFocusListener((FocusListener) instance));
        listeners.put(WindowAdapter.class, instance -> Main.getInstance().addWindowListener((WindowListener) instance));
    }

    public static void registerAllEvents() {
        try {
            Reflections reflections = new Reflections("com.haha.rpg.events");
            Set<Class<?>> eventClasses = reflections.getTypesAnnotatedWith(Events.class);
            for (Class<?> eventClass : eventClasses) {
                Object eventInstance = eventClass.getDeclaredConstructor().newInstance();
                listeners.forEach((listenerClass, addListener) -> {
                    if (listenerClass.isInstance(eventInstance)) {
                        addListener.accept(eventInstance);
                    }
                });
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}