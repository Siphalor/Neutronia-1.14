package team.abnormals.neutronia.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.item.SeedsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SeedsItem.class)
public interface SeedsItemAccessor {
    @Accessor
    Block getBlock();
}