package team.hollow.neutronia.api;

import java.util.List;

public interface LoadingProgress {

    LoadingProgress INSTANCE = APIInternals.getLoadingProgress();

    TaskInfo.Mutable pushTask();

    void popTask(TaskInfo ti);

    void popTask();

    interface TaskInfo {

        /* @Nullable */
        TaskInfo getParent();

        List<TaskInfo> getChildren();

        String getText();

        interface Mutable extends TaskInfo {

            void withTaskName(String name);

        }

    }

}