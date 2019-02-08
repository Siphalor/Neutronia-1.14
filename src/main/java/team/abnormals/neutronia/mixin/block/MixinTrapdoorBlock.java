/*
 * Copyright (c) 2016, 2017, 2018 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package team.abnormals.neutronia.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import team.abnormals.neutronia.api.Climbable;

@Mixin(TrapdoorBlock.class)
public abstract class MixinTrapdoorBlock implements Climbable {

    @Shadow
    @Final
    public static BooleanProperty OPEN;

    @Override
    public boolean canClimb(LivingEntity entity, BlockState state, BlockPos pos) {
        Boolean isOpen = state.get(OPEN);
        Block ladderBlock = entity.world.getBlockState(pos.down()).getBlock();

        return ladderBlock instanceof LadderBlock && isOpen;
    }

}