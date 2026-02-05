package com.supermartijn642.rechiseled.ae;

import appeng.api.crafting.PatternDetailsHelper;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.supermartijn642.core.CommonUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.block.BaseBlock;
import com.supermartijn642.core.block.BaseBlockEntityType;
import com.supermartijn642.core.item.BaseBlockItem;
import com.supermartijn642.core.item.CreativeItemGroup;
import com.supermartijn642.core.item.ItemProperties;
import com.supermartijn642.core.network.PacketChannel;
import com.supermartijn642.core.network.PacketDirection;
import com.supermartijn642.core.registry.RegistrationHandler;
import com.supermartijn642.core.registry.RegistryEntryAcceptor;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.*;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.ChiselingPatternEncoderContainer;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.packet.PacketEncodePattern;
import com.supermartijn642.rechiseled.ae.chiseling_pattern.screen.packet.PacketSelectEntry;
import com.supermartijn642.rechiseled.api.registration.RechiseledRegistration;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import java.util.function.Consumer;

/**
 * Created 25/04/2023 by SuperMartijn642
 */
@Mod(RechiseledAE.MODID)
public class RechiseledAE {

    public static final String MODID = "rechiseledae";

    public static ResourceLocation identifier(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final RechiseledRegistration REGISTRATION = RechiseledRegistration.get(MODID);
    @SuppressWarnings("Convert2MethodRef")
    public static final CreativeItemGroup GROUP = (CreativeItemGroup)REGISTRATION.itemGroup(() -> RechiseledAEBlocks.FLUIX_BLOCK_CHECKERBOARD.getRegularBlock(), "Rechiseled: Applied Energistics 2");
    public static final PacketChannel CHANNEL = PacketChannel.create(MODID);

    @RegistryEntryAcceptor(namespace = MODID, identifier = "chiseling_pattern", registry = RegistryEntryAcceptor.Registry.ITEMS)
    public static Item chiseling_pattern;
    @RegistryEntryAcceptor(namespace = MODID, identifier = "chiseling_pattern_encoder", registry = RegistryEntryAcceptor.Registry.BLOCKS)
    public static BaseBlock chiseling_pattern_encoder;
    @RegistryEntryAcceptor(namespace = MODID, identifier = "chiseling_pattern_encoder_entity", registry = RegistryEntryAcceptor.Registry.BLOCK_ENTITY_TYPES)
    public static BaseBlockEntityType<ChiselingPatternEncoderBlockEntity> chiseling_pattern_encoder_entity;

    public RechiseledAE(IEventBus eventBus){
        CHANNEL.registerMessage(PacketSelectEntry.class, PacketSelectEntry::new, PacketDirection.CLIENT_TO_SERVER, true);
        CHANNEL.registerMessage(PacketEncodePattern.class, PacketEncodePattern::new, PacketDirection.CLIENT_TO_SERVER, true);

        // Make sure the blocks get loaded
        RechiseledAEBlocks.init();
        RechiseledAERecipes.init();

        // Register everything for chiseling patterns
        RegistrationHandler handler = RegistrationHandler.get(MODID);
        handler.registerItem("chiseling_pattern", () -> PatternDetailsHelper.encodedPatternItemBuilder(ChiselingPattern::new).invalidPatternTooltip(ChiselingPattern::getInvalidTooltip).build());
        handler.registerDataComponentType("encoded_chiseling_pattern", EncodedChiselingPattern.COMPONENT_TYPE);
        handler.registerBlock("chiseling_pattern_encoder", ChiselingPatternEncoderBlock::new);
        handler.registerItem("chiseling_pattern_encoder", () -> new BaseBlockItem(chiseling_pattern_encoder, ItemProperties.create().group(GROUP)){
            @Override
            protected void appendItemInformation(ItemStack stack, Consumer<Component> info, boolean advanced){
                Component molecularAssembler = TextComponents.block(AEBlocks.MOLECULAR_ASSEMBLER.block()).color(ChatFormatting.GOLD).get();
                info.accept(TextComponents.translation("rechiseledae.chiseling_pattern_encoder.hint", molecularAssembler).color(ChatFormatting.GRAY).get());
                super.appendItemInformation(stack, info, advanced);
            }
        });
        handler.registerBlockEntityType("chiseling_pattern_encoder_entity", () -> BaseBlockEntityType.create(ChiselingPatternEncoderBlockEntity::new, chiseling_pattern_encoder));
        handler.registerMenuType("chiseling_pattern_encoder", ChiselingPatternEncoderContainer.TYPE);
        ChiselingPatternDataGenerators.register();

        // Register data providers for generating all the json files
        REGISTRATION.registerDataProviders();

        if(CommonUtils.getEnvironmentSide().isClient())
            RechiseledAEClient.initialize();
    }
}
