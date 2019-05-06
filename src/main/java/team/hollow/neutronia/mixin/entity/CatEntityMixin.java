package team.hollow.neutronia.mixin.entity;

import com.google.common.collect.Maps;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.SystemUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(CatEntity.class)
public class CatEntityMixin {

    @Shadow @Final @Mutable public static Map<Integer, Identifier> TEXTURES = SystemUtil.consume(Maps.newHashMap(), (hashMap_1) -> {
        hashMap_1.put(0, new Identifier("textures/entity/cat/tabby.png"));
        hashMap_1.put(1, new Identifier("textures/entity/cat/black.png"));
        hashMap_1.put(2, new Identifier("textures/entity/cat/red.png"));
        hashMap_1.put(3, new Identifier("textures/entity/cat/siamese.png"));
        hashMap_1.put(4, new Identifier("textures/entity/cat/british_shorthair.png"));
        hashMap_1.put(5, new Identifier("textures/entity/cat/calico.png"));
        hashMap_1.put(6, new Identifier("textures/entity/cat/persian.png"));
        hashMap_1.put(7, new Identifier("textures/entity/cat/ragdoll.png"));
        hashMap_1.put(8, new Identifier("textures/entity/cat/white.png"));
        hashMap_1.put(9, new Identifier("textures/entity/cat/jellie.png"));
        hashMap_1.put(10, new Identifier("textures/entity/cat/all_black.png"));
    });

}