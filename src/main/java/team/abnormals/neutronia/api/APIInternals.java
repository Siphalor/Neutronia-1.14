package team.abnormals.neutronia.api;

import net.fabricmc.loader.api.FabricLoader;
import team.abnormals.neutronia.LoadingProgressImpl;
import team.abnormals.neutronia.Neutronia;

import java.util.Collections;
import java.util.List;

public class APIInternals {

    static LoadingProgress getLoadingProgress() {
        if (FabricLoader.getInstance().isModLoaded(Neutronia.MODID)) {
            return LoadingProgressImpl.INSTANCE;
        } else {
            return DummyLoadingProgress.INSTANCE;
        }
    }

    private static class DummyLoadingProgress implements LoadingProgress {

        @Override
        public TaskInfo.Mutable pushTask() {
            return DummyTaskInfo.INSTANCE;
        }

        @Override
        public void popTask(TaskInfo ti) {
        }

        @Override
        public void popTask() {
        }

        private static class DummyTaskInfo implements TaskInfo.Mutable {

            public static final DummyTaskInfo INSTANCE = new DummyTaskInfo();

            @Override
            public void withTaskName(String name) {
            }

            @Override
            public TaskInfo getParent() {
                return null;
            }

            @Override
            public List<TaskInfo> getChildren() {
                return Collections.emptyList();
            }

            @Override
            public String getText() {
                return "";
            }

        }

    }

}