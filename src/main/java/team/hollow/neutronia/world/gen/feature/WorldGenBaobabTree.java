/*
package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.VineBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.blocks.NeutroniaLeavesBlock;
import vibrantjourneys.blocks.BlockPVJLeaves;
import vibrantjourneys.init.PVJBlocks;
import vibrantjourneys.util.EnumLeafType;
import vibrantjourneys.util.EnumWoodType;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class WorldGenBaobabTree extends AbstractTreeFeature<DefaultFeatureConfig>
{
    private static final BlockState LOG = PVJBlocks.LOGS.get(EnumWoodType.BAOBAB.getID()).getDefaultState();
    
    private static final BlockState LEAF = PVJBlocks.LEAVES.get(EnumLeafType.BAOBAB.getID()).getDefaultState()
    		.withProperty(NeutroniaLeavesBlock.PERSISTENT, Boolean.FALSE);
    
    public WorldGenBaobabTree(boolean notify, int baseHeightIn, int extraRandomHeightIn)
    {
        super(notify, baseHeightIn, extraRandomHeightIn, LOG, LEAF);
    }

    @Override
    public boolean generate(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos)
    {
        int i = this.getHeight(random);

        if (!this.ensureGrowable(modifiableTestableWorld, random, blockPos, i))
        {
            return false;
        }
        else
        {
            for (int i2 = 0; i2 < i; ++i2)
            {
                BlockPos blockpos = blockPos.up(i2);

                if (this.isAirLeaves(modifiableTestableWorld,blockpos))
                {
                    this.setBlockAndNotifyAdequately(modifiableTestableWorld, blockpos, this.woodMetadata);
                }

                if (i2 < i - 1)
                {
                    BlockPos blockpos1 = blockpos.east();

                    if (this.isAirLeaves(worldIn,blockpos1))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos1, this.woodMetadata);
                    }

                    BlockPos blockpos2 = blockpos.south().east();

                    if (this.isAirLeaves(worldIn,blockpos2))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos2, this.woodMetadata);
                    }

                    BlockPos blockpos3 = blockpos.south();

                    if (this.isAirLeaves(worldIn,blockpos3))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos3, this.woodMetadata);
                    }
                }
                if(i2 == i - 1)
                {
                	BlockPos branchPos = blockpos;
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.north();
                		if(rand.nextBoolean())
                			branchPos = branchPos.west();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	int width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}

                	branchPos = blockpos;
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.west();
                		if(rand.nextBoolean())
                			branchPos = branchPos.north();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.east();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.north();
                		if(rand.nextBoolean())
                			branchPos = branchPos.east();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.east();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.east();
                		if(rand.nextBoolean())
                			branchPos = branchPos.north();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.south();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.south();
                		if(rand.nextBoolean())
                			branchPos = branchPos.west();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.south();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.west();
                		if(rand.nextBoolean())
                			branchPos = branchPos.south();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.south().east();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.south();
                		if(rand.nextBoolean())
                			branchPos = branchPos.east();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                	branchPos = blockpos.south().east();
                	for(int length = 0; length <= 3 + rand.nextInt(4); length++)
                	{
                		branchPos = branchPos.east();
                		if(rand.nextBoolean())
                			branchPos = branchPos.south();
                		
                        if (this.isAirLeaves(worldIn, branchPos))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, branchPos, this.woodMetadata);
                            if(rand.nextBoolean())
                            {
                                if (this.isAirLeaves(worldIn, branchPos.down()))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, branchPos.down(), this.woodMetadata);
                                }
                            }
                        }
                        
                        branchPos = branchPos.up();
                	}
                	width = 4 + rand.nextInt(3);
                	for(int g = -1; g < 3; g++)
                	{
                    	this.growLeavesLayer(worldIn, branchPos.up(g), width);
                    	width -= 1 + rand.nextInt(2);
                	}
                }
                
                if(i2 == 0)
                {
            		int height = i - 1;
                	//inner layer
                	for(int ilike = -2; ilike < 2; ilike++)
                	{
                		if(ilike == -2 || ilike == 1)
                		{
                			height = 6 + rand.nextInt(6);
                		}
                		else
                		{
                			height = i - 1;
                		}
                		for(int tacos = 0; tacos < height; tacos++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.north().west(ilike).up(tacos)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.north().west(ilike).up(tacos), this.woodMetadata);
                            }
                		}
                		if(ilike == -2 || ilike == 1)
                		{
                			height = 8 + rand.nextInt(6);
                		}
                		else
                		{
                			height = i - 1;
                		}
                		for(int sausages = 0; sausages < height; sausages++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.south(2).west(ilike).up(sausages)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.south(2).west(ilike).up(sausages), this.woodMetadata);
                            }
                		}
                	}
                	for(int jugs = 0; jugs < 2; jugs++)
                	{
                		height = i - 1;
                		for(int and = 0; and < height; and++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.west().south(jugs).up(and)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.west().south(jugs).up(and), this.woodMetadata);
                            }
                		}
                		for(int buns = 0; buns < height; buns++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.east(2).south(jugs).up(buns)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.east(2).south(jugs).up(buns), this.woodMetadata);
                            }
                		}
                	}
                	
                	//outer layer
                	for(int l = 0; l < 2; l++)
                	{
                		height = 1 + rand.nextInt(4);
                		for(int g = 0; g < height; g++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.west(2).south(l).up(g)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.west(2).south(l).up(g), this.woodMetadata);
                            }
                		}
                		
                		height = 1 + rand.nextInt(4);
                		for(int b = 0; b < height; b++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.east(3).south(l).up(b)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.east(3).south(l).up(b), this.woodMetadata);
                            }
                		}
                		
                		height = 1 + rand.nextInt(4);
                		for(int t = 0; t < height; t++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.north(2).east(l).up(t)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.north(2).east(l).up(t), this.woodMetadata);
                            }
                		}
                		
                		height = 1 + rand.nextInt(4);
                		for(int pride = 0; pride < height; pride++)
                		{
                            if (this.isAirLeaves(worldIn, blockpos.south(3).east(l).up(pride)))
                            {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos.south(3).east(l).up(pride), this.woodMetadata);
                            }
                		}
                	}
                }
            }

            return true;
        }
    }

    private boolean isAirLeaves(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos);
    }

    private void generateBranches(World world, Random rand, BlockPos pos)
    {
        int numBranches = 2;
        Direction facing = Direction.Type.HORIZONTAL.random(rand);
        int length;
        ArrayList<BlockPos> outerLeaves = new ArrayList<BlockPos>();


        for(int i = 0; i <= numBranches; i++)
        {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            Direction temp = Direction.Type.HORIZONTAL.random(rand);
            if(!facing.getDirection().equals(temp.getDirection()))
            {
                facing = temp;
            }

            length = rand.nextInt(3) + 2;
            BlockState state = world.getBlockState(pos);
            for(int h = 0; h <= length; h++)
            {
                x += facing.getOffsetX();
                z += facing.getOffsetZ();

                if(rand.nextInt(4) < 2)
                    y += 1;

                BlockPos logpos = new BlockPos(x, y, z);
                state = world.getBlockState(logpos);
                if (state.isAir() || state.matches(BlockTags.LEAVES))
                {
                    this.setBlockState(world, logpos, LOG);
                    if(rand.nextInt(2) == 0)
                    {
                        state = world.getBlockState(logpos.down());
                        if (state.getBlock().isAir(state, world, logpos.down()) || state.getBlock().isLeaves(state, world, logpos.down()))
                        {
                            this.setBlockAndNotifyAdequately(world, logpos.down(), LOG);
                        }
                    }
                }

                for(int up = -rand.nextInt(3); up <= rand.nextInt(2) + 1; up++)
                {
                    for(int west = -rand.nextInt(3); west <= rand.nextInt(2) + 1; west++)
                    {
                        for(int north = -rand.nextInt(3); north <= rand.nextInt(2) + 1; north++)
                        {
                            BlockPos leafpos = logpos.up(up).west(west).north(north);
                            state = world.getBlockState(leafpos);
                            if (state.getBlock().isAir(state, world, leafpos) || state.getBlock().isLeaves(state, world, leafpos))
                            {
                                if(Math.abs(up) != 1 && Math.abs(west) != 1 && Math.abs(north) != 1)
                                {
                                    if(rand.nextInt(13) < 5)
                                    {
                                        this.setBlockAndNotifyAdequately(world, leafpos, LEAVES);
                                        outerLeaves.add(leafpos);
                                    }
                                }
                                else
                                {
                                    this.setBlockAndNotifyAdequately(world, leafpos, LEAVES);
                                }
                            }
                        }
                    }
                }
            }
        }
        generateVines(world, rand, outerLeaves);
    }

    public boolean canGenerateLogAt(World world, BlockPos pos)
    {
        return canTreeReplace(world, pos);
    }

    private void generateVines(World world, Random rand, ArrayList<BlockPos> outerLeaves)
    {
        for(BlockPos pos : outerLeaves)
        {
            BlockPos vinepos = pos;
            int length;
            if(world.isAir(pos.north()))
            {
                BlockState state = Blocks.VINE.getDefaultState().with(VineBlock.SOUTH, Boolean.TRUE);
                length = 3 + rand.nextInt(5);
                for(int i = 0; i < length; i++)
                {
                    vinepos = pos.north().down(i);
                    if(world.isAir(vinepos))
                    {
                        this.setBlockState(world, vinepos, state);
                    }
                }
            }
            if(world.isAir(pos.south()))
            {
                length = 3 + rand.nextInt(5);
                BlockState state = Blocks.VINE.getDefaultState().with(VineBlock.NORTH, Boolean.TRUE);
                for(int i = 0; i < length; i++)
                {
                    vinepos = pos.south().down(i);
                    if(world.isAir(vinepos))
                    {
                        this.setBlockState(world, vinepos, state);
                    }
                }
            }
            if(world.isAir(pos.west()))
            {
                length = 3 + rand.nextInt(5);;
                BlockState state = Blocks.VINE.getDefaultState().with(VineBlock.EAST, Boolean.TRUE);
                for(int i = 0; i < length; i++)
                {
                    vinepos = pos.west().down(i);
                    if(world.isAir(vinepos))
                    {
                        this.setBlockState(world, vinepos, state);
                    }
                }
            }
            if(world.isAir(pos.east()))
            {
                length = 3 + rand.nextInt(5);
                BlockState state = Blocks.VINE.getDefaultState().with(VineBlock.WEST, Boolean.TRUE);
                for(int i = 0; i < length; i++)
                {
                    vinepos = pos.east().down(i);
                    if(world.isAir(vinepos))
                    {
                        this.setBlockState(world, vinepos, state);
                    }
                }
            }
        }
    }

}*/
