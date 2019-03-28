package team.hollow.neutronia.book;

import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.util.profiler.Profiler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class GuidebookManager implements ResourceReloadListener {

    @Override
    public CompletableFuture<Void> apply(Helper var1, ResourceManager var2, Profiler var3, Profiler var4, Executor var5, Executor var6) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        }, var6);
    }

}
