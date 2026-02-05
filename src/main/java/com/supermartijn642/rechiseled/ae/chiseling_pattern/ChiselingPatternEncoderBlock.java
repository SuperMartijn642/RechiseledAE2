package com.supermartijn642.rechiseled.ae.chiseling_pattern;

import com.supermartijn642.core.CommonUtils;
import com.supermartijn642.core.block.BaseBlock;
import com.supermartijn642.core.block.BlockProperties;
import com.supermartijn642.core.block.BlockShape;
import com.supermartijn642.core.block.EntityHoldingBlock;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Created 14/02/2026 by SuperMartijn642
 */
public class ChiselingPatternEncoderBlock extends BaseBlock implements EntityHoldingBlock {

    public static final BooleanProperty ON = BooleanProperty.create("on");

    private static final BlockShape SHAPE = BlockShape.or(
        BlockShape.createBlockShape(2, 0, 2, 14, 2, 14),
        BlockShape.createBlockShape(3, 3, 3, 13, 4, 13),
        BlockShape.createBlockShape(1, 4, 1, 15, 10, 15)
    );

    public ChiselingPatternEncoderBlock(){
        super(
            false,
            BlockProperties.create()
                .mapColor(MapColor.METAL)
                .requiresCorrectTool()
                .destroyTime(4)
                .explosionResistance(6)
                .sound(SoundType.METAL)
                .lightLevel(state -> state.getValue(ON) ? 7 : 0)
        );
        this.registerDefaultState(this.defaultBlockState().setValue(ON, false));
    }

    @Override
    protected InteractionFeedback interact(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, Direction hitSide, Vec3 hitLocation){
        if(!level.isClientSide)
            CommonUtils.openContainer(new ChiselingPatternEncoderContainer(player, pos));
        return InteractionFeedback.SUCCESS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
        return SHAPE.getUnderlying();
    }

    @Override
    public BlockEntity createNewBlockEntity(BlockPos pos, BlockState state){
        return new ChiselingPatternEncoderBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> builder){
        builder.add(ON);
    }
}
