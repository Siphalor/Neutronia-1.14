package team.hollow.neutronia.entity;

import net.minecraft.block.Material;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class DragonPositionGenerator {

    public static Vec3d findRandomTargetBlock(MobEntityWithAi entitycreatureIn, int xz, int y, Vec3d targetVec3) {
        Vec3d vec = generateRandomPos(entitycreatureIn, xz, y, targetVec3, false);
        return vec == null ? entitycreatureIn.getPosVector() : vec;
    }

    public static Vec3d generateRandomPos(MobEntityWithAi mob, int xz, int y, Vec3d vec, boolean skipWater) {
        EntityNavigation pathnavigate = mob.getNavigation();
        Random random = mob.getRand();
        boolean flag;

        if (mob.getSleepingPosition().isPresent()) {
            double d0 = mob.getSleepingPosition().get().distanceTo(MathHelper.floor(mob.x), MathHelper.floor(mob.y), MathHelper.floor(mob.z)) + 4.0D;
            double d1 = (double) (mob.getSleepingPosition().get().getY() + (float) xz);
            flag = d0 < d1 * d1;
        } else {
            flag = false;
        }

        boolean flag1 = false;
        float f = -99999.0F;
        int k1 = 0;
        int i = 0;
        int j = 0;

        for (int k = 0; k < 10; ++k) {
            int l = random.nextInt(2 * xz + 1) - xz;
            int i1 = random.nextInt(2 * y + 1) - y;
            int j1 = random.nextInt(2 * xz + 1) - xz;

            if (vec == null || (double) l * vec.x + (double) j1 * vec.z >= 0.0D) {
                if (mob.getSleepingPosition().isPresent() && xz > 1) {
                    BlockPos blockpos = mob.getSleepingPosition().get();

                    if (mob.x > (double) blockpos.getX()) {
                        l -= random.nextInt(xz / 2);
                    } else {
                        l += random.nextInt(xz / 2);
                    }

                    if (mob.z > (double) blockpos.getZ()) {
                        j1 -= random.nextInt(xz / 2);
                    } else {
                        j1 += random.nextInt(xz / 2);
                    }
                }

                BlockPos blockpos1 = new BlockPos((double) l + mob.x, (double) i1 + mob.y, (double) j1 + mob.z);

                if ((!flag || mob.method_18407(blockpos1)) && pathnavigate.isValidPosition(blockpos1)) {
                    if (skipWater) {
                        blockpos1 = moveAboveSolid(blockpos1, mob);
                        if (isWaterDestination(blockpos1, mob)) {
                            continue;
                        }
                    }

                    float f1 = mob.getPathfindingFavor(blockpos1);

                    if (f1 > f) {
                        f = f1;
                        k1 = l;
                        i = i1;
                        j = j1;
                        flag1 = true;
                    }
                }
            }
        }

        if (flag1) {
            return new Vec3d((double) k1 + mob.x, (double) i + mob.y, (double) j + mob.z);
        } else {
            return null;
        }
    }

    private static BlockPos moveAboveSolid(BlockPos pos, MobEntityWithAi mob) {
        if (!mob.world.getBlockState(pos).getMaterial().isLiquid()) {
            return pos;
        } else {
            BlockPos blockpos;

            for (blockpos = pos.up(); blockpos.getY() < mob.world.getHeight() && mob.world.getBlockState(blockpos).getMaterial().isLiquid(); blockpos = blockpos.up()) {
            }

            return blockpos;
        }
    }

    private static boolean isWaterDestination(BlockPos pos, MobEntityWithAi mob) {
        return mob.world.getBlockState(pos).getMaterial() == Material.WATER;
    }
}