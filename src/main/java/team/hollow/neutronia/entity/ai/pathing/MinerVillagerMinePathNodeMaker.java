package team.hollow.neutronia.entity.ai.pathing;

import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.BlockView;

public class MinerVillagerMinePathNodeMaker extends PathNodeMaker {

    @Override
    public PathNode getStart() {
        return null;
    }

    @Override
    public PathNode getPathNode(double v, double v1, double v2) {
        return null;
    }

    @Override
    public int getPathNodes(PathNode[] pathNodes, PathNode pathNode, PathNode pathNode1, float v) {
        return 0;
    }

    @Override
    public PathNodeType getPathNodeType(BlockView blockView, int i, int i1, int i2, MobEntity mobEntity, int i3, int i4, int i5, boolean b, boolean b1) {
        return null;
    }

    @Override
    public PathNodeType getPathNodeType(BlockView blockView, int i, int i1, int i2) {
        return null;
    }

}